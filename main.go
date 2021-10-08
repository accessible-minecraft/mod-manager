package main

import "fmt"

func main() {

	fmt.Println("Prime Counter using Recursion")
	fmt.Println("Enter range:\t")
	var r int
	fmt.Scanln(&r)
	count := prime(2, r)

	fmt.Println("Total prime numbers till ", r, " are ", count)

}

func prime(current int, end int) int {
	if current > end {
		return 0
	} else {
		if isPrime(current) {
			return 1 + prime(current+1, end)
		}
		return prime(current+1, end)
	}
}

func isPrime(number int) bool {
	for i := 2; i <= number/2; i++ {
		if number%i == 0 {
			return false
		}
	}
	return true
}
