package Model;

/**
 * This class represents the vertices of the graph, which are different stops of the subway.
 */
public class Stop {
	public int stop_id;
	public String stop_name;
	public double stop_lat;
	public double stop_lon;

	/**
	 *
	 * @param stop_id the unique id corrosponding to each station
	 * @param stop_name name of the station
	 * @param stop_lat latitude of the station[
	 * @param stop_lon Longitude of the station
	 */
	public Stop(int stop_id, String stop_name, double stop_lat, double stop_lon) {
		super();
		this.stop_id = stop_id;
		this.stop_name = stop_name;
		this.stop_lat = stop_lat;
		this.stop_lon = stop_lon;
	}

	/**
	 *
	 * @return id of the station, which is here the vertices' id
	 */
	public int getStop_id() {
		return stop_id;
	}

	/**
	 *
	 * @param stop_id return stop id
	 */
	public void setStop_id(int stop_id) {
		this.stop_id = stop_id;
	}

	/**
	 *
	 * @return name of the station
	 */
	public String getStop_name() {
		return stop_name;
	}

	/**
	 * sets the name of the station
	 * @param stop_name
	 */
	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	/**
	 *
	 * @return the latitude of the station
	 */
	public double getStop_lat() {
		return stop_lat;
	}

	/**
	 * sets latitude of the station
	 * @param stop_lat
	 */
	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	/**
	 * gets the stop latitude
	 * @return stop latitude
	 */
	public double getStop_lon() {
		return stop_lon;
	}

	/**
	 * setStop stop longitude
	 * @param stop_lon
	 */
	public void setStop_lon(double stop_lon) {
		this.stop_lon = stop_lon;
	}
}
