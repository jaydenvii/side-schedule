import openai
import datetime

with open("assets/API_KEY.txt", "r") as file:
    openai.api_key = file.read()

with open("user_schedule.txt", "r") as file:
    text = file.read()

with open("tz.txt", "r") as file:
    timeZone = file.read()

with open("assets/emailaddress.txt", "r") as file:
    email_address = file.read()

chatLog = [
    {
        "role": "user",
        "content": "You are a well organized individual that is juggling a small business/side hustle alongside full-time occupation. Create a schedule for today with the following events. DO NOT include any other events other than the ones listed. If an event has a specific start and end, you must schedule it at those times. Events should not be put in the order they are given, but put in a smart and logical order that spreads out tasks. Again, DO NOT include any other events other than the ones listed. The events are:"
    },
    {
        "role": "user",
        "content": text
    },
    {
        "role": "user",
        "content": 'Format this info into a .json file with multiple entries (i.e., [{}, {}]) that can be used in the Google Calendar API inside a json code block. The .json should just have summary, start, end, attendees, and colorId. Generate it in a code block without any additional text.'
    },
    {
        "role": "user",
        "content": 'The date today is: '+datetime.datetime.today().strftime('%Y-%m-%d')+', and the time zone is: '+timeZone+'. It is of utmost importance that the times of events NEVER overlap. Try to leave 15 minute gaps between events. Again, there should never be two events happening at the same time. Your email (which should be included in attendees) is '+email_address
    }
    ]

response = openai.ChatCompletion.create(
    model = "gpt-3.5-turbo",
    messages = chatLog
)

assistantResponse = response["choices"][0]["message"]["content"]

events = assistantResponse.strip().replace("```json", "").replace("```python", "").replace("`", "")

eventsJSON = open("events.json", "w")

eventsJSON.write(events)

eventsJSON.close()
