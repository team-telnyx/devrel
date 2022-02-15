<div align="center">

# Messaging IoT Device state using AWS, Python and Telnyx

![Telnyx](/img/logo-dark.png)

An IoT use case where an IoT device sends data to the AWS cloud. And notifies anomaly to concerned individuals using Telnyx messaging.

</div>

## Prerequisite
 
 * [Telnyx Account](https://telnyx.com/sign-up?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link)
 * [Telnyx Phone Number](https://portal.telnyx.com/#/app/numbers/my-numbers?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) enabled with:
    * [Telnyx Messaging Profile](https://portal.telnyx.com/#/app/messaging)
 * [Python](https://nodejs.org/en/) installed
 * [AWS](https://aws.amazon.com/) account
 * Raspberry Pi (optional)

## Architecture

<div align="center">

![IoT](/img/architecture.PNG)

</div>

1. Raspberry Pi (a Python code) sends MQTT data to AWS IoT 
2. AWS IoT rule forwards data to AWS Lambda
3. AWS Lambda function (Python code) sends the message to your phone when a certain condition is satisfied 
  
## Steps

The following values need to be replaced in [publishData.py](https://github.com/vidhanbhonsle/Telnyx_messaging_AWS_IoT_Lambda/blob/main/publishData.py) and [lambda_function.py](https://github.com/vidhanbhonsle/Telnyx_messaging_AWS_IoT_Lambda/blob/main/lambda_function.py) files

| Variable               | Description                                                                                                                                              |
|:-----------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `TELNYX_API_KEY`       | Your [Telnyx API Key](https://portal.telnyx.com/#/app/api-keys?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link)              |
| `TELNYX_PHONE_NUMBER`    | Your [Telnyx number](https://portal.telnyx.com/#/app/numbers/my-numbers?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) |
| `YOUR_PHONE_NUMBER`      | Your personal phone number                                                                                                   |
| `AWS_END_POINT`             | Endpoint for communication with Thing using MQTT(works for HTTP too). Can be found under **`settings`** menu of **`AWS IoT`**                                                                                              |
| `CLIENT_ID` | The ID of the Thing created                          |
| `THING_NAME` | The name of the Thing |
| `THING_NAME.cert.pem` | Downloaded from the connection kit |
| `THING_NAME.private.key` | Downloaded from the connection kit |

The values can be acquired with the help of following steps -

 ### Step 1: Telnyx Setup 
Sign up for Telnyx account, obtain a number with SMS capabilities and configure the number for messaging.
> If you already have a Telnyx number with messaging enabled, move to `Step 2` 
<details>
<summary><strong>Steps to follow</strong> (click to expand)</summary><p>

 1. Sign up for Telnyx account
    > Set up a developer account with [Telnyx](https://telnyx.com/sign-up?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link).

 2. Obtain a number with SMS capabilities for auto-responder app
    > After creating an account and signing in, you need to [acquire a number](https://portal.telnyx.com/#/app/numbers/my-numbers?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) for the application. Search for a number by selecting your preferred 'Region' or 'Area Code'.
    
    > Make sure that the number supports SMS feature(Very Important!) as it will be used by our application.
 
 3. Create a messaging profile
    > Next create a [messaging profile](https://portal.telnyx.com/#/app/messaging?utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) by clicking on "Add new profile" and provide a suitable profile name to it(you do not need to provide any other detail for now).

 4. Configure the number for messaging
    > Go to the [numbers](https://portal.telnyx.com/#/app/numbers/my-numbers??utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) page, look for the number you created and set the number's `Messaging Profile` to the profile you created in the previous step. 
    
    <details>
    <summary>What if the Telnyx number is an international number for a User</summary>
    <br>    
    
    > If you want to send the message to a Telnyx number which is not in the country where you are, then you need to click on the 'Routing' option.
     <img src='./img/routing_click_red.png' width="800"/>
    
    > After clicking on 'Routing', a dialog box will open. In there, select the traffic type as "P2P" to allow International Inbound and Outbound SMS deliverability. And do not forget to save the changes!  

     <img src='./img/routing_selected.png' width="800"/> 
    </details>
    
 5. Acquire Telnyx API key
    > Go to the [API Keys](https://portal.telnyx.com/#/app/api-keys??utm_source=referral&utm_medium=github_referral&utm_campaign=cross-site-link) page and copy the API Key for the future steps. Incase there is no API Key, then create one.
</p></details>

___

### Step 2: AWS IoT and Lambda Setup
Before moving forward, make sure you have acquired `TELNYX_API_KEY`, `TELNYX_PHONE_NUMBER` and have a messaging profile associated with `TELNYX_PHONE_NUMBER`.

<details>
<summary><strong>Steps to follow</strong> (click to expand)</summary><p>

 1. Create AWS IoT Thing and download connection kit for Python
    * You can find steps [here](https://www.youtube.com/watch?v=6w9a6y_-T2o)
        * You need `root-CA.crt`, `THING_NAME.cert.pem` and `THING_NAME.private.key` from above
    
 2. Create [Lambda](https://aws.amazon.com/lambda/) function
    * Follow the steps mentioned [here]() for creating Python based lambda function.
    * Copy and paste the code from [lambda_function.py](https://github.com/vidhanbhonsle/Telnyx_messaging_AWS_IoT_Lambda/blob/main/lambda_function.py) file into the AWS consoles `lambda_function.py` file
    * Substitute below mentioned values with the values acquired from `Step One` - 
        * `TELNYX_PHONE_NUMBER`, 
        * `YOUR_PHONE_NUMBER` and 
        * `TELNYX_API_KEY` 
    * You can test the function with test feature in the menu of Lambda.
 
 3. Connect AWS IoT rule with AWS Lambda
    * Create a rule
        * Go to the AWS IoT Core Console, and from the left menu, click on the ‘Act’ dropdown, and click on ‘Rules’. Click on 'Create'
        * Provide a suitable name and description

        ![Rule_name_desc](/img/rule_name_desc.PNG)
        
        * Enter rule query
            ``` mysql
                SELECT * from "iot"    
            ```

        ![Rule_query](/img/rule_query.PNG)

        * Next click on `Add Action`.
        
        ![Rule_add_action](/img/rule_add_action.PNG)
        
        * Out of the list of actions that opens up, select ‘Send a message to a Lambda function’, and then click on ‘Configure action’. 

        ![Rule_add_lambda](/img/rule_add_lambda.PNG)

        * Select the lambda function you created and click `Add Action`.

        * Finally, click on `Create Rule` to complete the step

        * Lastly, enable the rule

        ![Rule_enable](/img/rule_enable.PNG)
</p></details>

___

### Step 3: Python code for Raspberry Pi (IoT Device)
Once you have completed AWS IoT rule and Lambda function, you need to work on sending the IoT data to AWS part. 
* `You can run this code on any machine with Python installed`

<details>
<summary><strong>Steps to follow</strong> (click to expand)</summary><p>

 1. Copy and paste the code from [publishData.py](https://github.com/vidhanbhonsle/Telnyx_messaging_AWS_IoT_Lambda/blob/main/publishData.py) into your own Python file (name it as you wish!)
    * Keep the files acquired from `Step One` in the same location as your Python file. These are the files -
        * `THING_NAME.cert.pem` file
        * `THING_NAME.private.key` file
        * `root-CA.crt` file
    * Substitute the following values with your own in Python code 
        * `AWS_END_POINT` 
        * `CLIENT_ID`
        * `THING_NAME`
        * `THING_NAME.cert.pem`
        * `THING_NAME.private.key`
</p></details>

___

<div align="center">
 
### `Run the Python code and you will get a message on your number if the value exceeds 24!`

</div> 
