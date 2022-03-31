import telnyx

telnyx.api_key = "TELNYX_API_KEY"

number_dict = {}

# List all the phone numbers with messaging settings
phone_number_data = telnyx.MessagingPhoneNumber.list(page={"number":1, "size":100})

#Iterate through the JSON response and extract ID and Phone number of P2P and A2P eligible numbers
for i in range(len(phone_number_data['data'])):
    # Find the numbers eligible for both, P2P and A2P
    if phone_number_data['data'][i]['eligible_messaging_products'] == ['A2P', 'P2P']:
        phone_numbers   = phone_number_data['data'][i]['phone_number']
        id              = phone_number_data['data'][i]['id']
        #Store phone number and Id as key-value pair in a Dictionary 
        number_dict.update({phone_numbers:id})
        #Check the traffic type of numbers
        traffic_type = phone_number_data['data'][i]["traffic_type"]
        print("The traffic type before change of phone number " + str(phone_numbers) + " is "+str(traffic_type))



# Change the traffic type of all the eligible numbers 
for key, value in number_dict.items():
    eligible_phone_number = telnyx.PhoneNumber.retrieve(value)
    messaging_resource = eligible_phone_number.messaging()
    messaging_resource.messaging_product = "P2P"
    messaging_resource.save()

#Check the traffic type of numbers after the change
for i in range(len(phone_number_data['data'])):
    # Find the numbers eligible for both, P2P and A2P
    if phone_number_data['data'][i]['eligible_messaging_products'] == ['A2P', 'P2P']:
        phone_numbers_after   = phone_number_data['data'][i]['phone_number']
        #Check the traffic type of numbers
        traffic_type_after = phone_number_data['data'][i]["traffic_type"]
        print("The traffic type after change of phone number " + str(phone_numbers_after) + " is "+str(traffic_type_after))
