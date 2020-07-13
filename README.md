This application is for calculation premium (price of insurance)

for api description please use swagger ui

`http://localhost:8080/swagger-ui.htm
`

it got POST /calculatePremium endpoint, which returns premium price rounded with 2 digits after comma

json body example:
```json
{
  "number": "string",
  "objects": [
    {
      "name": "string",
      "subObjects": [
        {
          "name": "string",
          "riskType": "FIRE",
          "sumInsured": 0
        }
      ]
    }
  ],
  "status": "REGISTERED"
}