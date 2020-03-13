import java.util.*;
import java.io.*;
public class Vertex_280 {
	static LinkedList<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		int n;
		while((n=sc.nextInt())!=0) {
			graph=new LinkedList[n];
//			Arrays.fill(graph, new LinkedList<Integer>());
			for(int i=0;i<n;i++)
				graph[i]=new LinkedList<Integer>();
			int from;
			while((from=sc.nextInt())!=0) {
				int to;
				while((to=sc.nextInt())!=0)
					graph[from-1].add(to-1);
			}
			int queries= sc.nextInt();
			while(queries-->0) {
				int source=sc.nextInt();
				visited= new boolean[n];
				dfs(source-1);
				int count=0;
				StringBuilder sb= new StringBuilder();
				for(int i=0; i<n; i++) 
					if(!visited[i]) {
						count++;
						sb.append(" "+(i+1));
					}
				out.println(count+sb.toString());
			}
		}
		out.close();
	}
	public static void dfs(int node) {
		for(int x: graph[node]) 
			if(!visited[x]) {
				visited[x]=true;
				dfs(x);
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
