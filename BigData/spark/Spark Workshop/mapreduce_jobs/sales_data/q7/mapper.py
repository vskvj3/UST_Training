#!/usr/bin/python3
"""mapper.py"""
import sys

for line in sys.stdin:

    line = line.strip().split(',')

    try:
        product_line = line[5]
        total = line[9]
        gross_income = float(line[15])
    except:
        continue

    print('%s,%s' % (product_line, gross_income))
