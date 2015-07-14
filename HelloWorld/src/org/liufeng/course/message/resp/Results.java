package org.liufeng.course.message.resp;

import java.util.LinkedList;
import java.util.List;

public class Results {
    private String currentCity;
    private String pm25;
    private LinkedList<Index> index;
    private List<WeatherData> weather_data;
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	
	public LinkedList<Index> getIndex() {
		return index;
	}
	public void setIndex(LinkedList<Index> index) {
		this.index = index;
	}
	public List<WeatherData> getWeather_data() {
		return weather_data;
	}
	public void setWeather_data(List<WeatherData> weather_data) {
		this.weather_data = weather_data;
	}
	
	
}
