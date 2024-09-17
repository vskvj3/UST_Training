# Azure Storage:
Azure storage offers a massively scalable object store for data objects. a file system for the azure cloud, a messaging store for reliable messaging and a NoSQL store.
- Durable
- Scalable
- Secure
- Fault Tolerence
- Highly available
- Managed
- Accessible

## Types
- Blobs:
  - Highly scalable, REST based cloud object store.
- Tables:
  - .
- Queues
- Disks
- Files
- Azure Import/Export
- AZCopy

### Blob Storage:
Massively scalable object store for text as well as binary data. This is ideal for serving images or documents directly to a browser.
- blob stores files for distributed access, streaming video and audio.
- blob offers backup and restore disaster recovery and archiving. 
  
```mermaid
block-beta
  block
  columns 2
  PNG CSV EXE XLSX
  end

  BLOB
```

```mermaid
---
title: containers
---
flowchart TB
    bs[Blob Storage]
    bs ---> c1[Container 1]
    bs ---> c2[Container 2]
    bs ---> c3[Container 3]
    bs ---> c4[Container 4]

    c1 ---> s1[Files 1]
    c2 ---> s2[Files 2]
    c3 ---> s3[Files 3]
    c4 ---> s4[Files 4]
```
each blob file will be accessible using URI

####  Blob storage access Tiers:
Azure storage provides different options for accessing block of bolb data based on usage patterns.
- **Hot**: Optimized for frequent access of objects
- **Cool**: Optimized for storing large amount of data which is infrequently accessible and stored for atleast 30 days.
- **Archive**: Optimized for the data, that can tolerate several hours of retrival latency and it will remain in the archive tier for atleast 180 days. 

### Queue Storage:
A messaging store for reliable messaging between application components.

- Messaging queue for asynchronous communication between application components.
- It stores and retrieves messages in FIFO order.

```mermaid
flowchart TB
    bs[Queue Storage]
    bs ---> q1[Queue 1]
    bs ---> q2[Queue 2]
    bs ---> q3[Queue 3]
    bs ---> q4[Queue 4]

    q1 ---> s1(Messages)
    q2 ---> s2(Messages)
    q3 ---> s3(Messages)
    q4 ---> s4(Messages)
```
- storage for small pieces of data, (source will generate small messages at regular intervals).
- It is usedfor Asynchronous messaging.
- Suppose reliable messaging between application and application component. 
- Highly scalable, as this can handle millions of messages per second.
- This can trigger azure services, functions for automation processing.
- This can be used for processing log message, user interaction with application and background tasks.

### Table Storage
- Azure storage table is NoSQL key value storage with fast access and schema-less design
- Highly scalable and designed for applications requiring large amount of structured, non-relational data. 
- eg: application logs as key value pairs or metadata. 
- This stores requires low latency data access and high availability.

```mermaid
flowchart TB
    bs[Table Storage]
    bs ---> t1[Table 1]
    bs ---> t2[Table 2]
    bs ---> t3[Table 3]
    bs ---> t4[Table 4]

    t1 ---> r1("Rows of records <br> as key value")
    t2 ---> r2("Rows of records <br> as key value")
    t3 ---> r3("Rows of records <br> as key value")
    t4 ---> r4("Rows of records <br> as key value")
```
```mermaid
block-beta
    block
    columns 1
        r1["{1d, XYZ, 21}"]
        r2["{1d, ABC, 22}"]
        r3["{1d, PQL, 21}"]
    end

    blockArrowId5<["Access Through app"]>(x)

    block
    columns 2
        t1["table 1"]
        t2["table 2"]
    end
```

### File Storage
- Manages files share accessible via SMB(Server Message Block) and NFS(Network File System)
- SMB Protocol is a network file sharing protocole that allows applications on cloud to read and write to files and request services from server programs.
- This can also be mounted to drives of computer.

## Data Redundancy:
- Azure storage replicates multiple copies of your data. Replications options for a storage includes: 
  - Local Redundant Storage (LRS)
  - Zone Redundant Storage (ZRS)
  - Geo Redundant Storage (GRS)

```mermaid
flowchart TD
    f[Files]

    bs[Blob Storage]

    subgraph ssu[Simple Storage Unit]
        subgraph ssu1[ ]
        s1[ ]
        s2[ ] 
        end
        subgraph ssu2[ ]
        s3[ ]
        s4[ ]
        end
        subgraph ssu3[ ]
        s5[ ]
        s6[ ]
        end
    end

    f --> bs
    bs --> ssu
    msg[/Data will be <br> replicated in Synchronously\]
    ssu --> msg
    msg --> f1[File Copy #1]
    msg --> f2[File Copy #2]
    msg --> f3[File Copy #3]
```

LRS: simple low cost data replication strategy. Data is replicated within a single storage.

ZRS: High availability and durability. Data is replicated synchronously across three availability zones.

GRS: Cross regional replication to protech against region wide unavailability of data.

Read-Access GRS(RA-GRS): Cross regional replicationw ith read access tot he replica.