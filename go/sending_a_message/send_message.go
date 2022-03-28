package main

import (
	"bytes"
	"io/ioutil"
	"log"
	"net/http"
)

func main() {
	url := "https://api.telnyx.com/v2/messages"
	method := "POST"
	client := http.Client{}

	var jsonStr = []byte(`{"from":"YOUR_TELNYX_NUMBER","to":"RECIPIENT_PHONE_NUMBER", "text":"Hi, this is Golang!"}`)

	req, err := http.NewRequest(method, url, bytes.NewBuffer(jsonStr))

	if err != nil {
		//handle error
	}

	req.Header = http.Header{
		"Content-Type":  []string{"application/json"},
		"Accept":        []string{"application/json"},
		"Authorization": []string{"Bearer TELNYX_API_KEY"},
	}

	res, err := client.Do(req)
	if err != nil {
		//Handle Error
	}

	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		//Handle Error
	}

	sb := string(body)
	log.Printf(sb)
}
