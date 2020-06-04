
import java.util.*;
import java.io.*;

public class UVA11813_shopping_Dijkstra_TSP {
	static final long INF = (long) 1e17; // don't increase, avoid overflow
	static int n, m, nStores;
	static HashSet<Integer> hs;
	static long[][] map;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tt=sc.nextInt();
		while (tt-- > 0) {
			n=sc.nextInt();
			m = sc.nextInt();
			ArrayList<Edge>[] graph = new ArrayList[n];
			long[] dist= new long[n];
			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Edge>();
			}
			while (m-- > 0) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				graph[u].add(new Edge(v, w));
				graph[v].add(new Edge(u, w));
			}
			nStores=sc.nextInt()+1;
			map=new long[nStores][nStores];
			hs = new HashSet<Integer>();
			int[] store= new int[nStores];
			hs.add(0);
			for(int i=1;i<nStores; i++) {
				store[i]=sc.nextInt();
				hs.add(store[i]);
			}
			for(int i=0;i<nStores; i++) {
				dijkstra(store[i], graph, dist);
				for(int j=0;j<nStores; j++)
					map[i][j]=dist[store[j]];
			}
			hs.clear();
			for(int i=1;i<nStores; i++) hs.add(i);
			out.println(TSP(0,hs));
		}
		out.close();
	}
	public static long TSP(int i, HashSet<Integer> hs ) {
		if(hs.isEmpty())
			return map[i][0];
		long ans=INF;
		HashSet<Integer> tmp= (HashSet<Integer>) hs.clone();
		for(int x: hs) {
			tmp.remove(x);
			ans=Math.min(ans, map[i][x]+TSP(x,tmp));
			tmp.add(x);
		}
		return ans;
	}
	public static void dijkstra(int s, ArrayList<Edge>[] graph, long[] dist) {
		int rem=nStores;
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue();
		pq.add(new Edge(s, 0));
		dist[s] = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.remove();
			if (cur.cost > dist[cur.node])
				continue;
			if(hs.contains(cur.node))
				if(--rem==0) break;
			for (Edge next : graph[cur.node])
				if (!next.neglect && cur.cost + next.cost < dist[next.node]) {
					pq.add(new Edge(next.node, dist[next.node] = cur.cost + next.cost));
				}
		}
	}

	static class Edge implements Comparable<Edge> {
		int node;
		long cost;
		boolean neglect;

		Edge(int a, long b) {
			node = a;
			cost = b;
		}

		public int compareTo(Edge e) {
			return Long.compare(cost, e.cost);

		}
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}
}
