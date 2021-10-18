package main

import (
	"fmt"
	"testing"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/test"
	"github.com/accessible-minecraft/mod-manager/gui"
	"github.com/stretchr/testify/assert"
)

func TestHomeContent(t *testing.T) {

	testApplication := &mainApplication{app: test.NewApp()}

	profileButton := gui.GetEnableDisableButton(testApplication.app, "Test", "test")
	profileButton.Resize(fyne.NewSize(150, 30))
	content := container.New(&gui.CustomLayout{}, profileButton)

	tempWindow := testApplication.app.NewWindow("Test")
	tempWindow.SetTitle("Test")
	tempWindow.SetContent(content)

	testApplication.window = tempWindow

	assert.Equal(t, content, testApplication.window.Content()) // This should be equal!!

	testApplication.setContentToHome()
	assert.Equal(t, content, testApplication.window.Content()) // This should not be equal!!
}

func TestCheckIfValidOrNot(t *testing.T) {
	fmt.Println(checkIfValidOrNot(getMinecraftDirectory()))
}

func TestSetMinecraftDirectory(t *testing.T) {
	ta := &mainApplication{app: test.NewApp()}
	ta.setMinecraftDirectory()
}
