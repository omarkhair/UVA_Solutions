package Algorithms;

import java.util.LinkedList;
import java.util.Stack;

public class StronglyConnectedComponents {

	static LinkedList<Integer>[] graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// Java implementation of Kosaraju's algorithm to print all SCCs 
//	Algorithm:
//	1. Call a series of DFS on the graph and push in Stack.
//	2. Get the transpose of Graph: reverse each directed edge.
//	3. Call a series of DFS on the transpose by the order of the stack: each call represents a SCC. 
	public static void SCC() {
		int n=graph.length;
		Stack<Integer> s= new Stack<Integer>();
		boolean[] visited= new boolean[n];
		for(int i=0;i<n;i++)
			if(!visited[i])
				dfs(i,visited,s);
		LinkedList<Integer>[] graphT= getTranspose();
		visited= new boolean[n];
		while(!s.isEmpty()) {
			int v=s.pop();
			if(!visited[v]) {
				dfs(v,visited);
			}
		}
	}
	
	public static void dfs(int node, boolean[] visited, Stack s) {
		visited[node]=true;
		for(int x: graph[node]) {
			if(!visited[x]) {
				dfs(x,visited,s);
			}
		}
		s.push(node);
	}
	public static void dfs(int node, boolean[] visited) {
		visited[node]=true;
		System.out.print(node+" ");
		for(int x: graph[node]) {
			if(!visited[x]) {
				dfs(x,visited);
			}
		}
	}
	public static LinkedList<Integer>[] getTranspose() 
    { 
		int n=graph.length;
		LinkedList<Integer>[] g= new LinkedList[n];
		for(int i=0;i<n;i++)
			g[i]=new LinkedList<Integer>();
		for(int u=0;u<n;u++) {
			for(int v:graph[u])
				g[v].add(u);
		}
        return g; 
    } 
}
