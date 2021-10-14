//go:build linux || darwin || windows
// +build linux darwin windows

package main

import (
	"crypto/md5"
	"encoding/hex"
	"fmt"
	"reflect"
	"time"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/app"
	"fyne.io/fyne/v2/container"
	"fyne.io/fyne/v2/widget"
	htgotts "github.com/hegedustibor/htgo-tts"
)

func main() {
	app := app.New()
	window := app.NewWindow("Mod Manager")

	bu1 := widget.NewButton("Test", func() {
		fmt.Println("clicked!")
	})
	bu1.Resize(fyne.NewSize(100, 30))

	bu2 := newMyButton("Test 2", func() {
		fmt.Println("C Button")
	})
	bu2.Resize(fyne.NewSize(100, 30))

	bu3 := widget.NewButton("Test 3", func() {
		fmt.Println("clicked!")
	})
	bu3.Resize(fyne.NewSize(100, 30))

	cont := container.New(&customLayout{}, bu1, bu2, bu3)
	fv := reflect.ValueOf(bu3).Elem().FieldByName("focused")

	go func(fv reflect.Value) {
		var name string = ""
		for {
			if fv.Bool() {
				name = bu3.Text
				fmt.Println(name)
				speech := htgotts.Speech{Folder: "audio", Language: "en"}
				// generatedHashName := generateHashName(name)
				// fileName := "audio/" + generatedHashName + ".mp3"
				// exec.Command("mplayer", fileName).Run()
				speech.Speak(name)
			}
			time.Sleep(time.Second * 5)
		}
	}(fv)
	window.SetContent(cont)

	window.ShowAndRun()
}
func generateHashName(name string) string {
	hash := md5.Sum([]byte(name))
	return fmt.Sprintf("%s_%s", "en", hex.EncodeToString(hash[:]))
}

type myButton struct {
	widget.Button
}

func (m *myButton) ocusGained() {
	fmt.Println("custom focus")
}

func newMyButton(label string, tapped func()) *myButton {
	ret := &myButton{}
	ret.ExtendBaseWidget(ret)
	ret.Text = label
	ret.OnTapped = tapped

	return ret
}
