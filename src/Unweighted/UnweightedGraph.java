package Unweighted;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import Model.Edge;

public class UnweightedGraph {

	int stopsNum;
	int curr_stop_id = 0;
	List<Integer>[] adj;

	public UnweightedGraph(int stopsNum, ArrayList<ArrayList<Integer>> trips_list) {
		this.stopsNum = stopsNum;
		
		// Map (stop_id, {stop1_id, stop2_id})
		int first_stop_id = 0;
		int second_stop_id = 0;
		Map<Integer, ArrayList<Integer>> uw_map = new HashMap<Integer, ArrayList<Integer>>();

		for (ArrayList<Integer> stop_list_i : trips_list) {
			for (int stop_id : stop_list_i) {
				System.out.print(stop_id + " ");
				if (first_stop_id == 0) {
					first_stop_id = stop_id;
					continue;
				}
				second_stop_id = stop_id;
				
				// add into map,2¸öÏà»¥
				ArrayList<Integer> stop1_neighbors = uw_map.get(first_stop_id);
				// Add in list if it doesnt existe in list.
				if (stop1_neighbors == null) {
					stop1_neighbors = new ArrayList<Integer>();
					stop1_neighbors.add(second_stop_id);
				}
				if (stop1_neighbors != null && !stop1_neighbors.contains(second_stop_id)) {
					stop1_neighbors.add(second_stop_id);
				}
				uw_map.put(first_stop_id, stop1_neighbors);
				first_stop_id = stop_id;
			}
			System.out.println();
		}


		for (int stop_id : uw_map.keySet()) {
			System.out.print(stop_id + " neighbors: ");
			ArrayList<Integer> list = uw_map.get(stop_id);
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	


}
