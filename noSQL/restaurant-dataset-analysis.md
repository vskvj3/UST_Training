### 1. use retaurants
db.createCollection()
### 2. db.getCollectionInfos()
db.getCollectionInfos()

db.restaurant.find()

### 2. 
### to show restaurant_id, name, borough, zipcode and exclude field id 
```
db.restaurant.find({}, {"restaurant_id":1, "name":1, "borough": 1, "address.zipcode": 1, _id: 0})
```

### combined rsponse of query and projections
```
db.restaurant.find({"cuisines":"American "}, {"restaurant_id":1, "name":1, "borough": 1, "address.zipcode": 1, _id: 0})
```

### restaurants with first score gt 2
```
db.restaurant.find({}, {"restaurant_id":1, "name":1, "borough": 1, "address.zipcode": 1, _id: 0})
```

### total scores of each restaurants
```
db.restaurant.aggregate(
  [{$unwind:"$grades"}, {$group:{_id: "$_id", totalScore: {$sum: "$grades.score"}}}])
```
- unwind: deconstruct array into individual documents

### grades greater than 90
```
db.restaurant.find({"grades.score": {$gte: 90}})
```

```
db.restaurant.aggregate([
	{$unwind:"$grades"}, 
	{$group:{_id: "$restaurant_id", 
		totalScore: {$sum: "$grades.score"},
		name: {$first: "$name"}, 
		cuisine: {$first:"$cuisine"}, 
		address: {$first:"$address"}}},
	{$match:{totalScore:{$gte:90}}}
])
```

### Scores greater than 90 and less than 100
db.restaurant.find({"grades.score": {$gt: 90, $lt: 100}})

### sorting cuisines in asc and borough in desc
> 1: ASC\
> -1: DESC

```
db.restaurant.find().sort(
  {cuisine: 1, borough: -1}
  ).pretty()
```

### 9. 
```
db.restaurant.find(
  {$or: [
    {cuisine: {$nin: ["American ", "Chinese"]}},
    {name: {$regex: /^Bil/}}
  ]},
    {_id: 0, restaurant_id: 1,  name: 1, borough: 1, cuisine: 1}
).pretty()
```
```
db.restaurant.find(
{$or: [
  {cuisine: {$nin: ["American ", "Chinese"]}},
  {name: {$regex: /^Bil/}}
]},
  {"_id": 0, "restaurant_id": 1,  "name": 1, "borough": 1, "cuisine": 1}
).pretty()

```
### 10. 
- answer 1:
```
db.restaurant.aggregate([
	{$unwind:"$grades"}, 
	{$group:{_id: "$restaurant_id", 
		maxScore: {$max: "$grades.score"},
		name: {$first: "$name"},
		restaurant_id: {$first: "$restaurant_id"}, 
		cuisine: {$first:"$cuisine"}}},
	{$match:{cuisine:{$eq :"Indian"}}}
])
```
### 11.
```
db.restaurant.aggregate([
	{$unwind:"$grades"}, 
	{$group:{_id: "$restaurant_id", 
		maxScore: {$max: "$grades.score"},
		name: {$first: "$name"},
		restaurant_id: {$first: "$restaurant_id"}, 
		cuisine: {$first:"$cuisine"}}},
	{$match:{cuisine:{$eq :"Indian"}}}
])
```
### 12. 
```
db.restaurant.find(
{name: {$regex: /il/}},
{restaurant_id: 1, name: 1, borough: 1, cuisine: 1, "grades.score": 1}).pretty()
```
### 14.
```
db.restaurant.find(
{"grades.score": {$mod:[7,0]}},
{restaurant_id: 1, name: 1, borough: 1, cuisine: 1, "grades.score": 1}).pretty()
``` 

### 15.
```
db.restaurant.countDocuments({"address.street": {$exists:true}})
db.restaurant.countDocuments({"address.street": {$exists:false}})
```

### 16. 


