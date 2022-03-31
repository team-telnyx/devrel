import telnyx
import config

telnyx.api_key = config.api_key
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