@REM @REM Delete temperory files
del "Temp.Mod.Manager.zip"
rmdir /s /q "temp

@REM Download the latest release file
curl -L https://github.com/accessible-minecraft/mod-manager/releases/latest/download/Mod.Manager.zip --output Temp.Mod.Manager.zip

@REM Extract it
@echo off
setlocal
cd /d %~dp0

powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('Temp.Mod.Manager.zip', 'temp\')

@REM @REM Move the new files
move /Y ".\temp\Mod Manager\*" ..\

@REM @REM Delete temperory files
del "Temp.Mod.Manager.zip"
rmdir /s /q "temp"
