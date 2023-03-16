# spring-cloud-gateway-jwt-security

## register an user
-> Call auth-service with below cURL
```
curl --location --request POST 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"John",
    "password":"password",
    "email":"john@gmail.com"
}'
```
![Screenshot 2023-03-16 at 10 52 25 AM](https://user-images.githubusercontent.com/25712816/225522510-386fb3c6-5cd5-43fd-a84f-bab9f1e60d66.png)

## Authenticate User & Generate token 

```
curl --location --request POST 'http://localhost:8080/auth/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"John",
    "password":"password"
}'
```
![Screenshot 2023-03-16 at 10 52 40 AM](https://user-images.githubusercontent.com/25712816/225522686-b7f192f2-16fc-4582-8bbd-591d208427a7.png)


## Pass token and call Swiggy-app 
```
curl --location --request GET 'http://localhost:8080/swiggy/37jbd832' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNjc4OTQzOTEyLCJleHAiOjE2Nzg5NDU3MTJ9.gV7_cGO0XDRlOKRM6MGEAM2kPdFp-cTiUepy3Xw0CrE'
```
![Screenshot 2023-03-16 at 10 52 58 AM](https://user-images.githubusercontent.com/25712816/225522724-83012849-23fd-4c91-92e6-eb0b0a854ba5.png)

## Pass token and call Restaurant service
```
curl --location --request GET 'http://localhost:8080/restaurant/orders/status/9u71245h' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNjc4OTQzOTEyLCJleHAiOjE2Nzg5NDU3MTJ9.gV7_cGO0XDRlOKRM6MGEAM2kPdFp-cTiUepy3Xw0CrE'
```
![Screenshot 2023-03-16 at 10 53 15 AM](https://user-images.githubusercontent.com/25712816/225522744-38176905-2b8c-4649-8627-569c1a2370ce.png)


