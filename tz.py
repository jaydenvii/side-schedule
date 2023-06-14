import pytz
import datetime
 
format = "%Y-%m-%d %H:%M:%S"
 
timezones = pytz.all_timezones

user_timezone = None
for timezone in timezones:
    now_local = datetime.datetime.now().strftime(format)
    now_tz = datetime.datetime.now(pytz.timezone('UTC')).astimezone(pytz.timezone(timezone)).strftime(format)
    if now_local == now_tz:
        user_timezone = timezone
        break

tzTXT = open("tz.txt", "w")

tzTXT.write(user_timezone)

tzTXT.close()
