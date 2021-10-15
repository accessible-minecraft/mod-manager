package main

import (
	"fmt"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"
)

func main() {
	app := app.New()
	window := app.NewWindow("Mod Manager")

	bu1 := enableDisableButton("Test 1", func() {
		fmt.Println("C Button")
	})
	bu1.Resize(fyne.NewSize(100, 30))

	bu2 := enableDisableButton("Test 2", func() {
		fmt.Println("C Button")
	})
	bu2.Resize(fyne.NewSize(100, 30))

	bu3 := enableDisableButton("Test 3", func() {
		fmt.Println("C Button")
	})
	bu3.Resize(fyne.NewSize(100, 30))

	cont := container.New(&customLayout{}, bu1, bu2, bu3)

	window.SetContent(cont)

	window.ShowAndRun()
}
