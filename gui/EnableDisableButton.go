package gui

import (
	"encoding/json"
	"io/ioutil"
	"os"

	"fyne.io/fyne/v2/widget"
	htgotts "github.com/hegedustibor/htgo-tts"
)

type myButton struct {
	widget.Button
	JsonKey string
	Label   string
}

func (m *myButton) FocusGained() {
	name := m.Text
	speech := htgotts.Speech{Folder: "audio", Language: "en"}
	speech.Speak(name)
}

func EnableDisableButton(label string, key string) *myButton {
	ret := &myButton{}
	ret.ExtendBaseWidget(ret)
	ret.JsonKey = key
	ret.Label = label
	ret.RefreshText()
	ret.OnTapped = func() { changeState(ret) }

	return ret
}

func changeState(m *myButton) {
	file, err := os.Open("test.json")
	checkError(err)
	defer file.Close()

	byteCode, err := ioutil.ReadAll(file)
	if err != nil {
		panic(err)
	}

	var result map[string]interface{}
	json.Unmarshal(byteCode, &result)

	if result[m.JsonKey].(bool) {
		result[m.JsonKey] = false
	} else {
		result[m.JsonKey] = true
	}

	newByteData, err := json.Marshal(&result)
	checkError(err)

	fileStat, err := os.Lstat("test.json")
	checkError(err)

	err = ioutil.WriteFile("test.json", newByteData, fileStat.Mode().Perm())
	checkError(err)

	m.RefreshText()
}

func getState(key string) string {
	file, err := os.Open("test.json")
	checkError(err)
	defer file.Close()

	byteCode, err := ioutil.ReadAll(file)
	if err != nil {
		panic(err)
	}

	var result map[string]interface{}
	json.Unmarshal(byteCode, &result)

	if result[key].(bool) {
		return "enabled"
	}
	return "disabled"
}

func checkError(err error) {
	if err != nil {
		panic(err)
	}
}

func (m *myButton) RefreshText() {
	m.Text = m.Label + ": " + getState(m.JsonKey)
}
