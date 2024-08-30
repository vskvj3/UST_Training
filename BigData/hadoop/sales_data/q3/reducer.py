#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_city = None
current_payments = defaultdict(int)
city = None

for line in sys.stdin:

    line = line.strip()

    city, payment = line.split(',', 1)
    if city == "City":
        continue

    if current_city == city:
        current_payments[payment] += 1
    else:
        if current_city:

            print("%s\t%s" % (current_city, max(current_payments, key=current_payments.get)))

        current_city = city
        current_payments = defaultdict(int)

if current_city == city:
    print("%s\t%s" % (current_city, max(current_payments, key=current_payments.get)))

