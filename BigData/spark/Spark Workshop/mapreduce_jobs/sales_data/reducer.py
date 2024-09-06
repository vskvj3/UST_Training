#!/usr/bin/python3
"""reducer.py"""
import sys
from collections import defaultdict

current_branch = None
current_revenue = 0
country = None
countries = []
# input comes from STDIN
for line in sys.stdin:

    line = line.strip()
    # parse the input we got from mapper.py
    # word & count are seperated by <tab> delimited
    # 1 -> No of splits
    branch, gross_income = line.split(',', 1)

    # convert count (currently a string) to integer data
    try:
        gross_income = float(gross_income)
    except ValueError:
        # count was not a number, so silently
        # ignore/discard this line
        continue

    # this IF-switch only works because Hadoop sorts map output
    # by key (here key is word) before it is passed to the reducer
    if current_branch == branch:
        current_revenue += gross_income
    else:
        if current_branch:
            # write result to stdoutput seperated by tab
            # print ('%s\t%s' % (current_word, current_count))
            print("%s\t%s" % (current_branch, current_revenue))
# making current_word = word
        current_branch = branch
        current_revenue = gross_income

# do not forget to output the last word if needed!
if current_branch == branch:
    print("%s\t%s" % (current_branch, current_revenue))
#    print ('%s\t%s' % (current_word, current_count))

