package main

import (
	"fyne.io/fyne/v2/widget"
	htgotts "github.com/hegedustibor/htgo-tts"
)

type myButton struct {
	widget.Button
	Focused, Hovered bool
}

func (m *myButton) FocusGained() {
	name := m.Text
	speech := htgotts.Speech{Folder: "audio", Language: "en"}
	speech.Speak(name)
}

func enableDisableButton(label string, tapped func()) *myButton {
	ret := &myButton{}
	ret.ExtendBaseWidget(ret)
	ret.Text = label
	ret.OnTapped = tapped

	return ret
}
