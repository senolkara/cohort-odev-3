package com.senolkarakurt.odev3.service;

public interface WeatherForecastService {
    String getRealTimeWeatherForecast(String apikey, String location);
    String getHistoricalWeatherForecast(String apikey, String location, String timesteps);
    String getWeatherForecast(String apikey, String location, String timesteps);
}
