#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_branch = None
current_lines = None
current_quantity = 0
branch = None
quantity = 0

for line in sys.stdin:

    line = line.strip()

    quantity, line, branch= line.split(',')
#    print(quantity, line, branch)
    try:
        quantity = int(quantity)
    except ValueError:
        continue

    if current_branch == branch and current_lines == line:
        current_quantity += quantity
    else:
        if current_branch and current_lines:
            print(current_branch, current_lines, current_quantity)
        current_branch = branch
        current_lines = line
        current_quantity = 0

if current_branch == branch and current_line == line:
    print(current_branch, current_lines, current_quantity)
