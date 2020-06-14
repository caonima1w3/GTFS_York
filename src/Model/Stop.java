package Model;

public class Stop {
	public int stop_id;
	public String stop_name;
	public double stop_lat;
	public double stop_lon;

	public Stop(int stop_id, String stop_name, double stop_lat, double stop_lon) {
		super();
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_lat = stop_lat;
		this.stop_lon = stop_lon;
	}

	public int getStop_id() {
		return stop_id;
	}

	public void setStop_id(int stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public double getStop_lat() {
		return stop_lat;
	}

	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	public double getStop_lon() {
		return stop_lon;
	}

	public void setStop_lon(double stop_lon) {
		this.stop_lon = stop_lon;
	}

}