## Questions
### 1. Find all restaurants in Brooklyn with grade of 'B' or lower
```javascript
db.restaurant.find({
	"borough": "Brooklyn", 
	"grades.grade" : {$in: ["B", "C", "D", "E", "P"]}
	})
```

```javascript
db.restaurant.find({
	'borough': 'Brooklyn', 
	"grades.grade" : {$nin: ['A']}, 
	grades: {$ne: []}
})

```
### 2. find restaurants with an average score below 10
```javascript
db.restaurant.aggregate([
	{$unwind:"$grades"}, 
	{$group:{_id: "$restaurant_id", 
		avgScore: {$avg: "$grades.score"},
		name: {$first: "$name"},
		restaurant_id: {$first: "$restaurant_id"}, 
		cuisine: {$first:"$cuisine"}}},
	{$match:{avgScore:{$lt :10}}}
])
```

### 3. Find all restaurants in the 'Brooklyn' borough that seve "American " online and have a score less tha 10 on any inspection.
```javascript
db.restaurant.find({
$and: [
  {borough: {$eq: "Brooklyn"}},
  {cuisine: {$eq: "American "}},
  {"grades.score": {$lt: 10}}
]
})
```
```javascript
db.restaurant.find({
	borough: "Brooklyn",
  	cuisine: "American ",
  	"grades.score": {$lt: 10}
})
```

### 4. Retrieve all restaurants that have a 'A' grade and sort them by themost recent grade in desc order.

```javascript
db.restaurant.find(
{"grades.grade": "A"}, 
{name: 1, "grades.$":1}
).sort({"grades.date": -1})
```

### 5. set limit on previous respnse by 10.
```javascript
db.restaurant.find(
{"grades.grade": "A"}, 
{name: 1, "grades.$":1}
).sort({"grades.date": -1}).limit(10)
```

### 6. For each borough Calculate and find aggregate methods - find average, median, minimum and maximum grade score of restaurant seving "Chinse" cuisine.
```javascript
db.restaurant.aggregate([
  {$match: {cuisine: "Chinese"}},
  {$unwind: "$grades"},
  {$group: {_id: "$borough", scores: {$push: "$grades.score"}}},
  {$addFields: {medianScore: {$percentile: {input: "$scores", percentile: 0.5}}}},
  {$project: {_id: 0, borough: "$_id", 
		avgScore: {$avg: "scores"}}}
])
```
- using sort
```javascript
db.restaurant.aggregate([
  {$match: {cuisine: "Chinese"}},
  {$unwind: "$grades"},
  {$group: {_id: "$borough", scores: {$push: "$grades.score"}}},
  {$project: {scores: { $sortArray: { input: "$scores", sortBy: 1 } } } },
  {$project: {_id: 0, borough: "$_id", 
		avgScore: {$avg: "$scores"},
		maxScore: {$max: "$scores"},
		minScore: {$min: "$scores"},
		medianScore: {$arrayElemAt: ["$scores", {$toInt: {$divide: [{ $size: "$scores" }, 2]}}]}
}}
])
```
## Optmization Techniques:
### 7. create the compund interest on borough and cuisine and query restaurants in "Queens" serving "Jewish/Kosher" cuising this index.
- Optimization of query using indexing
```javascript
db.restaurant.createIndex({
	borough: 1, 
	cuisine: 1
})

db.restaurant.find({
borough: "Queens",
cuisine: "Jewish/Kosher"
})
```
### 8. Optimization using partial index for restaurants

```javascript
db.restaurant.createIndex(
	{"grades.score": 1},
	{partialFilterExpression: 
		{"grades.score": {$gt: 20}}
	})

db.restaurant.find({"grades.score": {$gt: 20}})
```


### Group By template
```
aggregate([
	{$match: {}},
	{$group{
		_id: null
	}}
	{$project{ projections }},
	{$sort: { sort params }},
	{$limit: 1}
])
```

### find template
```javascript
find({
	query 
},
{
	projection
})
.sort({})
.limit({})
```