package main

import "fmt"

func main() {
	array := [...]string{"hello", "world", "Hi!!"}
	fmt.Println(array)

	for i, item := range array {
		fmt.Printf("Index:%v\tValue:%v \n", i, item)
	}
}
