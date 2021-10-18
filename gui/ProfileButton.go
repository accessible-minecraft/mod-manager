package gui

import (
	"fmt"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/widget"
)

func GetProfileButton(app fyne.App, label string) *ProfileButton {
	ret := &ProfileButton{}
	ret.ExtendBaseWidget(ret)

	ret.app = app

	ret.Label = label

	ret.RefreshText()
	ret.OnTapped = func() {
		fmt.Println("Changing Prefrence Here")
		ret.RefreshText()
	}
	ret.ListeningToKeys = make(chan bool)

	return ret
}

type ProfileButton struct {
	app fyne.App
	widget.Button
	Label           string
	ListeningToKeys chan bool
}

func (m *ProfileButton) RefreshText() {
	m.Text = m.Label + ": " + m.app.Preferences().StringWithFallback("modmanager.currentprofile", "default")
}
