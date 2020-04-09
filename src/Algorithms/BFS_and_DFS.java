package Algorithms;
import java.io.*;
import java.util.*;

public class BFS_and_DFS {
	static LinkedList<Integer>[] graph;
	static boolean[] visited;
	static int[] level;
	public static void main (String[] args) throws Exception {
		Scanner sc =new Scanner(System.in);
		PrintWriter out =new PrintWriter(System.out);	
//		Thread.sleep(2000);
		int n=sc.nextInt();
		graph=new LinkedList[n];
		visited= new boolean[n];
		level=new int[n];
		boolean[] hasManager= new boolean[n];
		for(int i=0; i<graph.length;i++)
			graph[i]=new LinkedList<Integer>();
		for(int i=0; i<n;i++) {
			int j=sc.nextInt()-1;
			if(j>=0) {
				hasManager[i]=true;
				graph[i].add(j);
				graph[j].add(i);
			}
		}
		int count=0;
		for(int i=0;i<n;i++)
			if(!hasManager[i])
				bfs(i);
		for(int i=0;i<n;i++)
			if(level[i]>count)
				count=level[i];
		
		out.println(count);
		out.close();
	}
	 
	public static void dfs(int node) {
		for(int x: graph[node]) {
			if(!visited[x]) {
				visited[x]=true;
				dfs(x);
			}
		}	
	}
	public static void bfs(int node) {
		LinkedList<Integer> l= new LinkedList<Integer>();
		l.add(node);
		visited[node]=true;
		level[node]=1;
		while(!l.isEmpty()) {
			int cur=l.remove();
			for(int x:graph[cur])
				if(!visited[x]) {
					visited[x]=true;
					level[x]=level[cur]+1;
					l.add(x);
				}
		}
	}
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
