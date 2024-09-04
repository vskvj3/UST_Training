## Hive
- hive is part of hadoop ecosystem
- hive runs on top of hadoop services
- Executes each query as map reduce job
- sql like data warehousing application
- HiveQL is used
- It provides a high level abstraction over map reduce
- batch processing
- suitable for structured or semi structured data
- if you perform load in hdfs to hive


![hive arch](https://miro.medium.com/v2/resize:fit:640/format:webp/0*d5DOvZIR_O4PPYlb)

- metastore: metadata about the table
- hive thrift server: mutliple users can access hive at the same time
- how to access hive:
  - cli
  - ihve thrift server
  - hive web interface
  - as .hql file

**Job execution flow**
1. Parser: check for the query syntax
   1. hive query --> parser --> check syntax, err, schema, datatype --> DAG (if no error)
2. Optimizer: optimize the query 
   1. faster execution
3. Executer:
   1. it splits jobs into multiple tasks and executes the tasks.
   2. DAG(physical execution plan) to map reduce job
4. submit jobs 
5. monitor


partitioning and bucketing
- partitioning divides data into mutliple partitions based on category.
- bucketing divides the data into equal distribution.
- 