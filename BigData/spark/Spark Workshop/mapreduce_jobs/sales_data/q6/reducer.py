#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_week = None
current_revenue = 0
days = []

for line in sys.stdin:

    line = line.strip()

    week, gross_income = line.split(',', 1)

    try:
        gross_income = float(gross_income)
    except:
        continue

    if current_week == week:
        current_revenue += gross_income
    else:
        if current_week:
            days.append((current_week, current_revenue))

        current_week = week
        current_revenue = gross_income

if current_week == week:
    days.append((current_week, current_revenue))

print("day: %s, revenue: %.2f" % max(days, key=lambda x: x[1]))


