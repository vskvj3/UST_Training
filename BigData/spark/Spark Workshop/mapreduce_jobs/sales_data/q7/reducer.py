#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_product = None
current_revenue = 0
product = None

records = []

for line in sys.stdin:

    line = line.strip()

    product, gross_income = line.split(',', 1)

    try:
        gross_income = float(gross_income)
    except ValueError:
        continue

    if current_product == product:
        current_revenue += gross_income
    else:
        if current_product:

            records.append((current_product, current_revenue))

        current_product = product
        current_revenue = gross_income

if current_product == product:
    records.append((current_product, current_revenue))

print("%s\t%.2f" % max(records, key=lambda x: x[1]))
