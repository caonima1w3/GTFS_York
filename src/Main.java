import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Edge;
import Model.Stop;

public class Main {
	static ReadCSV rc;

	public static ArrayList<ArrayList<Integer>> removeDuplicates(ArrayList<ArrayList<Integer>> list) {
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> element : list) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	public static ArrayList<Stop> getStopsList(ArrayList<String[]> stops) {
		ArrayList<Stop> stops_list = new ArrayList<Stop>();
		for (int i = 0; i < stops.size(); i++) {
			int stop_id = Integer.parseInt(stops.get(0)[0]);
			String stop_name = stops.get(0)[2];
			double stop_lat = Double.valueOf(stops.get(0)[5]);
			double stop_lon = Double.valueOf(stops.get(0)[6]);
			Stop stop = new Stop(stop_id, stop_name, stop_lat, stop_lon);
			stops_list.add(stop);
		}
		return stops_list;
	}

	public static void main(String[] args) {

		// Each complete trip
		ArrayList<String[]> stop_times = rc.readCSV("stop_times.csv");
		int cur_trip_id = 0;
		ArrayList<Integer> stop_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> line_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < stop_times.size(); i++) {
			int stop_times_trip_id = Integer.parseInt(stop_times.get(i)[0]);
			int stop_times_stop_id = Integer.parseInt(stop_times.get(i)[3]);
			if (cur_trip_id != stop_times_trip_id) {
				cur_trip_id = stop_times_trip_id;
				line_list.add(stop_list);
				stop_list = new ArrayList<Integer>();
			}
			stop_list.add(stop_times_stop_id);
		}

		// There are many repetition in stop_time, remove duplicate
		line_list = removeDuplicates(line_list);

		// !处理stop相交问题
		// Store in Map,  Map( Edge, distance=0 )
		int first_stop = 0;
		int second_stop = 0;
		Map map = new HashMap();
		for (ArrayList<Integer> stop_list_i : line_list) {
			ArrayList<Integer> curr_stop_list = new ArrayList<Integer>();
			for (int stop_id : stop_list_i) {
				System.out.print(stop_id + " ");
				if (first_stop == 0) {
					first_stop = stop_id;
					continue;
				}
				second_stop = stop_id;
				curr_stop_list.add(second_stop);
				map.put(first_stop, curr_stop_list);
				first_stop = stop_id;
			}
			System.out.println();
		}

		// Weighted graph, Map( Edge, distance )
		// Read Stop.csv
		ArrayList<String[]> stops = rc.readCSV("stops.csv");
		ArrayList<Stop> stops_list = getStopsList(stops);
		// for example: first stop
		Stop stop = stops_list.get(0);
		System.out.println("stop_id: " + stop.getStop_id());
		System.out.println("stop_name: " + stop.getStop_name());
		System.out.println("stop_lat: " + stop.getStop_lat());
		System.out.println("stop_lon: " + stop.getStop_lon());
		
		// Update distance in map
//		for(Edge edge : map.keySet()){
//			Stop stop1 = edge.stop1;
//			Stop stop2 = edge.stop2;
//			double distance = Math.sqrt(Math.pow((stop1.getStop_lat() - stop2.getStop_lat()),2) - Math.pow((stop1.getStop_lon() - stop2.getStop_lon()),2));
//			map.put(edge, distance);
//		}
		
		
	}
}
