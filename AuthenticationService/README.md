# AuthenticationServer


###  Endpoint

#### CreateToken

    POST http://localhost:8881/hr/newToken
        parameters:
            {
            "email" : "****@****.com"
            }
        
#### registration

    POST http://localhost:8881/anonymous/Registration/{{token}}
        !{the link will appear in CreateToken Email}
        parameters:
            {
                "username" : "****",
                "password" : "****",
                "email" : "****@****.com"
            }
#### login

    POST http://localhost:8881/anonymous/login
        parameters:
            {
                "username" : "****",
                "password" : "****"
            }

### RabbitMQ config

#### Sender config
    in file: "application.properties"

#### Java file
    At file "com.hr.authenticationservice.rabbitmq.config.RabbitConfig"
        line 27, 28 set to yours

#### web config
    Exchange:    "emailExchange"
    Queue:       "tokenQueue"
    binding: 
    Routing key: "token"


    