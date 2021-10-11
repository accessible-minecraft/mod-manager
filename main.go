package main

import (
	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/widget"
)

func main() {
	app := app.New()
	window := app.NewWindow("Hello :)")

	label := widget.NewLabel("Hello World")
	label.Resize(fyne.NewSize(100, 10))
	label2 := widget.NewLabel("Hello World nahlauhnjakljsnk")
	label2.Resize(fyne.NewSize(500, 10))
	label3 := widget.NewLabel("Hello World snadljka")
	label3.Resize(fyne.NewSize(100, 10))

	cont := container.New(&customLayout{}, label, label2, label3)

	window.SetContent(cont)

	window.ShowAndRun()
}
