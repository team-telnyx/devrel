from subprocess import call
import telnyx
import config

telnyx.api_key = config.api_key
print(telnyx.api_key)

#Create a Verify profile
createVerifyProfile = telnyx.VerifyProfile.create(
  name = "Python Verify",
  default_timeout_secs = 300,
  messaging_enabled = True
)

#Update verify Profile with custom messaging template, call template
verifyProfileId = createVerifyProfile['id']
print(verifyProfileId)
verifyProfile = telnyx.VerifyProfile.retrieve(verifyProfileId)
verifyProfile['sms']['messaging_template'] = 'Hello Telnyx Dev, {code} is your verification code to confirm you are at Telnyx Demo today'
verifyProfile = verifyProfile.save()