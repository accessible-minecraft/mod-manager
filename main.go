package main

import (
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"

	"github.com/accessible-minecraft/mod-manager/gui"
)

func main() {
	app := app.New()
	window := app.NewWindow("Mod Manager")

	cont := homeWindowContainer()

	window.SetContent(cont)

	window.ShowAndRun()
}

func homeWindowContainer() *fyne.Container {
	profileButton := gui.EnableDisableButton("Test", "test")
	profileButton.Resize(fyne.NewSize(150, 30))

	return container.New(&gui.CustomLayout{}, profileButton)
}
