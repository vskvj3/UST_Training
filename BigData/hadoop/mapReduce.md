## MapReduce
![MapReduce](https://datascientest.com/en/files/2023/09/illu_schema_mapreduce-04.png)

### Why MapReduce
- Parallel processing
- It was new at the time it is introduced

### Input Split & Record Reader
![Input Splitter and Record Reader](https://waytoeasylearn.com/storage/2020/01/Capture-36.jpg.webp)

![IS2](https://iamsoftwareengineer.wordpress.com/wp-content/uploads/2017/02/map-reduce-flow.png)

- InputSplit
  - it is used for splitting data
- Record Reader
  - It picks the splitted data as key and value.
  - random ofset will act as key
  - Mainly used in java(python map reduce may not need)
- Mapper
  - maps each item with a value
  - intermediate output will be generated, at it will be stored in the disk.
  - takes key and value pairs and generats another key and value pair.
- Partitioner:
  - ensure same key goes to same reducer
  - same keys will be combined together
  - pre-reducer
  - how the output of mapper will be distributed across the reducers.
  - It decides which key value pair will go with which reducer.
  - _load balancing_
- Combiner [optional]:
  - local reducer.
  - Performs partial aggregation of mapper output before it is send to the reducer.
  - reduces volume of data transfered between mapper and reducer.
- Reducer
  - performs agregate operation
  - depends of number of **nodes**