import java.util.*;
import java.io.*;

public class UVA11514_Batman {
	static final long INF = (long) 1e17; // don't increase, avoid overflow
	static ArrayList<Pair> node;
	static ArrayList<ArrayList<Edge>> graph;
	static int P,V,E;
	static long[] dist;
	static String ans;
	static Power[] pow;
	static Villain[] vill;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while ((P=sc.nextInt())!=0) {
			V=sc.nextInt();
			E=sc.nextInt();
			pow= new Power[P];
			vill = new Villain[V];
			for(int i=0;i<P;i++)
				pow[i]=new Power(sc.next(), sc.nextInt(),sc.nextInt());
			for(int i=0;i<V;i++) {
				vill[i]=new Villain(sc.next(), sc.nextInt());
				StringTokenizer st= new StringTokenizer(sc.next(), ",");
				while(st.hasMoreTokens())
					vill[i].effect.add(st.nextToken());
			}
			graph= new ArrayList<ArrayList<Edge>>();
			node= new ArrayList<Pair>();
			// adding the nodes to the state-space graph
			for(int i=0;i<V;i++) {
				for(int j=i;j<=P-V+i;j++) {
					if(vill[i].health<=pow[j].attack 
							&& vill[i].effect.contains(pow[j].name)) {
						node.add(new Pair(i,j));
						graph.add(new ArrayList<Edge>());
					}
				}
			}
			// adding the edges : weight 0 for edges between same villain, weight power.energy for two consequent villains
			for(int i=0;i<graph.size();i++) {
				if(node.get(i).villidx==V-1) break;
				String curName=vill[node.get(i).villidx].name;
				String nxtName=vill[node.get(i).villidx+1].name;
				int reqEnergy= pow[node.get(i).powidx].energy;
				for(int j=i+1;j<graph.size(); j++) {
					String toName=vill[node.get(j).villidx].name;
//					System.out.println(curName+" "+toName);
					if(toName.equals(curName))
						graph.get(i).add(new Edge(j,0));
					else if (toName.equals(nxtName)) {
						if(node.get(i).powidx<node.get(j).powidx)
							graph.get(i).add(new Edge(j,reqEnergy));
					}
					else break;
				}
			}
			ans="No";
			dijkstra(0);
			System.out.println(ans);
		}
		out.close();
	}

	public static void dijkstra(int s) {
		int n=graph.size();
		dist = new long[n];
		Arrays.fill(dist, INF);
		PriorityQueue<Edge> pq = new PriorityQueue();
		pq.add(new Edge(s, 0));
		dist[s] = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.remove();
			if (cur.cost > dist[cur.node])
				continue;
			if(node.get(cur.node).villidx==V-1 && dist[cur.node]+pow[node.get(cur.node).powidx].energy<=E) {
//				System.out.println(dist[cur.node]+pow[node.get(cur.node).powidx].energy);
				ans="Yes"; return;
			}
				
			for (Edge next : graph.get(cur.node))
				if (cur.cost + next.cost < dist[next.node])
					pq.add(new Edge(next.node, dist[next.node] = cur.cost + next.cost));
		}
	}
	static class Pair{
		int powidx,villidx;
		public Pair(int v, int p) {
			villidx=v;
			powidx=p;
		}
	}
	static class Power {
		String name;
		int attack, energy;
		public Power(String n, int a, int e) {
			name=n;
			attack=a;
			energy=e;
		}
	}
	static class Villain {
		String name;
		int health; 
		HashSet<String> effect;
		public Villain(String n, int h) {
			name=n;
			health =h;
			effect =new HashSet<String>();
		}
	}
	static class Edge implements Comparable<Edge> {
		int node;
		long cost;

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
