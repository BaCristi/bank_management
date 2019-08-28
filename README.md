# bank_management
Setup:

docker run --name mysql-run -p :9999:3306 -e MYSQL_ROOT_PASSWORD=p -d mysql:latest

mysql -P 9999 --protocol=tcp -u root -p

CREATE DATABASE bank_db;

1. Register user

curl -X POST \
  http://localhost:8081/user/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "cristi@mail.com"
}'

2. Create bank account

Request

curl -X POST \
  http://localhost:8081/bankAccount/create \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "cristi@mail.com"
}'

Response

{
    "accountName": "cristi_78b3d53",
    "balance": 0
}

3. Deposit into bank account

Request

curl -X POST \
  http://localhost:8081/bankAccount/balance/deposit \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "cristi@mail.com",
	"accountName" : "cristi_78b3d53",
	"depositQuantity": 1000
}'
Response

{
    "accountName": "cristi2_78b3d53",
    "balance": 1000
}

3. Transfer between bank accounts

Request

curl -X POST \
  http://localhost:8081/bankAccount/balance/transfer \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "cristi@mail.com",
	"accountNameSource" : "cristi_78b3d53",
	"accountNameDestination" : "cristi_16724f5",
	"transferQuantity": 1000
}'

Response

[
    {
        "accountName": "cristi_78b3d53",
        "balance": 0
    },
    {
        "accountName": "cristi_16724f5",
        "balance": 1000
    }
]

4. Get bank account balance

Request

curl -X GET \
  'http://localhost:8081/bankAccount/balance?email=cristi2%40mail.com&accountName=cristi2_16724f5' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"email": "cristi@mail.com",
	"accountName" : "cristi_643ee06" 
}'
Response

{
    "accountName": "cristi_643ee06",
    "balance": 1000
}
