# irrigation-system

`This a POC of a RESTful api in the project folder root you will find ERD.draw file you can open it throught this link "https://app.diagrams.net/" and select the file to validate the data model also you will find in the root folder two files in case you want to see schema "schema.sql" and "data.sql" finally I used h2 data base for simplicity and the proof of concept or if you want to use real db I used postgres and tried it and working fine you just will need to uncomment it configs in the "application.properties" finally I applied unit testing concept on jpa repository and on a service class for POC, finally I extracted the postman collection you will find it in the root folder just import it in postman and try to send requests, thank you .`
## 1. Add Crop

**URL** : `http://localhost:8080/api/crop/add`

**Method** : `POST`

**first you should add a crop details so that the plot of land be cultivated
to configure irrigation time slots automatically**

### 1.1. Request

```js
{
   "name":"Cotton",
   "waterAmountPerSeason":600,
   "totalGrowingPeriod":100,
   "daysBetweenEachPeriod":10
}
```
### 1.2. Response

`Crop added successfully and its id is 1`

## 2. Add Plot Of Land

**URL** : `http://localhost:8080/api/plot/add`

**Method** : `POST`

**Add a plot of land with its details to be irrigated**

### 2.1. Request

```js
{
   "crop":1,
   "area":100,
   "cultivatedDate":"2022-07-20"
}
```
### 2.2. Response

`Plot added successfully and its Id is 2`


## 3. Add A Sensor

**URL** : `http://localhost:8080/api/sensor/add`

**Method** : `POST`

**In this end point we configure a sensor and dedicated it to a plot of land
you will notice there is a sensorStatus to be 1 or 0 to simulate the case of
forbidden sensor after several retrials**

### 3.1. Request

```js
{
   "name":"sensor 1",
   "sensorStatus":1,
   "plotId":2
}
```

### 3.2. Response

`sensor added successfully with Id 3`

## 4. Configure Slots Automatically

**URL** : `http://localhost:8080/slot/sensor/configure-automatically`

**Method** : `POST`

**In this end pont you just send the plot id you want to configure slots to it and
the system will do it automatically based on crop type and amount of water per mm for each crop type
and total area**

### 4.1. Request

```js
{
   "plotId":2
}
```

### 4.2. Response

`Slots configured automatically`

## 5. Get list of plots and its details

**URL** : `http://localhost:8080/api/plot/details`

**Method** : `GET`

**This end point to get all plots and list its details**

### 5.1. Request

```js
{
   
}
```

### 5.2. Response
```js
{
[
    {
        "id": 3,
        "area": 100.0,
        "totalAmountOfWater": 40000.0,
        "cultivatedDate": "2022-07-20",
        "crop": {
            "id": 1,
            "name": "Tommato",
            "waterAmount": 400.0,
            "totalGrowingPeriod": 100,
            "daysBetweenEachPeriod": 10
        },
        "slots": [
            {
                "id": 8,
                "period": "02:46:40",
                "date": "2022-07-30",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 5,
                    "name": "sensor 1",
                    "sensorStatus": "SUCCEEDED"
                }
            },
            {
                "id": 9,
                "period": "02:46:40",
                "date": "2022-08-09",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 5,
                    "name": "sensor 1",
                    "sensorStatus": "SUCCEEDED"
                }
            },
            {
                "id": 10,
                "period": "02:46:40",
                "date": "2022-08-19",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 5,
                    "name": "sensor 1",
                    "sensorStatus": "SUCCEEDED"
                }
            },
            {
                "id": 7,
                "period": "02:46:40",
                "date": "2022-07-20",
                "amountOWater": 10000.0,
                "slotStatus": "SUCCEEDED",
                "sensor": {
                    "id": 5,
                    "name": "sensor 1",
                    "sensorStatus": "SUCCEEDED"
                }
            }
        ]
    },
    {
        "id": 4,
        "area": 100.0,
        "totalAmountOfWater": 60000.0,
        "cultivatedDate": "2022-07-10",
        "crop": {
            "id": 2,
            "name": "Cotton",
            "waterAmount": 600.0,
            "totalGrowingPeriod": 100,
            "daysBetweenEachPeriod": 10
        },
        "slots": [
            {
                "id": 17,
                "period": "02:46:40",
                "date": "2022-07-10",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            },
            {
                "id": 18,
                "period": "02:46:40",
                "date": "2022-07-20",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            },
            {
                "id": 19,
                "period": "02:46:40",
                "date": "2022-07-30",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            },
            {
                "id": 20,
                "period": "02:46:40",
                "date": "2022-08-09",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            },
            {
                "id": 21,
                "period": "02:46:40",
                "date": "2022-08-19",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            },
            {
                "id": 22,
                "period": "02:46:40",
                "date": "2022-08-29",
                "amountOWater": 10000.0,
                "slotStatus": null,
                "sensor": {
                    "id": 6,
                    "name": "sensor 2",
                    "sensorStatus": "FAILED"
                }
            }
        ]
    }
]
}
```

## 6. Send Signal To Irrigate A Slot 

**URL** : `http://localhost:8080/slot/sensor/irrigate`

**Method** : `POST`

**This end point for sending a signal to a sensor to be irrigated in case the sensor is active and 
running the slot status will be set to SUCCEEDED otherwise in case of the sensor 
is failed and down the system will try to send the signal to the sensor each 0.5s for five times 
then it will update the status of the slot to FAILED**

### In case of active sensor

#### 6.1. Request

```js
{
   "slotId":6
}
```
#### 6.2. Response

`successfully irrigated`

### In case of deactivated sensor

#### 6.3. Request

```js
{
   "slotId":6
}
```
#### 6.4. Response

`Forbidden Sensor Please Check it.....`

## 7. Edit A Plot Of Land

**URL** : `http://localhost:8080/api/plot/edit`

**Method** : `POST`

**In this end point you can edit an existing plot of land**

### 7.1. Request

```js
{
    "id":4,
   "crop":2,
   "area":100,
   "cultivatedDate":"2022-07-10"
}
```

### 7.2. Response

`Plot Edited Successfully`










