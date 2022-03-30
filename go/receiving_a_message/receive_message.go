package main

import (
	"fmt"
	"io"
	"log"
	"net/http"
	"os"
)

func handleWebhook(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("headers: %v\n", r.Header) // You can parse the response and get the text message

	_, err := io.Copy(os.Stdout, r.Body)
	if err != nil {
		log.Println(err)
		return
	}
}

func main() {
	log.Println("server started")
	http.HandleFunc("/webhooks", handleWebhook)
	log.Fatal(http.ListenAndServe(":8080", nil)) // Mention port '8080' in your tunneling tool (like Ngrok)
}
