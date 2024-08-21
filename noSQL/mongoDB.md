### Create database:
```
use database_name;
```
### create a collection:
```
db.createCollection("collection_name");
```
### insert into collections
- insertOne()
```
db.collection_name.insertOne()
```
eg:
```
db.products.insertOne({"product_id": 1, "product_name": "IPhone 13", "category": "Electronics", "price": 200000})
```
- inserMany()
```
db.collection_name.insertMany([{}, {} ..])
```
### show all docs
```
db.collection_name.find()
```
### show document using filter
can have query inside: 
`db.products.find({"product_id": 1})`
- it can also have operators:
```
db.products.find({"price": {$gt: 100000}})
```
- `$gt`: greater than
- `$lt`: less than
- `$gte`: greater than equal to
- `$lte`: less than equal to

### Update documents:
- update single document
```
db.collection_name.updateOne({find query}, {$set{new values}})
```

```
db.products.updateOne(
  {"product_id": 1},
  {$set:{"discount": 10}}
)

db.products.updateOne(
  {"product_id": 1},
  {$set:{"price": 10}}
)
```
- update multiple documents:
```
db.products.updateMany(
  {"category": "Electronics"},
  {$set:{"discount": "10%"}}
)
```
### Delete records
- delete a single record:
```
db.products.deleteOne({"product_id": 1})
```
- delete multiple records
```
db.products.deleteMany({"price": {$gte: 100000}})

db.products.deleteMany({"price": {$lte: 100000},"price": {$gte: 50000}})
```

### show number of documents in collection:
```
db.products.countDocuments()
```
### delete a collection
- drop current collection
```
db.products.drop()
```
- drop current database
```
db.dropDatabase()
```

### get collection information
- db.getCollectionInfos() get collection related infos
- db.getCollectioName() to get collection names
## Why?

- NoSQL databases can store both structured and unstructured data.
- multiple sources like(xml, csv, one is url with json etc. )

> $gt : predefined functions\
> "$name" : Attribute names of the collection\
> "name" : values of the attributes\
> "name" (in query): collection attribute name\
> name: field names


### logical operators:
- $or
- $and

> combining multiple conditions
```
db.restaurant.find({
  "grades.score": {$gt: 50}, 
  "cuisine": "Bakery"
})
```

```
db.restaurant.find({
"grades.score": {$gt: 50}, 
"restaurant_id": {$gt: "5000000"}
})
```

### using or condition
```
db.restaurant.find({
  $or: [
	  {"grades.score": {$gt: 50}}, 
    {"restaurant_id": {$gt: "5000000"}}
  ]
})
```

### using not
```
db.restaurant.find({
$or: [
	{"grades.score": {$gt: 50}}, 
  {"restaurant_id": {$gt: "5000000"}}
]
})
```