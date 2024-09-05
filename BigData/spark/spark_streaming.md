## Spark Streaming Workflow

Data streams --> recievers(ssc) --[batches]--\
--> spark --> results

![spark streaming](http://www.lifeisafile.com/assets/images/spark_streaming_working.jpg)

DStream:
- discretized stream
- fundamental abstraction in spark streaming
- A series of RDDs

- At regular interval line will be created

operations on dstreams:
- map
- flatMap
- filter
- repartiotion
- union
- count
- reduce
- countByValue
- ReduceByKey
- join
- cogroup
- transform: apply operations on each rdd, output will be dstream.
- foreachRDD: apply operations on each RDD on dstream, output will not be another dstream.