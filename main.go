package main

import (
	"os"
	"runtime"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/dialog"

	"github.com/accessible-minecraft/mod-manager/gui"
)

const (
	DefaultProfileKey = "modmanger.defaultprofilekey"
)

type mainApplication struct {
	app            fyne.App
	window         fyne.Window
	currentContent *fyne.Container
}

func (m *mainApplication) chooseDirectory(w fyne.Window) {
	dialog.ShowFolderOpen(func(dir fyne.ListableURI, err error) {
		if err != nil {
			dialog.ShowError(err, w)
			return
		}
		if dir != nil {
			save_dir := dir.Path() // here value of save_dir shall be updated!
			isValid, err := checkIfValidOrNot(save_dir)

			if isValid && err == nil {
				m.app.Preferences().SetString(DefaultProfileKey, save_dir)
			} else {
				dialog.ShowInformation("Error", "The selected directory is not a valid minecraft directory", m.window)
			}
		}
	}, w)
}

func main() {

	mainApplication := mainApplication{app: app.NewWithID("com.shoaibkhan.modmanager")}

	mainApplication.setContentToHome()

	mainApplication.setMinecraftDirectory()

	defer mainApplication.window.ShowAndRun()

}

func (m *mainApplication) setContentToHome() {
	m.window = m.app.NewWindow("Mod Manager")
	m.window.SetTitle("Mod Manager")

	profileButton := gui.GetProfileButton(m.app, "Selected Profile")
	profileButton.Resize(fyne.NewSize(200, 30))

	content := container.New(&gui.CustomLayout{}, profileButton)

	m.currentContent = content

	m.window.SetContent(content)
}

func (m *mainApplication) setMinecraftDirectory() {
	if m.app.Preferences().String(DefaultProfileKey) != "" {
		isValid, err := checkIfValidOrNot(m.app.Preferences().String(DefaultProfileKey))
		if !isValid && err != nil {
			m.chooseDirectory(m.window)
		}
		return
	}

	minecraftDirectory := getMinecraftDirectory()

	isValid, err := checkIfValidOrNot(minecraftDirectory)

	if isValid && err == nil {
		m.app.Preferences().SetString(DefaultProfileKey, minecraftDirectory)
	} else {
		m.chooseDirectory(m.window)
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
