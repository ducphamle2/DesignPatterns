package duc.vn.learning.design.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

interface WeatherDataInterface {
	
	void registerObserver(Observer obs);
	void removeObserver(Observer obs);
	void updateObserver(Observer obs);
	void notifyObservers();
}

/*
 * Applying Observer Pattern
 */
public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		Conditions curConditions = new Conditions(weatherData);
		
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("temperature");
		keys.add("humidity");
		ArrayList<Double> values = new ArrayList<Double>();
		values.add(80.0);
		values.add(72.0);
		
		weatherData.setMeasurements(keys, values);
	}
}

class WeatherData extends Observable {
	
	private HashMap<String, Double> weatherData;
	
	public WeatherData() {
		weatherData = new HashMap<String, Double>();
	}
	
	public void setMeasurements(ArrayList<? extends String> keys, ArrayList<? extends Double> values) {
		// if size is not match => keys and values are not compatible
		if (keys.size() != values.size()) {
			return;
		}
		for (String key : keys) {
			// index of key must be identical to index of value
			this.weatherData.put(key, values.get(keys.indexOf(key))); 
		}
		measurementsChanged();
	}
	
	public Double getDataValue(String key) {
		if (this.weatherData.containsKey(key)) {
			return this.weatherData.get(key);
		}
		return -1.0;
	}
	
	public HashMap<String, Double> getWeatherData() {
		return this.weatherData;
	}

	public void measurementsChanged() {
		setChanged();
		notifyObservers();
	}
}

interface DisplayBehaviour {
	void display();
}

class Conditions implements Observer, DisplayBehaviour {
	
	private static final String TEMPERATURE = "temperature";
	private static final String HUMIDITY = "humidity";
	private WeatherData weatherData;
	
	public Conditions(WeatherData weatherData) {
		this.weatherData = weatherData;
		this.weatherData.addObserver(this);
	}
	
	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) obs;
			this.weatherData = weatherData;
			display();
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (weatherData.getWeatherData().containsKey(TEMPERATURE)
				&& weatherData.getWeatherData().containsKey(HUMIDITY)) {
			System.out.println("temperature: " + weatherData.getDataValue(TEMPERATURE));
			System.out.println("humidity: " + weatherData.getDataValue(HUMIDITY));
		}
	}
}

class Statistics implements Observer, DisplayBehaviour {

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

class Forecast implements Observer, DisplayBehaviour {

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}