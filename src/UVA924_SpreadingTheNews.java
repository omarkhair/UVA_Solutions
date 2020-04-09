import java.util.*;
import java.io.*;

public class UVA924_SpreadingTheNews {
	static LinkedList<Integer>[] graph;
	static int n;

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
//			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//			StringTokenizer st= new StringTokenizer(br.readLine());
		Thread.sleep(2000);
			n = sc.nextInt();
			graph = new LinkedList[n];
			for (int i = 0; i < n; i++) {
				graph[i]=new LinkedList<Integer>();
				int count=sc.nextInt();
				while(count-->0)
					graph[i].add(sc.nextInt());
			}
			int t=sc.nextInt();
			while(t-->0) {
				int source=sc.nextInt();
				int[] day= new int[n];
				int[] peopleKnew= new int[n];
				boolean[] visited = new boolean[n];
				Queue<Integer> q= new LinkedList<Integer>();
				q.add(source);
				visited[source]=true;
				day[source]=1;
				while(!q.isEmpty()) {
					int u=q.remove();
					for(int v: graph[u]) {
						if(!visited[v]) {
							peopleKnew[day[u]]++;
							visited[v]=true;
							q.add(v);
							day[v]=day[u]+1;
						}
					}
				}
				int boomSize=0;
				int boomDay=0;
				for(int i=0;i<n;i++)
					if(peopleKnew[i]>boomSize) {
						boomSize=peopleKnew[i];
						boomDay=i;
					}
				out.println(boomSize+( boomSize==0?"":" "+boomDay));
			}
		out.close();
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
