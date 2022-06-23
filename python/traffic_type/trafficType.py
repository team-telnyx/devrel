import telnyx
import config
#Make Sure you replace the values of api_key and id in the config file based on the instructions in README file. 
telnyx.api_key = config.api_key
print(telnyx.api_key)

lookup = telnyx.MessagingPhoneNumber.retrieve(config.id)

if("P2P" in lookup.eligible_messaging_products):
    print('This is a  P2P supported phone number')
else:
    print('This phone number does not support P2P and has eligibility for:',lookup.eligible_messaging_products)

