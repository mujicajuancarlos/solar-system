# Sistema Solar 
[![Build Status](https://travis-ci.com/mujicajuancarlos/solar-system.svg?branch=develop)](https://travis-ci.com/mujicajuancarlos/solar-system)

_Sistema informático que permite dominar la predicción del clima de una galaxia lejana._

### Pre-requisitos

```
Java: 1.8
Sprint Boot: 2.1.5.RELEASE
```

### Instalacion

```
mvn clean install
```

### Deploy

```
mvn clean package appengine:deploy -P cloud-gcp
```

### REST API

##### Clima para un día especifico

###### Request

`GET /clima?dia=2`

    curl -i -H 'Accept: application/json' https://solar-system-242800.appspot.com/clima?dia=1

###### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Content-Type: application/json

    {
        "dia": 0,
        "clima": "Sequia"
    }

##### Estadisticas de los proximos 10 años

###### Request

`GET /clima/reporte`

    curl -i -H 'Accept: application/json'  https://solar-system-242800.appspot.com/clima/reporte

###### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Content-Type: application/json

    {
        "dias_sequia": 41,
        "dias_lluvia": 1187,
        "dia_lluvia_maxima": 72,
        "dias_optimo": 0
    }

