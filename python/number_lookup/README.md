<div align="center">

# Number Lookup: Determine the type of a phone number

![Telnyx](https://github.com/team-telnyx/devrel/blob/main/assets/img/logo-dark.png?raw=true)

The code lets developer use Telnyx Number Lookup API to determine if a number is an actual mobile number or of any other type like VoIP, fixed etc...
</div>

### Config File
One method for securing our confidential data like `API_KEY` and other values is to create variables for them and store them locally, for example in a config.py file and do not upload it to our public repository. Our config.py file may look like this:
```
api_key = '[YOUR API KEY]'
phone_number = '[PHONE NUMBER TO LOOKUP]'
```
Later import this config file in actual code file. 

Actuual code for number lookup is in [numberlookup.py](numberlookup.py)

