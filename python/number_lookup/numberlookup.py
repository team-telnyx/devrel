import telnyx
import config

telnyx.api_key = config.api_key
print(telnyx.api_key)

lookup = telnyx.NumberLookup.retrieve(config.phone_number)
if(lookup.portability.line_type == 'mobile'):
    print('This is a valid mobile phone number')
else:
    print('This phone number is not a mobile phone number and is of type:',lookup.portability.line_type)