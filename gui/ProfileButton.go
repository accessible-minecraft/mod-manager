package gui

import (
	"fmt"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/widget"
	htgotts "github.com/hegedustibor/htgo-tts"
)

func GetProfileButton(app fyne.App, label string) *ProfileButton {
	ret := &ProfileButton{}
	ret.ExtendBaseWidget(ret)

	ret.app = app

	ret.Label = label

	ret.RefreshText()
	ret.OnTapped = func() {
		fmt.Println("Changing Prefrence Here")
		app.Preferences().SetString("modmanager.currentprofile", "1.17")
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

func (m *ProfileButton) FocusGained() {
	// go EventListner(m.ListeningToKeys, m.Label)
	name := m.Text
	fmt.Println(name)
	speech := htgotts.Speech{Folder: "audio", Language: "en"}
	speech.Speak(name)
}

func (m *ProfileButton) FocusLost() {
}

func (m *ProfileButton) RefreshText() {
	m.Text = m.Label + ": " + m.app.Preferences().StringWithFallback("modmanager.currentprofile", "default")
}
