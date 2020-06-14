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

	public static Stop getStopById(int stopId, ArrayList<Stop> stops) {
		for (Stop s : stops) {
			if (stopId == s.stop_id) {
				return s;
			}
		}
		return null;
	}

	public static double calculDistanceByGEO(double lat1, double lon1, double lat2, double lon2) {
		// TODO

		return 0;
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

		// Unweighed graph, store in a Map( Edge, distance=0.0 )
		int first_stop = 0;
		int second_stop = 0;
		Map<Edge, Double> map = new HashMap<Edge, Double>();
		for (ArrayList<Integer> stop_list_i : line_list) {
			for (int stop_id : stop_list_i) {
				System.out.print(stop_id + " ");
				if (first_stop == 0) {
					first_stop = stop_id;
					continue;
				}
				second_stop = stop_id;
				// add Edge
				Edge edge = new Edge(first_stop, second_stop);
				map.put(edge, 0.0);
				first_stop = stop_id;
			}
			System.out.println();
		}

		// Weighted graph, Map( Edge, distance )

		// Read Stop.csv for getting position
		ArrayList<String[]> stops = rc.readCSV("stops.csv");
		ArrayList<Stop> stops_list = getStopsList(stops);
		// Update distance in map
		for (Edge edge : map.keySet()) {
			int stop1_id = edge.stop1_id;
			Stop stop1 = getStopById(stop1_id, stops_list);
			int stop2_id = edge.stop2_id;
			Stop stop2 = getStopById(stop2_id, stops_list);
			if (stop1 != null && stop2 != null) {
				double distance = calculDistanceByGEO(stop1.getStop_lat(), stop1.getStop_lon(), stop2.getStop_lat(), stop2.getStop_lon());
				map.put(edge, distance);
			}
		}

		// print
		for (Edge edge : map.keySet()) {
//			System.out.println(edge.stop1_id + " - " + edge.stop2_id + ": distance " + map.get(edge));
		}
	}
}
