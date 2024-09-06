#!/usr/bin/python3
"""mapper.py"""
import sys
# input comes from standard input
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip().split(',')
    # split the line into words
    try:
        product_line = line[5]
        rating = float(line[16])
    except:
        continue
    # increase counters
    # for word in words:
        # write the results to strdout(standard output)
        # tab-delimited; the trivial word count is 1
	# Output of Mapper Job is input to Reducer <--
    print('%s,%s' % (product_line, rating))
