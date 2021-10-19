package main

import (
	"fmt"
	"log"
	"os"
	"runtime"

	"github.com/asticode/go-astikit"
	"github.com/asticode/go-astilectron"
)

const (
	DefaultProfileKey = "modmanger.defaultprofilekey"
)

type mainApplication struct {
}

func main() {
	// Set logger
	l := log.New(log.Writer(), log.Prefix(), log.Flags())

	// Create astilectron
	a, err := astilectron.New(l, astilectron.Options{
		AppName:           "Mod Manager",
		BaseDirectoryPath: "main",
	})
	if err != nil {
		l.Fatal(fmt.Errorf("main: creating astilectron failed: %w", err))
	}
	defer a.Close()

	// Handle signals
	a.HandleSignals()

	// Start
	if err = a.Start(); err != nil {
		l.Fatal(fmt.Errorf("main: starting astilectron failed: %w", err))
	}

	// New window
	var w *astilectron.Window
	if w, err = a.NewWindow("resources/app/index.html", &astilectron.WindowOptions{
		Center: astikit.BoolPtr(true),
		Height: astikit.IntPtr(250),
		Width:  astikit.IntPtr(200),
	}); err != nil {
		l.Fatal(fmt.Errorf("main: new window failed: %w", err))
	}

	// Create windows
	if err = w.Create(); err != nil {
		l.Fatal(fmt.Errorf("main: creating window failed: %w", err))
	}

	// Blocking pattern
	a.Wait()
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

	isValid, err := checkDirValidity(minecraftDirectory)

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

// Checks if the given directory is a valid minecraft directory or not.
func checkDirValidity(directory string) (bool, error) {
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
