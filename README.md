# Mod Manager

Mod Manager is the new way to install and manage minecraft java accessibility mods. You can also navigate through the app using only keyboard, more about that in the [Keyboard Only Navigation](#keyboard-only-navigation) section.
If you like my work and want to support me, you can do so via patreon - [shoaibkhan](https://www.patreon.com/shoaibkhan).

## Quick Links

- [Download Latest Release Zip File](https://github.com/accessible-minecraft/mod-manager/releases/latest/download/Mod.Manager.zip)
- [About the Latest Release](https://github.com/accessible-minecraft/mod-manager/releases/latest/)
- [Install Java](https://www.java.com/en/download/)
- [Install Fabric](https://fabricmc.net/wiki/install)
- [Discord Sever](https://discord.gg/yQjjsDqWQX)
- [Report Issues](https://github.com/accessible-minecraft/mod-manager/issues)

## Table of Contents

1. [Requirements](#requirements)
2. [How to Run The App](#how-to-run-the-app)
3. [Keyboard Only Navigation](#keyboard-only-navigation)
4. [Features](#features)
5. [Multiple Profiles](#multiple-profiles)
	- [Adding a profile](#adding-a-profile)
	- [Removing a profile](#removing-a-profile)
6. [Managing Mods](#managing-mods)
	- [Installing or Uninstalling a mod](#installing-or-uninstalling-a-mod)
	- [Changing a version of the mod](#changing-a-version-of-the-mod)
7. [Installing NVDA Controller DLL](#installing-nvda-controller-dll)
8. [Contact](#contact)
 
## Requirements 

Only java is required for the app. The minimum version of java required is java 8 (or 1.8). The link to download and install java is in [quick links](#quick-links) section.

## How to Run The App

First step for all OS types is to extract the Mod.Manager.zip file which will be extracted to Mod Manager folder containing a jar file and a `run-on-unix` file.
The jar file is universal and will run in all OS but if for any reason it does not or you want the manager to log the errors, you can use the following files:- 

**For Windows:-**

In windows, you can run the `run-on-windows.bat`. It runs just like any other exe file.

**For Linux and Mac:-**

In mac and linux, you can run `run-on-unix` and it runs just like any other executable, but if it doesn't, then try right clicking on the `run-on-unix` file and find for an option similar to **run as a program**.

If that file is not running too, then you can open the terminal in the mod manager folder and type in the following command:-

	./run-on-unix

## Keyboard Only Navigation

Yup, you can use this app without using mouse natively. The app only uses two keybindings as of now:-
- **Tab** = to cycle through the buttons and labels
- **Spacebar** = to use a button.

## Features

- [Multiple profiles support](#multiple-profiles)
- [Manage Mods](#managing-mods)
- [Install nvda controller dll for Windows](#installing-nvda-controller-dll)

## Multiple Profiles

Before I talk about how to work with profile, let me tell you what are profiles in minecraft java. Skip to []() if you know what a profile is.
Imagine you are currently on minecraft 1.17 and for some reason you want to load your 1.16 world without upgrading it to 1.17. To do that you have to change the minecraft version to 1.16 from the launcher and then delete/move all the mods for minecraft 1.17 and reinstall them for 1.16. I don't have to tell you how frustating that is when you only want test mods for different minecraft versions frequently.

Profiles in minecraft java solve this issue and does more. So when you create a profile, it creates a folder in the location of your liking, adds the mods, saves, etc folders into the profile folder. So in short each profile has a different folder to store mods and worlds too. Once setup, you can swtch to different minecraft versions without having to update the mod files.

### Adding a profile

Note- Adding a profile does not creates the profile, you have to do that from the minecraft launcher, it only adds the profile's location into the config file.

1. To add a profile you have to use/click the Add Profile button in the profiles section of the app. 
2. It will then open up a dialog box asking you to give the profile a name. The input field has a default value of `Enter profile name`. It is selected by default so when you press any button other than the arrow keys, it deletes the default value.
3. After that, it will ask for the minecraft version of the profile. Again the input field has a default value of `Enter minecraft version` and again, it is selected by default.
4. After that, it will ask for the location of the profile. The input field will have a default value of the default minecraft folder on your pc and again, it is selected by default, so when you press any button other than the arrow keys, it deletes the default value.
5. If the location and minecraft verion you enter is correct, it will add the profile and then you cycle through the profiles using the `Next Profile` button.

### Removing a profile

To remove a profile, you just have to use the `Remove Profile` button.

Note- It will not delete the original profile folder, it will only remove it from the config.json file in the Config folder

## Managing Mods

Note- You only manage a mod in the currently selected profile.
After each mod's name, there will be a `Install/Uninstall Button` and if the mod is installed, a `Change Version Button`

### Installing or Uninstalling a mod

To do that, you have to just click on the `Install/Uninstall Button` and will bring up a dialoge to select version of the mod if you want to install it.


### Changing a version of the mod

It's similar to installing a mod, just use/click the `Change Version` Button and it will opepn a dialog to select the version.

## Installing Nvda Controller Dll

It's the easiest, just use/click the Install `Nvda Controller Dll` Button and it will download and install it for ya.

## Contact

- Discord Server - https://discord.gg/yQjjsDqWQX
- Discord - Shoaib#9319 
- Email - mdshoaibkhan.1999@gmail.com
