package gui

import (
	"encoding/json"
	"io/ioutil"
	"os"

	"fyne.io/fyne/v2"
	"fyne.io/fyne/v2/widget"
)

func GetEnableDisableButton(app fyne.App, label string, key string) *EDButton {
	ret := &EDButton{}
	ret.ExtendBaseWidget(ret)
	ret.JsonKey = key
	ret.Label = label
	ret.RefreshText()
	ret.OnTapped = func() { changeState(ret) }
	ret.ListeningToKeys = make(chan bool)

	return ret
}

type EDButton struct {
	app fyne.App
	widget.Button
	JsonKey         string
	Label           string
	ListeningToKeys chan bool
}

func changeState(m *EDButton) {
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

func (m *EDButton) RefreshText() {
	m.Text = m.Label + ": " + getState(m.JsonKey)
}
