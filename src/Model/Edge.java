package Model;

public class Edge {
	public int stop1_id;
	public int stop2_id;
	
	public Edge(int stop1_id, int stop2_id) {
		super();
		this.stop1_id = stop1_id;
		this.stop2_id = stop2_id;
	}
	
	public int getStop1_id() {
		return stop1_id;
	}

	public void setStop1_id(int stop1_id) {
		this.stop1_id = stop1_id;
	}

	public int getStop2_id() {
		return stop2_id;
	}

	public void setStop2_id(int stop2_id) {
		this.stop2_id = stop2_id;
	}
	
}
