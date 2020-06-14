package Unweighted;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class BFSShortestPath {

	static boolean[] visited;
	static List<Integer> stopVisitedList = new ArrayList<Integer>();

	public BFSShortestPath(UnweightedGraph g) {
		visited = new boolean[g.stopsNum];
	}

	public static List<Integer> bfs( int startNode, UnweightedGraph g) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Init
		queue.add(g.curr_stop_id);
		visited[g.curr_stop_id] = true;
		stopVisitedList.add(g.curr_stop_id);
		boolean end = false;

		while (!end) {
			int node = g.curr_stop_id;
			for (int i = 0; i < g.adj[node].size(); i++) {
				int neighbor = g.adj[node].get(i);
				if (!visited[neighbor]) {
					queue.add(neighbor);
					visited[neighbor] = true;
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

	public static void main(String[] args) {

//		// 1. Test bfs
//		Graph g = new Graph(9); // vertices number
//		Bfs test = new Bfs( stop_id, g);

//		test.bfs(g);
//		for (int i : stopVisitedList) {
//			System.out.print(i + " ");
//		}

	}
}
