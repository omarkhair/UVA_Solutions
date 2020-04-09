package Algorithms;

import java.util.*;
import java.awt.image.SampleModel;
import java.io.*;

public class Kruskal_MST_DSU {
	static ArrayList<Edge>[] graph;
	static boolean[] visited;
	static Edge[] edge;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		int n=sc.nextInt();
		int m=sc.nextInt();
		graph= new ArrayList[n];
		visited= new boolean[n];
		edge= new Edge[m];
		for(int i=0;i<n;i++)
			graph[i]=new ArrayList<Edge>();
		for(int i=0;i<m;i++) {
			int u=sc.nextInt()-1;
			int v=sc.nextInt()-1;
			int w=sc.nextInt();
			Edge e= new Edge(u, v, w);
			graph[u].add(e);
			graph[v].add(e);
			edge[i]=e;
		}
		Arrays.sort(edge);
		int mstCost=0;
		DisjointSet ds= new DisjointSet(n);
		for(int i=0;i<m;i++) {
			Edge e= edge[i];
			if(!ds.isSameSet(e.u, e.v)) {
				mstCost+=e.w;
				ds.unionSet(e.u, e.v);
			}
		}
		System.out.println(mstCost);
	}
	
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge( int u, int v, int w) {
			this.u=u;
			this.v=v;
			this.w=w;
		}
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
		public String toString() {
			return u+" "+v+" "+w;
		}
	}
	
	static class DisjointSet {                                              
		int[] p, rank, setSize;
		int numSets;

		public DisjointSet(int N) 
		{
			p = new int[numSets = N];
			rank = new int[N];
			setSize = new int[N];
			for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
		}

		public int findSet(int i) { return p[i] == i ? i : (p[i] = findSet(p[i])); }

		public boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

		public void unionSet(int i, int j) 
		{ 
			if (isSameSet(i, j)) 
				return;
			numSets--; 
			int x = findSet(i), y = findSet(j);
			if(rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
			else
			{	p[x] = y; setSize[y] += setSize[x];
				if(rank[x] == rank[y]) rank[y]++; 
			} 
		}

		public int numDisjointSets() { return numSets; }

		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
	}
}
