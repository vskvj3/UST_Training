#!/usr/bin/python3
"""mapper.py"""
import sys
# input comes from standard input
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip().split(',')
    # split the line into words
    try:
        quantity = line[7]
        product_line = line[5]
        branch = line[1]
    except:
        continue
    print('%s,%s,%s' % (quantity, product_line, branch))
