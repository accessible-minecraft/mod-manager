package main

import (
	"fmt"
)

func main() {
	fmt.Println("Structs")

	e1 := *NewEmployee1("shoaib", "khan")
	e2 := *NewEmployee2("yusuf", "khan")
	fmt.Println(e1)
	ChangeAge(e1)
	fmt.Println(e2)
	ChangeAge(e2)
}

func ChangeAge(obj AgeInterface) {
	fmt.Println(obj.Age())
}

type AgeInterface interface {
	Age() int
}

func NewEmployee1(name string, surName string) *Employee1 {
	e := Employee1{
		Name:    name,
		SurName: surName,
		age:     15,
	}
	return &e
}

func NewEmployee2(name string, surName string) *Employee2 {
	e := Employee2{
		Name:    name,
		SurName: surName,
		age:     18,
	}
	return &e
}

type Employee1 struct {
	Name, SurName string
	age           int
}

func (obj Employee1) Age() int {
	return obj.age + 5
}

type Employee2 struct {
	Name, SurName string
	age           int
}

func (obj Employee2) Age() int {
	return obj.age - 5
}
