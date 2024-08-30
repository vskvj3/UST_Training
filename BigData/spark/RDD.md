## RDD

- Resilient Distributed Dataset
- It is a collection of objects connected across nw
- immutable collection of objects which defines the data structure of spark
  
### Characteristics of RDD
- Lazy evaluation:
  - [only executes when action command is found]
  - rdd --> transform --> DAG --> waits for action command
  - It creates a DAG:
    - is a graphical representation of how spark will execute teh program.
  - Parallelized Collections: sc.parallelize()
    - any collections
  - External Data: textFile()
    - external files
  - Existing RDD: rdd2 = rdd.tranformation()
- Partitioning:
  - equivalent to number of workers. 
  - rdd.repartition() --> increase number of partitions
  - rdd.coalesce() --> reduce number of partitions
  - divides and distributes the input to multiple workers. 
- Persistance
  - every RDD is immutable
- Inmemory computation
  -  we have cache by default
  -  
### DAG
- operations performed on RDDs are represnted in the form of vertices and edes respectively.
![DAG](https://techvidvan.com/tutorials/wp-content/uploads/sites/2/2019/11/DAG-Visualisation-01.jpg)
- edges represents a seperate operation and edges represent the operation dependencies.
- Directed, Asyclic, Graph
### Transformations
- RDD Transformamtons are divided into narrow transformations and wide transformations 
- Narrow transformation: 
  - self sufficient
  - result of map and filter functions, such that the data is froma sinle partiiton only
  - aggregate type of functions.
- Wide transformation
  - not self sufficient
  - aggregate type of transformation
  - takes help of 3 parts shuffling
  - output will be depended upon the individual data.