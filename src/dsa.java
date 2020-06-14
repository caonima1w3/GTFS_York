import java.util.ArrayList;

public class dsa {
	public static void main(String[] args) {
		ArrayList<Integer> stops = new ArrayList<Integer>();
		stops.add(1);
		stops.add(2);
		stops.add(3);
		stops.add(4);
		stops.add(5);
		
		stops.remove(1);
		
		for(int i:stops) {
			System.out.println(i);
		}
	}
}
