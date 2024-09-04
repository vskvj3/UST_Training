### What is streaming
- continues flow of data at a high speed rate

### Realtime dataprocessing lifecycle:
<!-- ![rts](https://www.researchgate.net/profile/Fatih-Gurcan/publication/329565393/figure/fig1/AS:711978163134464@1546760230618/Lifecycle-of-real-time-big-data-processing.png) -->

<img  src='https://www.researchgate.net/profile/Fatih-Gurcan/publication/329565393/figure/fig1/AS:711978163134464@1546760230618/Lifecycle-of-real-time-big-data-processing.png' height='700'></img>

Introduction to spark streaming:
- extension of core spark API
- Kafka: acts as broker
- Flume
- Kinesis
------
- SSC(Structure Streaming Context)
  - Entry point to spark streaming
- features of spark streaming:
  - scaling
  - speed
  - fault tolerence
  - integration
  - business analysis

- workflow of spark streaming

![alt](http://www.lifeisafile.com/assets/images/spark_streaming_working.jpg)

```mermaid
flowchart LR
    stream-->dstreams-->rdd-->hdfs
```

DStream discretized stream