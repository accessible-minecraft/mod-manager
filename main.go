package main

import (
	"fmt"
	"os"
	"runtime"
)

const (
	DefaultProfileKey = "modmanger.defaultprofilekey"
)

type mainApplication struct {
}

func main() {
	mainApplication := &mainApplication{}

	mainApplication.setMinecraftDirectory()

	fmt.Println("Removed fyne")
}

func (m *mainApplication) setMinecraftDirectory() {
	// FIXME Redo this for go-a
	// if m.app.Preferences().String(DefaultProfileKey) != "" {
	// 	isValid, err := checkIfValidOrNot(m.app.Preferences().String(DefaultProfileKey))
	// 	if !isValid && err != nil {
	// 		m.chooseDirectory(m.window)
	// 	}
	// 	return
	// }

	minecraftDirectory := getMinecraftDirectory()

	isValid, err := checkIfValidOrNot(minecraftDirectory)

	if isValid && err == nil {
		// TODO save directory here
	} else {
		// TODO Ask the user to enter directory here
	}
}

func getMinecraftDirectory() string {
	ostype := runtime.GOOS
	var minecraftDir string
	switch ostype {
	case "windows":
		homeDir := os.Getenv("APPDATA")

		minecraftDir = homeDir + "\\.minecraft"
	case "darwin":
		homeDir, err := os.UserHomeDir()
		checkError(err)
		minecraftDir = homeDir + "/Library/Application Support/minecraft"
	case "linux":
		homeDir, err := os.UserHomeDir()
		checkError(err)

		minecraftDir = homeDir + "/.minecraft"
	default:
		// TODO Ask user to input dir
	}

	return minecraftDir
}

func checkIfValidOrNot(directory string) (bool, error) {
	dirEntries, err := os.ReadDir(directory)

	isOptionsTXTPresent, isSavesFolderPresent, isResourceFolderPresent := false, false, false
	for _, dirEntry := range dirEntries {
		name := dirEntry.Name()
		if name == "options.txt" {
			isOptionsTXTPresent = true
		}
		if name == "saves" {
			isSavesFolderPresent = true
		}
		if name == "resourcepacks" {
			isResourceFolderPresent = true
		}
	}

	if isOptionsTXTPresent && isSavesFolderPresent && isResourceFolderPresent {
		return true, err
	}

	return false, err
}

func checkError(e error) {
	if e != nil {
		panic(e)
	}
}
