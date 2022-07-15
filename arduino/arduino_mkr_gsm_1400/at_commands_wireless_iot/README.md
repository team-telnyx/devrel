# AT commands for Arduino MKR GSM 1400 using Telnyx Wireless SIM

![Telnyx](https://github.com/team-telnyx/devrel/blob/main/assets/img/logo-dark.png?raw=true)

AT is the short form of 'attention' in AT command. These commands are used for controlling modem.

## Hardware Requirment

- [Arduino MKR GSM 1400](https://docs.arduino.cc/hardware/mkr-gsm-1400)
- [Telnyx Wireless SIM](https://developers.telnyx.com/docs/v2/wireless/quickstarts/wireless-setup)
- 3.7 Volt (minimum 2500 mAH) LiPo battery
- GSM Antenna for Arduino MKR Board
- micro usb cable


## Software Requirment
- [Arduino IDE](https://www.arduino.cc/en/software)


## Steps
- Acquire Telnyx Wireless SIM and [activate](https://developers.telnyx.com/docs/v2/wireless/quickstarts/wireless-setup) it
- Connect battery and antenna to Arduino MKR GSM 1400
![mkr gsm 1400](https://docs.arduino.cc/static/f890641fd6709a56c71c103a43e800ba/ABX00018-pinout.png)
- Connect the Arduino board to your computer using micro usb cable
- Insert Telnyx SIM in the Arduino board
- Open Arduino IDE 
- Copy the below code and paste it in Arduion IDE.
- Save the code as "at_command_arduino"

```
// baud rate for both Serial ports
unsigned long baud = 115200;

void setup() {
  // reset the ublox module
  pinMode(GSM_RESETN, OUTPUT);
  digitalWrite(GSM_RESETN, HIGH);
  delay(100);
  digitalWrite(GSM_RESETN, LOW);

  Serial.begin(baud);
  SerialGSM.begin(baud);
}

void loop() {
  if (Serial.available()) {
    SerialGSM.write(Serial.read());
  }

  if (SerialGSM.available()) {
    Serial.write(Seria-lGSM.read());
  }
}

```
- Run the code and open `Serial Monitor`
- Make sure you have set the serial interface to terminate with carriage return and newline or the AT commands will not be interpreted by the modem.
- The baud of Serial Monitors should be `115200 baud`

![serial monitor]("/img/serial monitor setup.PNG")

- Pass the following AT commands

| Command | What does it do? |Sample Response |
| ------- | -------- | ------ |
| AT | Same thing as "Hey!" in real life. This is used to check if you are able to communicate with the device.|OK|
|AT+COPS=?|To see all available carriers. This command will also filter out carriers that are not compatible with the SIM card.|0, 1, "T-Mobile" or 0, 4, "AT&T"|
|AT+COPS = (#, #)|Check the current network. Obtain two #'s from the above command. For example, if you want to connect to T-Mobile, you would do AT+COPS=(0,1).|"T-Mobile" or "AT&T"|
|AT+CCID|Check the SIM ID IMEI NUMBER|OK|
|AT+CREG?|Network Registration Status. You can run the AT+CREG=? for available flags.(#, "Current Network Name", PLMN #)|OK|
|AT+COPS = 1, 0 "Carrier Name"|Manually connect to a network. This is also known as PLMN selection.|If all went well, you should get an OK response. If there is an error, it will reply back with CME ERROR. The error usually occurs if your provider's SIM does not support the carrier you are connecting to. Please check with your wireless provider on the list of providers that is supported in your area.|
|AT+CGMR|Firmware Version Identification|23.60|
|AT+CIMI| International mobile subscriber identification| 454015789003805|
|AT+GCAP|Request complete capabilities list|+CGSM|
|AT+CLAC|List all available AT commands|-|


- For more AT commands and information, check the [official](https://content.u-blox.com/sites/default/files/u-blox-CEL_ATCommands_UBX-13002752.pdf) document of U-blox.