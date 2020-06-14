package Unweighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BFSShortestPath {

	static List<Integer> stopVisitedList = new ArrayList<Integer>();

	public static List<Integer> bfs(int startNode, UnweightedGraph g) {
		
		// Init
		if(g.curr_stop_id==0) g.curr_stop_id = startNode;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Map (stop_id, boolean)
		Map<Integer, Boolean> visited_map = initVisitedMap(g);
		
		// Visit
		queue.add(g.curr_stop_id);
		stopVisitedList.add(g.curr_stop_id);
		boolean end = false;

		while (!end) {
			int node = g.curr_stop_id;
			ArrayList<Integer> neighbors_list = g.uw_map.get(node);
			for(int neighbor: neighbors_list) {
				if(!visited_map.get(neighbor)) {
					queue.add(neighbor);
					visited_map.put(neighbor, true); // note visited
					stopVisitedList.add(neighbor);
				}
			}
			if (queue.isEmpty())
				end = true;
			else
				g.curr_stop_id = queue.removeFirst();
		}
		return stopVisitedList;
	}
	
	public static Map<Integer, Boolean> initVisitedMap(UnweightedGraph g) {
		Map<Integer, Boolean> visited_map = new HashMap<Integer, Boolean>();
		for (int stop1_id : g.uw_map.keySet()) {
			visited_map.put(stop1_id, false);
			ArrayList<Integer> list = g.uw_map.get(stop1_id);
			for (int stop2_id : list) {
				visited_map.put(stop2_id, false);
			}
		}
		return visited_map;
	}
}
