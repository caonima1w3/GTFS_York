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
	/**
	 *reads "stop_times.csv" file, which contains Times that a vehicle arrives at and departs from
	 * individual stops for each trip. If stop_sequence is equal to 1 it means that new trip started.
	 * @return an array of array. Each inner array contains stop ids belonging to one completed trip.
	 */
	public static ArrayList<ArrayList<Integer>> getTripList() {
		// Read stop_times.csv, find each complete trip
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
		// There are many repetition in stop_time, remove duplicate trip
		trips_list = removeDuplicates(trips_list);
		
		return trips_list;
	}

	/**
	 *
	 * @param list of the existing trips. Input is array of array with stop ids.
	 * We create a new list and add the trip list to the new list if it does not already exist.
	 * @return  return list of the trips without duplicates
	 */
	public static ArrayList<ArrayList<Integer>> removeDuplicates(ArrayList<ArrayList<Integer>> list) {
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> element : list) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	/**
	 * In this method we read the list of the stops and create objects for each stop
	 * the stop_id, stop_name, stop_lat and stop_lon are given as a parameter to the
	 * Stop object.
	 * @param stops array of all stops.
	 * @return the list of all Stop pbjects. each object has stop_id, stop_name,
	 * stop_lat and stop_lon features.
	 */
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

	/**
	 * checks for the ids of the stops and return them
	 * @param stopId stop id
	 * @param stops list of stops
	 * @return reurns object corresponding to stop id
	 */
	public static Stop getStopById(int stopId, ArrayList<Stop> stops) {
		for (Stop s : stops) {
			int stop_id = s.stop_id;
			if (stop_id == stopId) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Calculate distance between two points in latitude and longitude.
	 * we do not take into account the  height so el1 and el2 is equal to 0. lat1, lon1
	 * are the start point lat2, lon2 are the end points.
	 * @returns Distance in Meters
	 */
	public static double calculDistanceByGEO(double lat1, double lon1, double lat2, double lon2) {
		double el1 = 0 ;
		double el2 = 0;
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;
		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);

	}

	public static void main(String[] args) {

		// Get trips list by stop_times.csv
		ArrayList<ArrayList<Integer>> trips_list = getTripList();

		// Build unweighed graph
		UnweightedGraph uwGraph = new UnweightedGraph(trips_list);

		// Build weighted graph
		ArrayList<Edge> weighed_edge_list = new ArrayList<Edge>();

		// Read Stop.csv for getting position
		ArrayList<String[]> stops = rc.readCSV("stops.csv");
		ArrayList<Stop> stops_list = getStopsList(stops);

		// Get all edges, build a Map(Edge, distance)
		for (int stop1_id : uwGraph.uw_map.keySet()) {
			ArrayList<Integer> neigbors_list = uwGraph.uw_map.get(stop1_id); //take neighboring nodes
			//this for loop is for finding all the distances between the vertex and its neighboring vertices.
			for (int stop2_id : neigbors_list) {
				Stop stop1 = getStopById(stop1_id, stops_list); // stop 1
				Stop stop2 = getStopById(stop2_id, stops_list);	//neighbor stop
				//computing distance between two vertices
				double distance = calculDistanceByGEO(stop1.getStop_lat(), stop1.getStop_lon(), stop2.getStop_lat(),
						stop2.getStop_lon());
				//creating adge between two vertices
				Edge edge = new Edge(stop1_id, stop2_id, distance);
				weighed_edge_list.add(edge);
			}
		}

		for (Edge edge : weighed_edge_list) {
 			System.out.println(edge.stop1_id + " - " + edge.stop2_id + ": distance " + edge.distance);
		}

		// BFS shortest path in unweighted Graph
		int test_start_stop_id = 7310;
		int test_target_stop_id = 1974;
		//ArrayList<Integer> bfsVisitedPath = BFSShortestPath.bfs( test_start_stop_id, test_target_stop_id,uwGraph);

//		System.out.println("=========== (BFS shortest path) 7310 to 1974 ===========");
//		for(int i:bfsVisitedPath) {
//			System.out.print(i+ " ");
//		}
//		System.out.println();
		
		 //Build Weighted Graph
		 //...

	}
}
