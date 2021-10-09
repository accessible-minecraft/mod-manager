package main

import "fmt"

func main() {
	fmt.Println("Structs")

	e1 := &Employee{
		Name:    "Shoaib",
		SurName: "Khan",
		age:     18,
	}

	fmt.Println("Name:\t", (*e1).Name)
	fmt.Println("Sur-Name:\t", (*e1).SurName)
	fmt.Println("Age:\t", (*e1).age)
	(*e1).AddAge()
	fmt.Println("Age:\t", (*e1).age)

}

type Employee struct {
	Name, SurName string
	age           int
}

func (obj *Employee) AddAge() {
	obj.age += 5
}
