package main

import "fmt"

func main() {

	fmt.Println("multi return")

	i, result := multiReturn()

	fmt.Println(i)
	fmt.Println(result)

}

func multiReturn() (int, string) {
	return 1, "shoaib"
}
