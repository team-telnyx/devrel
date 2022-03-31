package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
	"strings"
)

func main() {

	endpoint := "https://api.telnyx.com/v2/faxes"
	method := "POST"

	data := url.Values{}
	data.Set("media_url", "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf") //The URL of the PDF used for the fax's media
	data.Set("connection_id", "YOUR FAX APP ID")                                                     //Acquire from Fax app
	data.Set("to", "RECIPIENT_PHONE_NUMBER")
	data.Set("from", "YOUR_TELNYX_NUMBER0")

	client := &http.Client{}

	req, err := http.NewRequest(method, endpoint, strings.NewReader(data.Encode()))

	if err != nil {
		fmt.Println(err)
		return
	}

	req.Header = http.Header{
		"Authorization": []string{"Bearer TELNYX_API_KEY"},
		"Content-Type":  []string{"application/x-www-form-urlencoded"},
	}

	res, err := client.Do(req)
	if err != nil {
		fmt.Println(err)
		return
	}

	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(string(body))
}
