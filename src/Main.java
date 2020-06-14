import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Edge;
import Model.Stop;
import Unweighted.BFSShortestPath;
import Unweighted.UnweightedGraph;

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
			int stop_id = Integer.parseInt(stops.get(i)[0]);
			String stop_name = stops.get(i)[2];
			double stop_lat = Double.valueOf(stops.get(i)[5]);
			double stop_lon = Double.valueOf(stops.get(i)[6]);
			Stop stop = new Stop(stop_id, stop_name, stop_lat, stop_lon);
			stops_list.add(stop);
		}
		return stops_list;
	}

	public static Stop getStopById(int stopId, ArrayList<Stop> stops) {
		for (Stop s : stops) {
			int stop_id = s.stop_id;
			if (stop_id == stopId) {
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
		ArrayList<Integer> stop_list = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> trips_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < stop_times.size(); i++) {
			int stop_times_stop_id = Integer.parseInt(stop_times.get(i)[3]);
			int stop_sequence = Integer.parseInt(stop_times.get(i)[4]);

			if (stop_sequence == 1) {
				trips_list.add(stop_list);
				stop_list = new ArrayList<Integer>();
			}
			stop_list.add(stop_times_stop_id);
		}
		trips_list.remove(0); // remove first empty element

		// There are many repetition in stop_time, remove duplicate
		trips_list = removeDuplicates(trips_list);

		// Unweighed graph
		// Store in a List<Edge> which has (stop1_id,stop2_id, 0.0)
		int first_stop_id = 0;
		int second_stop_id = 0;
		ArrayList<Edge> unweighed_edge_list = new ArrayList<Edge>();
		for (ArrayList<Integer> stop_list_i : trips_list) {
			for (int stop_id : stop_list_i) {
				System.out.print(stop_id + " ");
				if (first_stop_id == 0) {
					first_stop_id = stop_id;
					continue;
				}
				second_stop_id = stop_id;
				// add Edge
				Edge edge = new Edge(first_stop_id, second_stop_id, 0.0);
				unweighed_edge_list.add(edge);
				first_stop_id = stop_id;
			}
			System.out.println();
		}
	
	
		// Weighted graph
		// List<Edge> which has (stop1_id,stop2_id, distance)
		ArrayList<Edge> weighed_edge_list = unweighed_edge_list;
		// Read Stop.csv for getting position
		ArrayList<String[]> stops = rc.readCSV("stops.csv");
		ArrayList<Stop> stops_list = getStopsList(stops);
		// Update distance in map
		int index = 0;
		for (Edge edge : weighed_edge_list) {
			int stop1_id = edge.stop1_id;
			Stop stop1 = getStopById(stop1_id, stops_list);
			int stop2_id = edge.stop2_id;
			Stop stop2 = getStopById(stop2_id, stops_list);
			if (stop1 != null && stop2 != null) {
				edge.distance = calculDistanceByGEO(stop1.getStop_lat(), stop1.getStop_lon(), stop2.getStop_lat(),
						stop2.getStop_lon());
				weighed_edge_list.set(index, edge);
			}
			index++;
		}

		// print
		for (Edge edge : weighed_edge_list) {
//			System.out.println(edge.stop1_id + " - " + edge.stop2_id + ": distance " + edge.distance);
		}
		
		
		// Build Unweighted Graph
		UnweightedGraph ug = new UnweightedGraph(stops.size());
		
		// BFS shortest path in unweighted Graph
		int test_stop_id = 10;
		List<Integer> stopVisitedList = BFSShortestPath.bfs( test_stop_id, ug);
		
		
		// Build Weighted Graph
		// ...
		
		
		
	}
}
