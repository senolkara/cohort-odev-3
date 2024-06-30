package com.senolkarakurt.odev3.controller;

import com.senolkarakurt.odev3.service.WeatherForecastService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/weatherForecast")
public class WeatherForecastController {

    private final WeatherForecastService weatherForecastService;

    @GetMapping("/realTimeWeatherForecast")
    public ResponseEntity<String> getRealTimeWeatherForecast(@RequestHeader(value = "apikey") String apikey,
                                                             @RequestParam(value = "location", defaultValue = "istanbul") String location) {
        return new ResponseEntity<>(weatherForecastService.getRealTimeWeatherForecast(apikey, location),
                HttpStatus.OK);
    }

    /**
     * minutely, hourly, daily
     * timesteps = 1m, 1h, 1d
     */
    @GetMapping("/historicalWeatherForecast")
    public ResponseEntity<String> getHistoricalWeatherForecast(@RequestHeader(value = "apikey") String apikey,
                                                           @RequestParam(value = "location", defaultValue = "istanbul") String location,
                                                           @RequestParam(value = "timesteps", defaultValue = "1h") String timesteps){
        return new ResponseEntity<>(weatherForecastService.getHistoricalWeatherForecast(apikey, location, timesteps),
                HttpStatus.OK);
    }

    /**
     * minutely, hourly, daily
     * timesteps = 1m, 1h, 1d
     */
    @GetMapping
    public ResponseEntity<String> getWeatherForecast(@RequestHeader(value = "apikey") String apikey,
                                                     @RequestParam(value = "location", defaultValue = "istanbul") String location,
                                                     @RequestParam(value = "timesteps", defaultValue = "1h") String timesteps){
        return new ResponseEntity<>(weatherForecastService.getWeatherForecast(apikey, location, timesteps),
                HttpStatus.OK);
    }

}
