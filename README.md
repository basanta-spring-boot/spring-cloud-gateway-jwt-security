# spring-cloud-gateway-jwt-security

## register an user
-> Call auth-service with below cURL
```
curl --location --request POST 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B05ED94219BCD9E44C82EF1AB7C780B2' \
--data-raw '{
    "name":"Basant",
    "password":"Pwd1",
    "email":"basant@gmail.com"
}'
```
## Authenticate User & Generate token 

```
curl --location --request POST 'http://localhost:8080/auth/authenticate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=72CFCE2BE9F082C1ACC86D7D53A61081' \
--data-raw '{
    "username": "Basant",
    "password": "Pwd1"
}'
```

## Pass token and call Swiggy-app 
```
curl --location --request GET 'http://localhost:8080/swiggy/9u71245h' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCYXNhbnQiLCJpYXQiOjE2Nzg5MDE1MDgsImV4cCI6MTY3ODkwMzMwOH0.nFUsIEP3hi0YV380p2dh6-px4rJfiaF8rVP3w21JaQY' \
--header 'Cookie: JSESSIONID=72CFCE2BE9F082C1ACC86D7D53A61081'
```

## Pass token and call Restaurant service
```
curl --location --request GET 'http://localhost:8080/restaurant/orders/status/9u71245h' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCYXNhbnQiLCJpYXQiOjE2Nzg5MDE1MDgsImV4cCI6MTY3ODkwMzMwOH0.nFUsIEP3hi0YV380p2dh6-px4rJfiaF8rVP3w21JaQY' \
--header 'Cookie: JSESSIONID=72CFCE2BE9F082C1ACC86D7D53A61081'
```
