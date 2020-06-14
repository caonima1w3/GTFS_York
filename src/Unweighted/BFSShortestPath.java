package Unweighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BFSShortestPath {
	
	static int resourceNode;
	static ArrayList<ArrayList<Integer>> previousList = new ArrayList<ArrayList<Integer>>();
	
	public static ArrayList<Integer> bfs(int startNode, int target, UnweightedGraph g) {
		resourceNode = startNode;
		// Init
		if(g.curr_stop_id==0) g.curr_stop_id = startNode;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Map (stop_id, boolean)
		Map<Integer, Boolean> visited_map = initVisitedMap(g);
		
		// Visit
		queue.add(startNode);
		boolean end = false;

		while (!end) {
			int node = g.curr_stop_id;
			ArrayList<Integer> neighbors_list = g.uw_map.get(node);
			ArrayList<Integer> previousArray = new ArrayList<Integer>();
			for(int neighbor: neighbors_list) {
				// if doesnt visited
				if(!visited_map.get(neighbor)) {
					queue.add(neighbor);
					visited_map.put(neighbor, true); // note visited
					previousArray.add(neighbor);
				}
			}
			previousList.add(previousArray);
			if (queue.isEmpty())
				end = true;
			else
				g.curr_stop_id = queue.removeFirst();
		}
		
		
		return getShortestPathTo(previousList, target);
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
	
	public static ArrayList<Integer> getShortestPathTo(ArrayList<ArrayList<Integer>> previousList, int target) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(resourceNode);
		// Add each first element until target
		for(ArrayList<Integer> list:previousList) {
			if(list.contains(target)) {
				path.add(target);
				break;
			}else {
				path.add(list.get(0));
			}
		}
		return path;
	}
	
}
