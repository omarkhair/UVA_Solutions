import java.util.*;
import java.io.*;

public class UVA12144_AlmostShortestPath {
	static final long INF = (long) 1e17; // don't increase, avoid overflow
	static int n, m, s, e;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while ((n = sc.nextInt()) > 0) {
			m = sc.nextInt();
			s = sc.nextInt();
			e = sc.nextInt();
			ArrayList<Edge>[] graph = new ArrayList[n];
			ArrayList<Edge>[] graphT = new ArrayList[n];

			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<Edge>();
				graphT[i] = new ArrayList<Edge>();
			}
			while (m-- > 0) {
				int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
				graph[u].add(new Edge(v, w));
				graphT[v].add(new Edge(u, w));
			}
			long[] distSource = new long[n];
			dijkstra(s, graph, distSource);
			if (distSource[e] == INF)
				out.println(-1);
			else {
				long[] distEnd = new long[n];
				dijkstra(e, graphT, distEnd);
				for (int u = 0; u < n; u++) {
					for (Edge ed : graph[u])
						if (distSource[u] + distEnd[ed.node] + ed.cost == distSource[e])
							ed.neglect = true;
				}
				dijkstra(s, graph, distSource);
				out.println(distSource[e] == INF ? -1 : distSource[e]);
			}
		}
		out.close();
	}

	public static void dijkstra(int s, ArrayList<Edge>[] graph, long[] dist) {
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue();
		pq.add(new Edge(s, 0));
		dist[s] = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.remove();
			if (cur.cost > dist[cur.node])
				continue;

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
