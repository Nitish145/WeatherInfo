package com.example.weatherinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public TextView City_name;
    public EditText SearchBar;
    public Button SearchButton;
    public TextView Temperature;
    public TextView Min_Temp;
    public TextView Max_Temp;
    public TextView Humidity;
    public TextView Pressure;


    private static final String API_KEY = "b6907d289e10d714a6e88b30761fae22";
    private WeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service= BuilderAPI.getClient().create(WeatherService.class);

        City_name = findViewById(R.id.City_Name);
        SearchBar = findViewById(R.id.SearchBar);
        SearchButton = findViewById(R.id.SearchButton);
        Temperature = findViewById(R.id.Temperature);
        Min_Temp = findViewById(R.id.temp_min);
        Max_Temp = findViewById(R.id.temp_max);
        Humidity = findViewById(R.id.humidity);
        Pressure = findViewById(R.id.pressure);

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherData();
            }
        });

    }

    public void getWeatherData(){
        service.getWeatherData(SearchBar.getText().toString(), API_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                Log.d("ResponseSuccessful", "Response successful");

                WeatherResponse res = response.body();

                City_name.setText(SearchBar.getText());
                SearchBar.setText("");
                Temperature.setText(String.valueOf(res.getMain().getTemp()));
                Pressure.setText(String.valueOf(res.getMain().getPressure()));
                Humidity.setText(String.valueOf(res.getMain().getHumidity()));
                Min_Temp.setText(String.valueOf(res.getMain().getTempMin()));
                Max_Temp.setText(String.valueOf(res.getMain().getTempMax()));
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("ResponseFailure", "Response Failure");
            }
        });
    }
}
