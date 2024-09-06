#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_product = None
weekly_sales = defaultdict(int)
product = None

for line in sys.stdin:

    line = line.strip()

    product, week, quantity = line.split(',')

#    try:
    quantity = int(quantity)
        #week = int(week)
 #   except:
 #       continue
#
    if current_product == product:
        weekly_sales[week] += quantity
    else:
        if current_product:
            weeks = sorted(weekly_sales.keys())
            for i in range(1, len(weeks)):
                previous_week = weeks[i - 1]
                current_week = weeks[i]
                growth = weekly_sales[current_week] - weekly_sales[previous_week]
                print(f"{current_product}\t{current_week}\t{growth}")
        current_product = product
        weekly_sales = defaultdict(int)
        weekly_sales[week] = quantity

if current_product == product:
    weeks = sorted(weekly_sales.keys())
    for i in range(1, len(weeks)):
        previous_week = weeks[i - 1] 
        current_week = weeks[i]
        growth = weekly_sales[current_week] - weekly_sales[previous_week]
        print(f"{current_product}\t{current_week}\t{growth}")

