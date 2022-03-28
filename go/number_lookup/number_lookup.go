package main

import (
	"io/ioutil"
	"log"
	"net/http"
)

func main() {
	url := "https://api.telnyx.com/v2/number_lookup/PHONE_NUMBER" // Example - +18665552368
	// url := "https://api.telnyx.com/v2/number_lookup/PHONE_NUMBER?type=carrier&type=caller-name"
	method := "GET"
	client := http.Client{}

	req, err := http.NewRequest(method, url, nil)

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
