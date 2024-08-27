
- create new folder
```bash
hdfs dfs -mkdir /newfolder
hdfs dfs -mkdir -p /newfolder2/newfolder
```

- copy from local to hdfs
```bash
hdfs dfs -copyFromLocal file.txt /newfolder/file.txt
```
or
```bash
hdfs dfs -put file.txt /newfolder/file.txt
```

- print file
```bash
hdfs dfs -cat /newfolder/file.txt
```

- create new file
```bash
hdfs dfs -touchz newfile.txt
```

- copy from one file to another
- copy content from local to hadoop
```bash
hdfs dfs -copyFromLocal newfile.txt /newfolder2/tempfile.txt
```

we can add mutiple files in single append
```bash
hdfs dfs -copyFromLocal newfile1.txt newfile2.txt /newfolder2/tempfile.txt
```

- append from hadoop to locatl
```
hdfs dfs -cat /newfolder/newfile.txt > locafile.txt
```

- copy from hadoop to local
```bash
hdfs dfs -copyToLocal /newdir1/tempfile.txt .
```
or 
```bash
hdfs dfs -get /newdir1/tempfile.txt .
```

- copy within hadoop
```bash
hdfs dfs -cp /hdfs/file1.txt /newdir1/
```

- list files
```bash
hdfs dfs -ls /
```
we can provide recursive path aswell
```bash
hdfs dfs -ls -R /
```

- delete a file
```bash
hdfs dfs -rm /folder/file.txt
```

- delete a directory
empty:
```bash
hdfs dfs -rmdir /newdir
```
non empty:
```bash
hdfs dfs rm -r /newdir
```

- empty the trash
```bash
hdfs dfs -expunge
```

- change file permissions
```bash
hdfs dfs -chmod 777 file.txt
```

- change ownership
```bash
hdfs dfs -chown user:group file.txt
```
