import java.util.*;
import java.io.*;

public class UVA11080_PlaceTheGuards_Bipartite {
	static LinkedList<Integer>[] graph;
	static int[] color;
	static int n, m, count;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
//			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//			StringTokenizer st= new StringTokenizer(br.readLine());
		Thread.sleep(2000);
		int t = sc.nextInt();
		while (t-- > 0) {
			n = sc.nextInt();
			m = sc.nextInt();
			graph = new LinkedList[n];
			color = new int[n];
			for (int i = 0; i < n; i++)
				graph[i] = new LinkedList<Integer>();
			for (int i = 0; i < m; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				graph[u].add(v);
				graph[v].add(u);
			}
			count = 0;
			for (int i = 0; i < n; i++)
				if (color[i]==0) {
					if(!isBipartite(i))
						break;
				}
			out.println(count);
		}

		out.close();
	}

	public static boolean isBipartite(int source) {
		Queue<Integer> q= new LinkedList<Integer>();
		int count1=0,count2=0;
		q.add(source);
		color[source]=1;
		count1++;
		while(!q.isEmpty()) {
		int u=q.remove();
		for(int v:graph[u]) {
			if(color[v]==0) {
				color[v]=3-color[u];
				if(color[v]==1)count1++; else count2++;
				q.add(v);
			}
			else if(color[v]==color[u]) {
				count=-1;
				return false;
			}
		}
		}
		if(count1==0 || count2==0)
			count+=count1+count2;
		else
			count+=Math.min(count1, count2);
		return true;
	}



	//
//		public static void print(char[][] grid) {
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++)
//					System.out.print(grid[i][j]);
//				System.out.println();
//			}
//			System.out.println();
//		}
//		public static void print(int[][] grid) {
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++)
//					System.out.print(grid[i][j]+" ");
//				System.out.println();
//			}
//			System.out.println();
//		}
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