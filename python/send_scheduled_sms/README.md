<div align="center">

# Send scheduled SMS with Python

![Telnyx](https://github.com/team-telnyx/devrel/blob/main/assets/img/logo-dark.png?raw=true)

The Python code lets developer schedule a message to be sent in the future.
</div>

## Using Schedule library
[Schedule](https://pypi.org/project/schedule/), is a lightweight Python library which helps developer to schedule jobs easily.

### Usage
```sh
    pip install schedule 
   ```
<br>

Replace `TELNYX_API_KEY`, `YOUR_TELNYX_NUMBER` and `RECIPIENT_PHONE_NUMBER` with your values
- `TELNYX_API_KEY`and `YOUR_TELNYX_NUMBER` to be acquired from portal.telnyx.com
- `RECIPIENT_PHONE_NUMBER` is your number

<br>

```sh
    import schedule
    import time
    import telnyx

    telnyx.api_key = "TELNYX_API_KEY"

    your_telnyx_number = "YOUR_TELNYX_NUMBER"
    destination_number = "RECIPIENT_PHONE_NUMBER"

    def job():
        telnyx.Message.create(
            from_=your_telnyx_number,
            to=destination_number,
            text="Hello, this is a test message!",
        )
        return schedule.CancelJob

    # Run job every 5 second/minute/hour/day/week,
    # Starting 5 second/minute/hour/day/week from now
    schedule.every(5).seconds.do(job)
    # schedule.every(5).minutes.do(job)
    # schedule.every(5).hours.do(job)
    # schedule.every(5).days.do(job)
    # schedule.every(5).weeks.do(job)

    # Run job every minute at the 10th second
    # schedule.every().minute.at(":10").do(job)

    # Run job every hour at the 15th minute
    # schedule.every().hour.at(":15").do(job)

    # Run jobs every 5th hour, 20 minutes and 30 seconds in.
    # If current time is 02:00, first execution is at 06:20:30
    # schedule.every(5).hours.at("20:30").do(job)

    # Run job every day at specific HH:MM and next HH:MM:SS
    # schedule.every().day.at("10:30").do(job)
    # schedule.every().day.at("10:30:42").do(job)

    # Run job on a specific day of the week
    # schedule.every().monday.do(job)
    # schedule.every().wednesday.at("13:15").do(job)
    # schedule.every().minute.at(":17").do(job)

    while True:
        schedule.run_pending()
        time.sleep(1) 
   ```

   The Schedule library works great for repetitive task like running a Python function preodically at pre-determined intervals. 

## Using datetime library
[datetime](https://docs.python.org/3/library/datetime.html) is a module to manipulate date and time in Python

### Usage

Replace `TELNYX_API_KEY`, `YOUR_TELNYX_NUMBER` and `RECIPIENT_PHONE_NUMBER` with your values
- `TELNYX_API_KEY`and `YOUR_TELNYX_NUMBER` to be acquired from portal.telnyx.com
- `RECIPIENT_PHONE_NUMBER` is your number

<br>
```
    import datetime
    import telnyx

    telnyx.api_key = "TELNYX_API_KEY"

    your_telnyx_number = "YOUR_TELNYX_NUMBER"
    destination_number = "RECIPIENT_PHONE_NUMBER"

    while True:
        t = datetime.datetime.now()
        if t.hour == 19 and t.minute == 2:
            telnyx.Message.create(
            from_=your_telnyx_number,
            to=destination_number,
            text="Hello, this is a test message!",
            )
            break
```