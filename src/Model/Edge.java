package Model;

/**
 * This class represents the edges between two vertex. the edge is created between stop1 vertex and
 * and stop2 vertex. If we have unweighted graph the distance is 0 otherwise is a number.
 */

public class Edge {
	public int stop1_id;
	public int stop2_id;
	public double distance;
	
	public Edge(int stop1_id, int stop2_id, double distance) {
		super();
		this.stop1_id = stop1_id;
		this.stop2_id = stop2_id;
		this.distance = distance;
	}

	/**
	 *
	 * @return the id of the stop1
	 */
	public int getStop1_id() {
		return stop1_id;
	}

	/**
	 * sets stop 1 id
	 * @param stop1_id
	 */
	public void setStop1_id(int stop1_id) {
		this.stop1_id = stop1_id;
	}

	/**
	 *
	 * @return stop2 id
	 */
	public int getStop2_id() {
		return stop2_id;
	}

	/**
	 * sets stop 2 id
	 * @param stop2_id
	 */
	public void setStop2_id(int stop2_id) {
		this.stop2_id = stop2_id;
	}

	/**
	 * gets the weight between two vertices.
	 * @return
	 */
	public double getWeight() {
		return distance;
	}

	/**
	 * sets the weight between two vertices.
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.distance = weight;
	}
}
