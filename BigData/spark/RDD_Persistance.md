## RDD Persistance
- memory is either cache or persist
- if cache is too much, spark automatically evicts old partition using LRU.
### Storage Level

- MEMORY_ONLY
  - desirialized java objects
  - recomputation for uncached data(if memory exceeds)
  - only memory
  - no dist
  - deserialized
- MEMORY_AND_DISK
  - store data in disk if it exceeds memory.
  - read them from disk when needed.
- MEMORY_ONLY_SER
  - Serialized java objects
  - onee byte array per partition
- DISK_ONLY
  - Stores RDD in disk
- MEMORY_ONLY_2 and MEMORY_AND_DISK_2
  - replicate in tow cluster nodes
- OFF_HEAP
  - similar to MEMORY_ONLY_SER
  - store data in off heap memory 
  - off heap memory needs to enabled
