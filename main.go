package main

import (
	"fmt"
	"io"
	"net/http"
	"os"
)

func main() {
	fmt.Println("JSON")

	url := "https://github.com/accessible-minecraft/accessibility-plus-extended/raw/master/accessibility-plus-extended-1.16-curseforge-v1.6.4.jar"

	// Get the data
	resp, err := http.Get(url)
	check(err)
	defer resp.Body.Close()

	// Create the file
	out, err := os.Create("./ape.jar")
	check(err)
	defer out.Close()

	// Write the body to file
	_, err = io.Copy(out, resp.Body)
	check(err)

}

func check(err error) {
	if err != nil {
		panic(err)
	}
}
