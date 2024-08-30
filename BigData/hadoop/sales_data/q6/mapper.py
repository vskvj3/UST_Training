#!/usr/bin/python3
"""mapper.py"""
import sys
import datetime

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

for line in sys.stdin:

    line = line.strip().split(',')

    try:
        date = line[10].split('/')
        month, day, year = int(date[0]), int(date[1]), int(date[2])
        week = datetime.datetime(year, month, day).weekday()
        current_revenue = line[9]
    except:
        continue

    print('%s,%s' % (days[week], current_revenue))
