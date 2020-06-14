package Unweighted;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class UnweightedGraph {

	int stopsNum;
	int curr_stop_id = 0;
	List<Integer>[] adj;

	public UnweightedGraph(int stopsNum) {
		this.stopsNum = stopsNum;
		adj = new List[stopsNum];
		for (int i = 0; i < stopsNum; i++) {
			adj[i] = new ArrayList<>();
		}
	}
	
	// ��Ϊid���ǰ�˳��ģ����Ե�ʹ��Map
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

}
