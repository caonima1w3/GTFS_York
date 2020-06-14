package Weighted;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class WeightedGraph {

	int currNode = 0;
	int verticesNum;
	List<Integer>[] adj;

	public WeightedGraph(int verticesNum) {
		this.verticesNum = verticesNum;
		adj = new List[verticesNum];
		for (int i = 0; i < verticesNum; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

}
