API_KEY = ""
fromNumber = ""

function sendMessage(to, body) {
    var data = {
    'to': to,
    'from': fromNumber,
    'text': body
    };
  
    var options = {
      'method' : 'post',
      'payload' : JSON.stringify(data)
    };
  
    options.headers = {
      'Content-Type': 'application/json',
      "Accept": "application.json",
      "Authorization": "Bearer " + API_KEY
    }
  
    UrlFetchApp.fetch('https://api.telnyx.com/v2/messages', options);
  }
  
  function sendAll(){
    var sheet = SpreadsheetApp.getActiveSheet();
    var startRow = 2;
    var numRows = sheet.getLastRow() - 1;
    var dataRange = sheet.getRange(startRow, 1, numRows, 2);
    var data = dataRange.getValues();
  
    for (i in data){
      var row = data[i];
      try {
        response_data = sendMessage("+" + row[0], row[1]);
        status = "Sent Sucessfully";
      } catch(err) {
        Logger.log(err);
        status = "Error Sending";
      }
      sheet.getRange(startRow + Number(i), 3).setValue(status);
    }
  }
  
  function app(){
    sendAll();
  }