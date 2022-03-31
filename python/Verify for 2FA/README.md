# 2FA using Verify
<div align="center">

## _Make use of Verify API for 2FA using SMS with custom message template_

![Telnyx](https://github.com/team-telnyx/devrel/blob/main/assets/img/logo-dark.png?raw=true)
</div>

The code lets developer use Telnyx Verify API for implementing 2FA
- [Config file to store API_KEY and other values](#config-file)
- [Setting up a verify profile](#create-a-verify-profile)
- [Updating the verify profile with custom message template](#updating-the-verify-profile-with-custom-sms-template)
- [Initiate verification](#initiate-verification)
- [Complete verification](#complete-verification)

### Config File
One method for securing our confidential data like `API_KEY` and other values is to create variables for them and store them locally, for example in a config.py file and do not upload it to our public repository. Our config.py file may look like this initially but we keep adding variables as we go:
```
api_key = "[YOUR API KEY]"
```
### Create a Verify Profile
Let's create a verify profile with minimum required details. Complete code is in [createVerifyProfile.py](createVerifyProfile.py)
```py
createVerifyProfile = telnyx.VerifyProfile.create
(
name = 'Python Verify',
default_timeout_secs = 60,
messaging_enabled = True
)
```
Assigning the result to a variable makes it easy to access the verify profile id after creating the profile. 
##### *Updating the verify profile with custom sms template*
`{code}` is used to template the actual verification code
```py
verifyProfileId = createVerifyProfile['id']
print(verifyProfileId)
verifyProfile = telnyx.VerifyProfile.retrieve(verifyProfileId)
verifyProfile['sms']['messaging_template'] = 'Hello Telnyx Dev, {code} is your verification code to confirm you are at Telnyx Demo today'
verifyProfile = verifyProfile.save()
```

Once we got the verify profile id from the above code, let us update the config.py file we created before. 
Also, let us add a phone number we would want to use for 2FA
It may look like this:
```
api_key = '[YOUR API KEY]
verify_profileid = '[YOUR VERIFY PROFILE ID]'
phone_number = '[PHONE NUMBER FOR 2FA]'
```

### Initiate verification
For initiating verification, we need the phone_number, `verify_profile_id` and if you want to modify the default timeout, you can assign/update it with `timeout_secs`.
Complete code is in [initiateVerify.py](initiateVerify.py)
```py
  
initiateVerification = telnyx.Verification.sms
(
phone_number= config.phone_number,
verify_profile_id= config.verify_profileid,
timeout_secs= 60
)

verificationId = initiateVerification['id']
print(verificationId)
```

Once we get the verification id, let's add it to the config.py file. Updated config.py should look as follows:
```
api_key = '[YOUR API KEY]
verify_profileid = '[YOUR VERIFY PROFILE ID]'
phone_number = '[PHONE NUMBER FOR 2FA]'
verification_id = '[VERIFICATION ID GENERATED AFTER INITIATING VERIFICATION]'
```

### Complete Verification
For verifying, we use `verify_by_phone_number` method from `verification` class. 
Full code is available in [verify.py](verify.py)

```py
verificationId = config.verification_id
verification = telnyx.Verification.retrieve(verificationId)

  
code = "12345" #Replace with the code you get to the number
verify_response = verification.verify_by_phone_number(
code=code,
phone_number=config.phone_number)

if verify_response.data.response_code == "accepted":
print("Verification with 2FA is successful!")
else:
print("There was an issue verifying: {}".format(verify_response["data"]))
```