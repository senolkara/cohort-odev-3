package com.senolkarakurt.odev3.service.impl;

import com.senolkarakurt.odev3.service.WeatherForecastService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WeatherForecastServiceImpl implements WeatherForecastService {
    @Override
    public String getRealTimeWeatherForecast(String apikey, String location) {
        requestControl(apikey, location);
        String responseData = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.tomorrow.io/v4/weather/realtime" +
                        "?location=" + location +
                        "&apikey=" + apikey)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                ResponseBody responseBody = response.body();
                responseData = responseBody.string();
                System.out.println(responseData);
            } else {
                System.out.println("Request not successful: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    public String getHistoricalWeatherForecast(String apikey, String location, String timesteps) {
        requestControl(apikey, location);
        String responseData = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getHistoricalWeatherForecastApiUrl(apikey, location, timesteps))
                .get()
                .addHeader("accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                responseData = response.body().string();
                System.out.println(responseData);
            } else {
                System.out.println("Request not successful: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    private String getHistoricalWeatherForecastApiUrl(String apikey, String location, String timesteps){
        String url;
        if (timesteps == null){
            url = "https://api.tomorrow.io/v4/weather/history/recent" +
                    "?location=" + location +
                    "&apikey=" + apikey;
        }
        else{
            url = "https://api.tomorrow.io/v4/weather/history/recent" +
                    "?location=" + location +
                    "&timesteps=" + timesteps +
                    "&apikey=" + apikey;
        }
        return url;
    }

    @Override
    public String getWeatherForecast(String apikey, String location, String timesteps) {
        requestControl(apikey, location);
        OkHttpClient client = new OkHttpClient();
        String responseData = null;
        Request request = new Request.Builder()
                .url(getWeatherForecastApiUrl(apikey, location, timesteps))
                .get()
                .addHeader("accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                responseData = response.body().string();
                System.out.println(responseData);
            } else {
                System.out.println("Request not successful: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    private String getWeatherForecastApiUrl(String apikey, String location, String timesteps){
        String url;
        if (timesteps == null){
            url = "https://api.tomorrow.io/v4/weather/forecast" +
                    "?location=" + location +
                    "&apikey=" + apikey;
        }
        else{
            url = "https://api.tomorrow.io/v4/weather/forecast" +
                    "?location=" + location +
                    "&timesteps=" + timesteps +
                    "&apikey=" + apikey;
        }
        return url;
    }

    private void requestControl(String apikey, String location){
        if (apikey == null || location == null){
            throw new RuntimeException("again control request");
        }
    }
}
