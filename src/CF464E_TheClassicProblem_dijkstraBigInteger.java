import java.util.*;

import java.io.*;
import java.math.BigInteger;

public class CF464E_TheClassicProblem_dijkstraBigInteger {
	static final long INF = (long) 1e14; // don't increase, avoid overflow
	static int n, m;
	static TreeSet<Integer>[] dist;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		n = sc.nextInt();
		m = sc.nextInt();

		ArrayList<Edge>[] graph = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<Edge>();
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		int s = sc.nextInt(), e = sc.nextInt();
		dist = new TreeSet[n + 1];
		int[] parent = new int[n + 1];
		dijkstra(graph, s, e, parent);
//			System.out.println(dist[e]);
//			System.out.println(Arrays.toString(parent));
		if (dist[e] == null)
			out.println(-1);
		else {
			TreeSet<Integer> ts = dist[e];
			int[] num = new int[ts.last() + 1];
			StringBuilder sb = new StringBuilder(ts.last() + 1);
			for (int x : ts)
				num[x] = 1;
			for (int i = num.length - 1; i >= 0; i--)
				sb.append(num[i]);

			BigInteger a = new BigInteger(sb.toString(), 2);
			a = a.mod(new BigInteger("1000000007"));
			out.println(a.toString());
			int ref = e;
			LinkedList<Integer> l = new LinkedList<Integer>();
			while (ref != -1) {
				l.addFirst(ref);
				ref = parent[ref];
			}
			out.println(l.size());
			for (int x : l)
				out.print(x + " ");
		}
		out.close();
	}

	public static void dijkstra(ArrayList<Edge>[] graph, int s, int e, int[] parent) {
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		Path p = new Path(s);
		pq.add(p);
		dist[s] = p.ts;
		parent[s] = -1;
		while (!pq.isEmpty()) {
			Path cur = pq.remove();
			if (cur.node == e) // remove if all computations are needed
				return;
			if (compare(cur.ts, dist[cur.node]) > 0)
				continue;
//				System.out.println(cur.ts);
			for (Edge nxt : graph[cur.node]) {
				TreeSet<Integer> ts2 = (TreeSet<Integer>) cur.ts.clone();
				addTS(ts2, nxt.cost);
				if (dist[nxt.node] == null || compare(ts2, dist[nxt.node]) < 0) {
					dist[nxt.node] = ts2;
					p = new Path(nxt.node);
					p.ts = ts2;
					pq.add(p);
					parent[nxt.node] = cur.node;
				}
			}
		}
	}

	public static void addTS(TreeSet<Integer> ts, int x) {
		if (ts.contains(x)) {
			ts.remove(x);
			addTS(ts, x + 1);
		} else
			ts.add(x);
	}

	public static int compare(TreeSet<Integer> ts1, TreeSet<Integer> ts2) {
		Iterator<Integer> it1 = ts1.descendingIterator();
		Iterator<Integer> it2 = ts2.descendingIterator();
		while (it1.hasNext() && it2.hasNext()) {
			int i = it1.next(), j = it2.next();
			if (i > j)
				return 1;
			else if (i < j)
				return -1;
		}
		if (it1.hasNext())
			return 1;
		else if (it2.hasNext())
			return -1;
		else
			return 0;
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

	static class Path implements Comparable<Path> {
		int node;
		TreeSet<Integer> ts;

		Path(int n) {
			node = n;
			ts = new TreeSet<Integer>();
		}

		@Override
		public int compareTo(Path o) {
			return compare(ts, o.ts);
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
