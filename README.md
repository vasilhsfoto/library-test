# library-test
testing

e.g 
1. POST create a book 
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "extra-field": "extra",
  "name": "The best book 2",
  "publicationDate":"1998-10-12",
  "numberOfPages": 150,
  "authors":[
    {"name":"Alex"},
    {"name":"Alex"}
   ]
}' \
 'http://localhost:8080/api/libraries/1/books/'

2. GET a book by id 

curl -i -X GET \
 'http://localhost:8080/api/libraries/1/books/${bookId}'
 
 3. GET all Books
 
 url -i -X GET \
  'http://localhost:8080/api/libraries/1/books/'
  