	import java.io.*;
	import java.util.*;
	import javafx.util.Pair;
	public class CF1296E1_StringColoring_Bipaitite {
		static LinkedList<Integer>[] graph;
		static boolean[] visited;
		static int[] ans;
	
		public static void main (String[] args) throws Exception {
			Scanner sc =new Scanner(System.in);
			PrintWriter out =new PrintWriter(System.out);	
	//		Thread.sleep(2000);
			int n=sc.nextInt();
			char[] characters= sc.next().toCharArray();
			Pair<Character,Integer>[] arr= new Pair[n];
			graph= new LinkedList[n];
			ans= new int[n];
			visited=new boolean[n];
			for(int i=0;i<n;i++) {
				graph[i]=new LinkedList<Integer>();
				arr[i]=new Pair<Character, Integer>(characters[i],i);
			}
			for(int i=0;i<n-1;i++) 
				for (int j=0;j<n-1-i;j++) 
					if(arr[j+1].getKey()<arr[j].getKey()) {
						graph[arr[j].getValue()].add(arr[j+1].getValue());
						graph[arr[j+1].getValue()].add(arr[j].getValue());
						Pair temp=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=temp;
					}
	//		System.out.println(Arrays.deepToString(graph));
			boolean possible=true;
			for(int i=0;i<n;i++) {
				if(!visited[i]) {
					if(!isBipartite(i))
						possible =false;
				}
			}
			if(possible) {
				out.println("YES");
				for(int x:ans)
					out.print(x);
				}
			else
				out.println("NO");
			out.close();
		}
		
		
		public static boolean isBipartite(int source) {
			Queue<Integer> q= new LinkedList<Integer>();
			ans[source]=0;
			q.add(source);
			visited[source]=true;
			while(!q.isEmpty()) {
				int node=q.remove();
				for(int x:graph[node]) {
					if(!visited[x]) {
						visited[x]=true;
						q.add(x);
						ans[x]=1-ans[node];
					}
					if(ans[x]==ans[node])
						return false;		
				}
			}
			return true;
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
