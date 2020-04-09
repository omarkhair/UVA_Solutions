import java.io.*;
import java.util.*;
// can only be solved using BFS Topological sort
public class Beverages_11060 {
	static HashMap<String, Integer> hm;
	static HashMap<Integer, String> hm2;
	static LinkedList<String> ans;	
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[] in;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        PrintWriter out= new PrintWriter(System.out);
        Thread.sleep(3000);
        int testnum=1;
        while(sc.hasNext()) {
        	int n=sc.nextInt();
        	hm= new HashMap<String, Integer>();
        	hm2= new HashMap<Integer, String>();
        	visited=new boolean[n];
        	graph= new ArrayList[n];
        	in =new int[n];
        	for(int i=0;i<n;i++) {
        		String s=sc.next();
        		hm.put(s, i);
        		hm2.put(i, s);
        		graph[i]=new ArrayList<Integer>();
        	}
        	int m=sc.nextInt();
        	for(int i=0;i<m;i++) {
        		int from=hm.get(sc.next());
        		int to=hm.get(sc.next());
        		graph[from].add(to);
        		in[to]++;
        	}
        	ans= new LinkedList<String>();
        	sb= new StringBuilder();
//        	for(int i=0;i<n;i++) 
//        		Collections.sort(graph[i],Collections.reverseOrder());
//        	for(int i=n-1;i>=0;i--) {
//        		if(!visited[i])
//        			dfs(i);
//        	}
        	PriorityQueue<Integer> q= new PriorityQueue<Integer>(); // PriorityQueue is required to sort according to input time
        	for(int i=0;i<n;i++)
        		if(in[i]==0)
        			q.add(i);
        	while(!q.isEmpty()) {
        		int u=q.remove();
        		ans.add(" "+hm2.get(u));
        		for(int v: graph[u])
        			if(--in[v]==0)
        				q.add(v);
        	}
        	for(String s:ans)
        		sb.append(s);
        	System.out.printf("Case #%d: Dilbert should drink beverages in this order:%s.%n%n", testnum++,sb.toString());
         }
        out.close();
	        
	 }
	public static void dfs(int node) {
		visited[node]=true;
		for(int x: graph[node]) 
			if(!visited[x]) {
				dfs(x);
			}
		ans.addFirst(" "+hm2.get(node));
	}
	
	
//    static class Scanner {
//        StringTokenizer st;
//        BufferedReader br;
//
//        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
//
//        public String next() throws IOException
//        {
//            while (st == null || !st.hasMoreTokens())
//                st = new StringTokenizer(br.readLine());
//            return st.nextToken();
//        }
//
//        public int nextInt() throws IOException {return Integer.parseInt(next());}
//
//        public long nextLong() throws IOException {return Long.parseLong(next());}
//
//        public String nextLine() throws IOException {return br.readLine();}
//
//        public boolean ready() throws IOException {return br.ready();}
//
//
//    }

}
