import java.util.*;

import java.io.*;

public class Main {
	static LinkedList<Integer>[] graph;
	static boolean[] visited;
	static int n,m;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
//		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
		Thread.sleep(2000);
		int t=sc.nextInt();
		while(t-->0) {
			n=sc.nextInt();
			m=sc.nextInt();
			graph= new LinkedList[n];
			visited=new boolean[n];
			for(int i=0;i<n;i++) 
				graph[i]=new LinkedList<Integer>();
			for(int i=0;i<m;i++) {
				int u=sc.nextInt();
				int v=sc.nextInt();
				graph[u].add(v);
				graph[v].add(u);
			}
			int count=0;
			for(int i=0;i<n;i++)
				if(!visited[i]) {
					if(color(i))
						count++;
					else {
						count=-1;
						break;
					}
				}
			out.println(count);
		}
		
		out.close();
	}
	public static boolean color(int source) {
		visited[source]=true;
		for(int v:graph[source]) {
			if(visited[v])
				return false;
			visited[v]=true;
		}
		return true;
	}
	
//
//	public static void print(char[][] grid) {
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++)
//				System.out.print(grid[i][j]);
//			System.out.println();
//		}
//		System.out.println();
//	}
//	public static void print(int[][] grid) {
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++)
//				System.out.print(grid[i][j]+" ");
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	 static class Scanner {
	        StringTokenizer st;
	        BufferedReader br;

	        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

	        public String next() throws IOException
	        {
	            while (st == null || !st.hasMoreTokens())
	                st = new StringTokenizer(br.readLine());
	            return st.nextToken();
	        }

	        public int nextInt() throws IOException {return Integer.parseInt(next());}

	        public long nextLong() throws IOException {return Long.parseLong(next());}

	        public String nextLine() throws IOException {return br.readLine();}

	        public boolean ready() throws IOException {return br.ready();}


	    }
}