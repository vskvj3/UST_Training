#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_branch = None
current_lines = defaultdict(int)
branch = None
result = defaultdict(list)

for line in sys.stdin:

    line = line.strip()

    quantity, line, branch= line.split(',')
#    print(quantity, line, branch)
    try:
        quantity = int(quantity)
    except ValueError:
        continue

    if current_branch == branch:
        current_lines[line] += quantity
    else:
        if current_branch:
            result[current_branch].append(dict(current_lines))
        current_branch = branch
        current_lines = defaultdict(int)

if current_branch == branch:
    result[current_branch].append(dict(current_lines))

for branch, value in result.items():
    print(branch, value)
    print('')
