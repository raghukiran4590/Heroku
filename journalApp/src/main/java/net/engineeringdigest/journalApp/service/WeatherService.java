package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String lethalCheetah = "sk_ba54b5ee0b11fb321c796eaab0299067e5cfa7e6848e1541";

    @Autowired
   private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            System.out.println("weatherResponse != null");
            return weatherResponse;
        } else {
            String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);
            System.out.println(finalApi);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                System.out.println("body != null");
                redisService.set("weather_of_"+city, body, 300l);
            }
            return body;
        }

    }

    public WeatherResponse postWeather(String city){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "value");

        String body = "{\n" +
                "    \"title\": \"Afternoon\",\n" +
                "    \"content\": \"Went to shopping\"\n" +
                "}";

        HttpEntity<String> httpEntity = new HttpEntity<>(body);

//        User user = User.builder().userName("ram").password("ram").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user,httpHeaders);

        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
        return response.getBody();
    }
}
