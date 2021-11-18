package com.shoaibkhan.modmanager.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.shoaibkhan.modmanager.utils.ActionResult;
import org.apache.commons.io.FileUtils;

public class LatestFiles {
    private static final String TEMP_FOLDER = Paths.get("config", "temp", "latest").toString();
    private static final String EXTRACT_FOLDER = Paths.get("config", "temp", "latest", "extract").toString();
    private static final String APP_FOLDER = Paths.get("").toString();
    private static final String FILE_NAME = Paths.get(TEMP_FOLDER, "Mod.Manager.zip").toString();
    private static final String FILE_URL = "https://github.com/accessible-minecraft/mod-manager/releases/latest/download/Mod.Manager.zip";

    public static ActionResult updateFiles() {
        // Download zip file
        ActionResult downloadResult = downloadLatestZipFile();
        if (downloadResult != ActionResult.PASS)
            return downloadResult;

        // Extract Files
        ActionResult extractResult = extractZipFile();
        if (extractResult != ActionResult.PASS)
            return extractResult;

        // Move Files
        ActionResult moveResult = moveExtractedFiles();
        if (moveResult != ActionResult.PASS)
            return moveResult;
        
        // Give run permissions to run-on-unix file
        final File runOnUnixFile = new File(Paths.get(APP_FOLDER, "run-on-unix").toString());
        runOnUnixFile.setReadable(true, false);
        runOnUnixFile.setExecutable(true, false);
        runOnUnixFile.setWritable(true, false);

        return ActionResult.PASS;
    }

    private static ActionResult moveExtractedFiles(){
        File folder = new File(Paths.get(EXTRACT_FOLDER, "Mod Manager").toString()); // the `extract/Mod Manager` folder

        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            File renameFile = new File(Paths.get(APP_FOLDER, file.getName()).toString());
            file.renameTo(renameFile);
        }
        return ActionResult.PASS;
    }

    private static ActionResult extractZipFile() {
        // From https://www.baeldung.com/java-compress-and-uncompress
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(FILE_NAME));) {
            File destDir = new File(EXTRACT_FOLDER);
            byte[] buffer = new byte[1024];

            ZipEntry zipEntry;
            zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        System.out.println("Failed to create directory " + newFile);
                        return ActionResult.FAILED_TO_CREATE_DIRECTORY;
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        System.out.println("Failed to create directory " + parent);
                        return ActionResult.FAILED_TO_CREATE_DIRECTORY;
                    }

                    // write file content
                    try (FileOutputStream fos = new FileOutputStream(newFile);) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ActionResult.FAILED;
                    }
                }
                zipEntry = zis.getNextEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ActionResult.FAILED;
        }
        return ActionResult.PASS;
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        // From https://www.baeldung.com/java-compress-and-uncompress
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    private static ActionResult downloadLatestZipFile() {
        try {
            File temp = new File(FILE_NAME);

            // Store the file
            FileUtils.copyURLToFile(new URL(FILE_URL), temp);

        } catch (IOException e) {
            e.printStackTrace();
            return ActionResult.FAILED;
        }
        return ActionResult.PASS;
    }
}
