module.exports = function(recipient, text, context, cb) {

    const apiKey = context.webtask.secrets.TELNYX_API_KEY;
    const profileId = context.webtask.secrets.PROFILE_ID; 
    
    var request = require('request');
    
    var headers = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Authorization': 'Bearer ' + apiKey
    }
    
    var options1 = {
      'method': 'POST',
      'url': 'https://api.telnyx.com/v2/verify_profiles',
      headers,
      body: JSON.stringify({
        "name": "Auth0 profile",
        "sms": {
          "default_verification_timeout_secs": 300,
          "messaging_enabled": true,
          "messaging_template": "Hello, this is Telnyx, your verification code is: {code}."
        }
      })
    
    };
    request(options1, function (error, response) {
      if (error) throw new Error(error);
      console.log(response.body);
    });
    
    var options2 = {
      'method': 'POST',
      'url': 'https://api.telnyx.com/v2/verifications/sms',
      headers,
      body: JSON.stringify({
        "phone_number": recipient,
        "verify_profile_id": profileId
      })
    
    };
    request(options2, function (error, response) {
      if (error) throw new Error(error);
      console.log(response.body);
    });
    
      cb(null, {});
    };