package main

import (
	"fmt"
	"os"
	"strings"
)

func main() {
	homedir, err := os.UserHomeDir()
	check(err)

	out, err := os.ReadDir(homedir + "/.minecraft/mods")
	check(err)

	for _, v := range out {
		if strings.Contains(v.Name(), "accessibility-plus-extended") {
			fmt.Println("A P Extended present")
		}
	}

}

func check(err error) {
	if err != nil {
		panic(err)
	}
}
