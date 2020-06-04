package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPath {

	static final int INF = (int) 1e9; // don't increase, avoid overflow
	static ArrayList<Edge>[] adjList;
	static int V;

	/*
	 * 1. Dijkstra's Algorithm for SSSP on weighted graphs
	 */
	static int dijkstra(int S, int T) // O(E log E)
	{
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		dist[S] = 0;
		pq.add(new Edge(S, 0)); // may add more in case of MSSP (Mult-Source)
		while (!pq.isEmpty()) {
			Edge cur = pq.remove();
			if (cur.node == T) // remove if all computations are needed
				return dist[T];
			if (cur.cost > dist[cur.node]) // lazy deletion
				continue;
			for (Edge nxt : adjList[cur.node])
				if (cur.cost + nxt.cost < dist[nxt.node])
					pq.add(new Edge(nxt.node, dist[nxt.node] = cur.cost + nxt.cost));
		}
		return -1;
	}

	/*
	 * 2. Bellman-Ford's Algorithms for SSSP on weighted graphs with negative cycles
	 */
	static boolean bellmanFord(int S) {
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		boolean modified = true;
		for (int k = 0; modified && k < V - 1; ++k) {
			modified = false;
			for (int u = 0; u < V; ++u) // these two loops run in O(E) in total
				for (Edge nxt : adjList[u])
					if (dist[u] + nxt.cost < dist[nxt.node]) {
						modified = true;
						dist[nxt.node] = dist[u] + nxt.cost;
					}
		}

		boolean hasNegCycle = false;
		for (int u = 0; u < V; ++u)
			for (Edge nxt : adjList[u])
				if (dist[u] + nxt.cost < dist[nxt.node])
					hasNegCycle = true;
		return hasNegCycle;
	}
	
	public static void BFS_weighted(int s) {
		int[] dist = new int[V];
		int[] parent =new int[V];
		Arrays.fill(dist, INF);
		Queue<Integer> q= new LinkedList<>();
		q.add(s);
		dist[s]=0;
		while(!q.isEmpty()) {
			int cur = q.remove();
			for(Edge next: adjList[cur]) {
				if(dist[cur]+next.cost<dist[next.node]) {
					dist[next.node]=dist[cur]+next.cost;
					q.add(next.node);
					parent[next.node]=cur;
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int node, cost;

		Edge(int a, int b) {
			node = a;
			cost = b;
		}

		public int compareTo(Edge e) {
			return cost - e.cost;
		}
	}
}
