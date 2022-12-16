**/api/customer**

**POST /create**

                {
                    "firstName": "Harry",
                    "secondName": "Potter",
                    "surName": "James ",
                    "secretWord": "Code Potter"
                }

    GET /all

    GET /{id}

**/api/account**

    GET /{id}


    POST /create/{id}

POST /transfer/{id} 

            {
                "savingAccountNumber": "844778665",
                "currentAccountNumber": "1970149665",
                "sumTransfer": 5.0,
                "secretWord": "Code Potter"
            }

**/api/order**

**POST /create**

    {
            "orderType": "WITHDRAWAL",
            "sum": 400.0,
            "executionResult": "SUCCESSFULLY",
            "dateCreation": "2022-12-09",
            "customerAccount":{
                "id": 1,
                "customer": {
                "id": 1,
                "secretWord": "Code Potter"
                            },
        "accountNumber": "1970149665",
        "accountType": "CURRENT_ACCOUNT"
        }
    }

**/api/account/**


transfer/another

    {
        "from": {
                "accountNumber": "1829178941",
                "sum": "10",
                "customer":{
                            "secretWord": "Code Weasley"
                    }
        },
            "to": {
                    "accountNumber": "1970149665"
        }
    }



- Получить информацию обо всех клиентах
- Получить информацию о клиенте по его идентификатору
- Получить информацию о счетах клиента
- Перевод между счетами одного пользователя ()
- Получить информацию о транзакциях по счету клиента
- Получить информацию о кассовых ордерах по счету клиента