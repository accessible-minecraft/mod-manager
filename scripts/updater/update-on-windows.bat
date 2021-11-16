
@REM Download the latest release file
curl -L https://github.com/accessible-minecraft/mod-manager/releases/latest/download/Mod.Manager.zip --output Temp.Mod.Manager.zip

@REM Extract it
@echo off
setlocal
cd /d %~dp0
Call :UnZipFile "temp\" "Temp.Mod.Manager.zip"
exit /b

:UnZipFile <ExtractTo> <newzipfile>
set vbs="%temp%\_.vbs"
if exist %vbs% del /f /q %vbs%
>%vbs%  echo Set fso = CreateObject("Scripting.FileSystemObject")
>>%vbs% echo If NOT fso.FolderExists(%1) Then
>>%vbs% echo fso.CreateFolder(%1)
>>%vbs% echo End If
>>%vbs% echo set objShell = CreateObject("Shell.Application")
>>%vbs% echo set FilesInZip=objShell.NameSpace(%2).items
>>%vbs% echo objShell.NameSpace(%1).CopyHere(FilesInZip)
>>%vbs% echo Set fso = Nothing
>>%vbs% echo Set objShell = Nothing
cscript //nologo %vbs%
if exist %vbs% del /f /q %vbs%

@REM Move the new files
mv -f ./temp/Mod\ Manager/* ../

@REM Delete temperory files
rm -rf Temp.Mod.Manager.zip temp