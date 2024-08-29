
### Mapper:
```python
#!/usr/bin/python3
"""mapper.py"""
import sys
# input comes from standard input
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip()
    # split the line into words
    words = line.split()
    # increase counters
    for word in words:
        # write the results to strdout(standard output)
        # tab-delimited; the trivial word count is 1
	# Output of Mapper Job is input to Reducer <--
        print('%s\t%s' % (word, 1))
```
- output of the mapper will be in sorted order by country name(keys)

### Reducer
```python
#!/usr/bin/python3
"""reducer.py"""
import sys

current_word = None
current_count = 0
word = None

# input comes from STDIN
for line in sys.stdin:

    line = line.strip()
    # parse the input we got from mapper.py
    # word & count are seperated by <tab> delimited
    # 1 -> No of splits
    word, count = line.split('\t', 1)

    # convert count (currently a string) to integer data
    try:
        count = int(count)
    except ValueError:
        # count was not a number, so silently
        # ignore/discard this line
        continue

    # this IF-switch only works because Hadoop sorts map output
    # by key (here key is word) before it is passed to the reducer
    if current_word == word:
        current_count += count
    else:
        if current_word:
            # write result to stdoutput seperated by tab
            print ('%s\t%s' % (current_word, current_count))
	# making current_word = word
        current_count = count
        current_word = word

# do not forget to output the last word if needed!
if current_word == word:
    print ('%s\t%s' % (current_word, current_count))
```

- testing in local:
```bash
cat ~/Downloads/sample.txt | ./mapper.py | sort -k1,1 | ./reducer.py | sort -k2,2rn
```

### Running in hadoop
1. first upload the dataset to hdfs
```bash
hdfs dfs -put ./employeer.csv /wordcount
```
- here wordcount is a dir in hdfs
2. run mapreduce job
```bash
hadoop jar /path/to/hadoop-streaming.jar -file ./mapper.py -mapper mapper.py -file ./reducer.py -reducer reducer.py -input /wordcount/sample.txt -ouput /wordcount/output
```

hadoop streaming will be stored in:
```bash
/usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-2.7.6.jar
```

3. get the output
```bash
hdfs dfs -cat /wordcount/output/*
```

- /wordcount/output in hdfs will have two files
  - _SUCCESS: when the map reduce operation were successful
  - part-00000: the output of map reduce operation    