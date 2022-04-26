from flask import Flask, request
import RPi.GPIO as GPIO

LED_PIN = 17
GPIO.setmode(GPIO.BCM)
GPIO.setup(LED_PIN, GPIO.OUT)

app = Flask(__name__)

@app.route('/webhooks', methods=['POST'])

def webhooks():    
    payload = request.json['data']['payload']
    if payload['direction'] == 'inbound':
        takeAction(payload)
    return 'success', 200

def takeAction(payload):
    incomingText    = payload['text']
    if incomingText == "ON":
        GPIO.output(LED_PIN, GPIO.HIGH)
        print(incomingText)
    elif incomingText == "OFF":
        GPIO.output(LED_PIN, GPIO.LOW)
        print(incomingText)
    else:
        print("You have entered "+incomingText+".")
if __name__ =="__main__":
    app.run(port=5000)