package main

import (
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"net/http"
	"os"
)

func main() {
	fmt.Println("JSON")

	url := "https://raw.githubusercontent.com/accessible-minecraft/accessibility-plus-extended/master/Accessibility%20Plus%20Extended/src/main/resources/fabric.mod.json"

	// Get the data
	resp, err := http.Get(url)
	check(err)
	defer resp.Body.Close()

	// Create the file
	out, err := os.Create("./temp.json")
	check(err)
	defer out.Close()

	// Write the body to file
	_, err = io.Copy(out, resp.Body)
	check(err)

	file, err := os.Open("./temp.json")
	check(err)
	defer file.Close()

	byteCode, _ := ioutil.ReadAll(file)

	var result map[string]interface{}
	json.Unmarshal(byteCode, &result)

	result["schemaVersion"] = 2.0

	fmt.Println(result["schemaVersion"])

	// Write the body to file
	b, _ := json.Marshal(&result)
	fileStat, _ := os.Lstat("./temp.json")
	err = ioutil.WriteFile("./temp.json", b, fileStat.Mode().Perm())
	check(err)

}

func check(err error) {
	if err != nil {
		panic(err)
	}
}
