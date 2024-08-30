#!/usr/bin/python3
"""reducer.py"""
import sys
import numpy as np

ratings = []
revenue = []

for line in sys.stdin:

    line = line.strip()

    rating, total = line.split(',', 1)

    try:
        rating = float(rating)
        total = float(total)
    except ValueError:
        continue

    ratings.append(rating)
    revenue.append(total)


#print(ratings)
#print("totals")
#print(revenue)

print("correlation: ", np.corrcoef(ratings, revenue)[0][1])
