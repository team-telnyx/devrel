import json
import paho.mqtt.client as paho
import ssl
from time import sleep
from random import uniform

connflag = False

def on_connect(client, userdata, flags, rc):
    global connflag
    connflag = True
    print("Connection returned result: " + str(rc) )

def on_message(client, userdata, msg):
    print(msg.topic+" "+str(msg.payload))

mqttc = paho.Client()

mqttc.on_connect = on_connect
mqttc.on_message = on_message

awshost = "AWS_END_POINT"
awsport = 8883
clientId = "CLIENT_ID"
thingName = "THING_NAME"
caPath = "root-CA.crt"
certPath = "THING_NAME.cert.pem"
keyPath = "THING_NAME.private.key"

mqttc.tls_set(caPath, certfile=certPath, keyfile=keyPath, cert_reqs=ssl.CERT_REQUIRED, tls_version=ssl.PROTOCOL_TLSv1_2, ciphers=None)

mqttc.connect(awshost, awsport, keepalive=60)

mqttc.loop_start()

while 1==1:
    sleep(10)
    if connflag == True:
        tempreading = uniform(20.0,25.0)
        payload = json.dumps({'temperature':tempreading})
        mqttc.publish("iot", payload, qos=0)
        print("msg sent: " + "%s" % payload )
    else:
        print("waiting for connection...")