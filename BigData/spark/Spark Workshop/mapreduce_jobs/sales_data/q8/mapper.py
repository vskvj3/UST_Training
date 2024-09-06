#!/usr/bin/env python3
import sys
from datetime import datetime

for line in sys.stdin:
    line = line.strip()
    if line.startswith("Invoice ID"):
        continue
    fields = line.split(',')
    date = fields[10]
    total = float(fields[9])
    day_of_week = datetime.strptime(date, "%m/%d/%Y").strftime("%A")
    print(f"{day_of_week}\t{total}")

