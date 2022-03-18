import json
import websocket
import telnyx

def send_message(stock_text):    
    telnyx.api_key = "TELNYX_API_KEY"

    telnyx.Message.create(
    from_="TELNYX_PHONE_NUMBER",
    to="YOUR_PHONE_NUMBER",
    text=stock_text
    )
    print(stock_text)

def on_message(ws, message):
    json_message = json.loads(message)
    
    stock_price = json_message["data"][0]['p']
    stock_symbol = json_message["data"][0]['s']

    if stock_price < 160 or stock_price > 165:
        send_message("The stock price of "+stock_symbol+" is "+str(stock_price)+".")
        
def on_error(ws, error):
    print(error)

def on_close(ws):
    print("### closed ###")

def on_open(ws):
    ws.send('{"type":"subscribe","symbol":"AAPL"}')

if __name__ == "__main__":
    websocket.enableTrace(True)
    ws = websocket.WebSocketApp("wss://ws.finnhub.io?token=c8o6tlqad3iep4jeko50",
                              on_message = on_message,
                              on_error = on_error,
                              on_close = on_close)
    ws.on_open = on_open
    ws.run_forever()