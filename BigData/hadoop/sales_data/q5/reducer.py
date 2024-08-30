#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_customer_type = None
current_line = defaultdict(int)
# input comes from STDIN
for line in sys.stdin:

    line = line.strip()

    customer_type, product_line = line.split(',', 1)

    if customer_type == "Customer type":
        continue

    if current_customer_type == customer_type:
        current_line[product_line] += 1
    else:
        if current_customer_type:
            print("%s\t%s" % (current_customer_type, max(current_line, key=current_line.get)))

        current_customer_type = customer_type
        current_line = defaultdict(int)

if current_customer_type == customer_type:
    print("%s\t%s" % (current_customer_type, max(current_line, key=current_line.get)))
