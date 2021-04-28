
###  Weather API
- Accept a city name via REST API
- Get the current weather for the city name using the OpenWeather API
- Return the response as JSON following this **[JSON schema](https://json-schema.org/)**:
```
{
  "title": "Weather",
  "type": "object",
  "properties": {
    "condition": {
      "type": "string",
      "description": "The description of the weather. eg: scattered clouds."
    },
    "temperature": {
      "type": "number",
      "description": "The actual temperature of the city in celsius."
    },
    "wind_speed": {
      "type": "number",
      "description": "Speed of the wind in km/h.",
      "minimum": 0
    }
  }
}

