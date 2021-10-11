package main

import (
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/widget"
)

func main() {
	app := app.New()
	window := app.NewWindow("Hello :)")

	label := widget.NewLabel("Hello World")

	window.SetContent(container.NewVBox(
		label,
	))

	window.ShowAndRun()
}
