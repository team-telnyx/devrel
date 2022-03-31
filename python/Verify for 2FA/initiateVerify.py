import telnyx
import config

telnyx.api_key = config.api_key


initiateVerification = telnyx.Verification.sms(
  phone_number= config.phone_number,
  verify_profile_id= config.verify_profileid,
  timeout_secs= 300
)
verificationId = initiateVerification['id']
print(verificationId)



