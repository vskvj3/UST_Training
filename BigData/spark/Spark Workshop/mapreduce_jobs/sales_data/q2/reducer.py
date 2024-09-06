#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_product = None
current_rating = 0
count = 0
product = None
countries = []
# input comes from STDIN
for line in sys.stdin:

    line = line.strip()
    # parse the input we got from mapper.py
    # word & count are seperated by <tab> delimited
    # 1 -> No of splits
    product, rating = line.split(',', 1)

    # convert count (currently a string) to integer data
    try:
        rating = float(rating)
    except ValueError:
        # count was not a number, so silently
        # ignore/discard this line
        continue

    # this IF-switch only works because Hadoop sorts map output
    # by key (here key is word) before it is passed to the reducer
    if current_product == product:
        current_rating += rating
        count += 1
    else:
        if current_product:
            # write result to stdoutput seperated by tab
            # print ('%s\t%s' % (current_word, current_count))
            print("%s\t%.2f" % (current_product, (current_rating) / count))
# making current_word = word
        current_product = product
        current_rating = rating
        count = 0

# do not forget to output the last word if needed!
if current_product == product:
    print("%s\t%.2f" % (current_product, (current_rating)/count))
#    print ('%s\t%s' % (current_word, current_count))

