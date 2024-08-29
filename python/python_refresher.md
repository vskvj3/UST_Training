```python
a = 1
b = 3

print("a: ", a, "b: ", b)
print("a: %d b: %d" % (a, b))
print("a: {} b: {}").format(a, b)
```

### python sys
```python
import sys
```
- part of standard library
- offers function and variables interacts with interpreter or os


**common attributes**
`sys.argv` 
- (commandline args passed to script)

`sys.exit`:
- exit the program with specified 

`sys.path`:
- list of strings which specifies search path for the module

`sys.stdin`:
- file like object that represents the standard input stream.
- used to read input from user or input stream.
- used when we need to handle input from command line.
