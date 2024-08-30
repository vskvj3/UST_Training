#!/usr/bin/python3
"""mapper.py"""
import sys
from datetime import datetime

for line in sys.stdin:

    line = line.strip().split(',')

    try:
        date = line[10]
        day_of_week = datetime.strptime(date, "%m/%d/%Y").strftime("%Y-%U")
        quantity = line[7]
        product_line = line[5]
    except:
        continue

    print('%s,%s,%s' % (product_line, day_of_week, quantity))
