
Arithmetic Operation Demo Kotlin Project 


This application developed using kotlin with simple lambda function.

Build project and package as fat jar

Run
```
gradle shadowJar
```

Login with Aws creds in your aws cli 

```
aada login -n -p sand
```

Created Lambda function & Available in AWS
Provide your profile role for needed environment
ex: your account arn: 123412341234
```
aws lambda --profile sand --region eu-west-1 create-function --function-name  ApCalculationLambda --zip-file fileb://APCalculation-all.jar --handler com.test.App --runtime java11 --role arn:aws:iam::<your account arn>:role/service-role/lambda-basic-execution --memory 1024
```

Note: you will get this if your lambda function seems to be already present in aws for testing this
```

An error occurred (ResourceConflictException) when calling the CreateFunction operation: Function already exist: ApCalculation
```

Existing post-api url created by aws apigateway
XXXX should be replacing after deployed lambda then manually created apigateway and its reference id.
```
https://CoXXXX.execute-api.eu-west-1.amazonaws.com/default/ApCalculation 
```
Adding Event Trigger for API GATEWAY

Choose lambda function and add trigger then choose API GATEWAY.

Create api ->RESTAPI ->secuirty as open -> ADD XXXX should be replacing after deployed lambda then manually created apigateway and its reference id.

Execute the below commands for testing in terminal As Demo For APIGATWAY EVENT TRIGGERING as Manual stuff
Api Gate Way DNS after created and deployed then you will get that https://coxxxxxx.execute-api.eu-west-1.amazonaws.com/default/APCalculation

I did manual apigateway setup and triggering as below ways to get the response which are similar of CDK kind of hosting the APi in aws
```
cul -X  POST https://coxxxxxx.execute-api.eu-west-1.amazonaws.com/default/ApCalculation  -H 'Accept: application/json' -H 'Content-Type: application/json' -d $'{
  "argument1": "3.44",
  "argument2": "4.4",
  "result": "7.84",
  "datatype": "Float",
  "method": "Add",
  "message": "Success",
  "statusCode": "200"
}'
```

Response Input
```
{
   "argument1":"3.44",
    "argument2":"4.4",
    "method":"Add",
    "datatype":"Float"
}
```

Response Output
```
{
  "argument1": "3.44",
  "argument2": "4.4",
  "result": "7.84",
  "datatype": "Float",
  "method": "Add",
  "message": "Success",
  "statusCode": "200"
}
```
