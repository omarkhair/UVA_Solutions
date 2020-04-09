import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class CF160D_EdgesInMST {
	static ArrayList<Edge>[] graph;
//	static boolean[] visited;
	static Edge[] arr;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		int n=sc.nextInt();
		int m=sc.nextInt();
		graph= new ArrayList[n];
		for(int i=0;i<n;i++)
			graph[i]=new ArrayList<Edge>();
		String[] ans= new String[m];
//		visited= new boolean[n];
		arr=new Edge[m];
		for(int i=0;i<m;i++) {
			int u=sc.nextInt()-1;
			int v=sc.nextInt()-1;
			int w=sc.nextInt();
			Edge e= new Edge(i,u,v,w);
			arr[i]=e;
			graph[u].add(e);
			graph[v].add(e);
		}
		Arrays.parallelSort(arr);
		DisjointSet ds= new DisjointSet(n);
		for(int i=0;i<m;i++) {
//			ArrayList<Edge> l= new ArrayList<Edge>();
			Edge e= arr[i];
//			while(i<m && arr[i].w==e.w)
//				l.add(arr[i++]);
				if(!ds.isSameSet(e.u, e.v)) {
					ds.unionSet(e.u, e.v);
					if(i<m-1 && arr[i+1].w==e.w && e.hasCommonVertex(arr[i+1]))
						ans[e.i]="at least one";
					else
						ans[e.i]="any";
				}
				else
					ans[e.i]="none";	
		}
		for(int i=0;i<n;i++) {

		}
		System.out.println(Arrays.deepToString(ans));
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
	static class Edge implements Comparable<Edge>{
		int i,u, v, w;
		public Edge(int i,int u, int v, int w) {
			this.i=i;
			this.u=u;
			this.v=v;
			this.w=w;
		}
		@Override
		public int compareTo(Edge o) {
			if(this.w==o.w)
				return this.u-o.u;
			return this.w-o.w;
		}
		public String toString() {
			return +v+" "+w;
		}
		public boolean hasCommonVertex(Edge o) {
			return (u==o.u || u==o.v || v==o.u || v==o.v);
		}
	}
}
//HashMap<Integer, ArrayList<Edge>> hm= new HashMap<Integer, ArrayList<Edge>>();
//for(Edge a: l) {
//	if(!hm.containsKey(a.u))
//		hm.put(a.u,new ArrayList<Edge>());
//	if(!hm.containsKey(a.v))
//		hm.put(a.v,new ArrayList<Edge>());
//	hm.get(a.u).add(a);
//	hm.get(a.v).add(a);
//}
//for(Entry<Integer, ArrayList<Edge>> entry: hm.entrySet()) {
//	ArrayList<Edge> l2=entry.getValue();
//	
//}
