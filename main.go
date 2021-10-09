package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"os"
)

func main() {
	fmt.Println("JSON")

	file, err := os.Open("/home/shoaib/.minecraft/home/Fabric-1.17/config/easylife/config.json")
	if err != nil {
		panic(err)
	}

	defer file.Close()

	byteCode, err := ioutil.ReadAll(file)
	if err != nil {
		panic(err)
	}

	var result map[string]interface{}
	json.Unmarshal(byteCode, &result)
	fmt.Println(result["player_warnings_timeout"])
}
