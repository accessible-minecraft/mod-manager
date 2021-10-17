package main

import (
	"fmt"
	"os"
	"runtime"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"

	"github.com/accessible-minecraft/mod-manager/gui"
)

type mainApplication struct {
	app            fyne.App
	window         fyne.Window
	currentContent *fyne.Container
}

func main() {

	mainApplication := mainApplication{app: app.NewWithID("com.shoaibkhan.modmanager")}

	mainApplication.getMinecraftDirectory()

	mainApplication.setContentToHome()

}

func (m *mainApplication) setContentToHome() {
	m.window = m.app.NewWindow("Mod Manager")
	m.window.SetTitle("Mod Manager")

	defer m.window.ShowAndRun()

	profileButton := gui.GetProfileButton(m.app, "Selected Profile")
	profileButton.Resize(fyne.NewSize(150, 30))

	content := container.New(&gui.CustomLayout{}, profileButton)
	m.currentContent = content

	m.window.SetContent(content)
}

func (m *mainApplication) getMinecraftDirectory() string {
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

	fmt.Println(minecraftDir)
	return minecraftDir
}

func checkError(e error) {
	if e != nil {
		panic(e)
	}
}
