#!/usr/bin/python3
"""mapper.py"""
import sys

for line in sys.stdin:

    line = line.strip().split(',')

    try:
        rating = float(line[16])
        total = float(line[15])
    except:
        continue

    print('%s,%s' % (rating, total))
