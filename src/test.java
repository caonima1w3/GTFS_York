import Model.Edge;
import Model.Stop;
import Unweighted.UnweightedGraph;

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
		UnweightedGraph ug = new UnweightedGraph(stops.size(), unweighed_edge_list);
		
		// BFS shortest path in unweighted Graph
//		int test_stop_id = 10;
//		List<Integer> stopVisitedList = BFSShortestPath.bfs( test_stop_id, ug);
		
		
		// Build Weighted Graph
		// ...