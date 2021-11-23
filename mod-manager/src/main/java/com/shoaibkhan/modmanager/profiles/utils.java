package com.shoaibkhan.modmanager.profiles;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class utils {
	public static enum OS {
		WINDOWS, MACOS, LINUX;
	}

	public static String getMinecraftDirectory() {
		OS currentOs = getOS();
		String homeDirectory = "";
		StringBuilder minecraftDirectory = new StringBuilder("");

		switch (currentOs) {
		case WINDOWS: {
			homeDirectory = System.getenv("APPDATA");
			minecraftDirectory = new StringBuilder(homeDirectory);
			minecraftDirectory.append("\\.minecraft");
			break;
		}
		case LINUX: {
			homeDirectory = System.getProperty("user.home");
			minecraftDirectory = new StringBuilder(homeDirectory);
			minecraftDirectory.append("/.minecraft");
			break;
		}
		case MACOS: {
			homeDirectory = System.getProperty("user.home");
			minecraftDirectory = new StringBuilder(homeDirectory);
			minecraftDirectory.append("/Library/Application Support/minecraft");
			break;
		}
		}

		if (Files.exists(Paths.get(minecraftDirectory.toString())))
			return minecraftDirectory.toString();
		else
			return homeDirectory;
	}

	public static OS getOS() {
		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("win")) {
			return OS.WINDOWS;
		} else if (osName.contains("mac")) {
			return OS.MACOS;
		} else if (osName.contains("nix") || osName.contains("nux") || osName.indexOf("aix") > 0) {
			return OS.LINUX;
		}
		return OS.LINUX;
	}

	public static boolean checkValidity(String path) {
		if (!Files.exists(Paths.get(path).toAbsolutePath()))
			return false;

		File folder = new File(path);

		try {
			List<String> listOfItemsInFolder = Arrays.asList(folder.list());

			boolean hasSaves = listOfItemsInFolder.contains("saves");
			boolean hasOptionsTXT = listOfItemsInFolder.contains("options.txt");

			// Display a message if options.txt is not present
			if (hasSaves && !hasOptionsTXT)
				JOptionPane.showMessageDialog(null,
						"No options.txt file found!\n You can generate it by changing a setting from inside minecraft,\n like the render distance, graphics, remapping a key.",
						"Important", JOptionPane.INFORMATION_MESSAGE);

			return hasSaves;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean isOptionsTxtPresent(String path) {
		if (!Files.exists(Paths.get(path).toAbsolutePath()))
			return false;

		File folder = new File(path);

		try {
			List<String> listOfItemsInFolder = Arrays.asList(folder.list());

			boolean hasOptionsTXT = listOfItemsInFolder.contains("options.txt");

			return hasOptionsTXT;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
