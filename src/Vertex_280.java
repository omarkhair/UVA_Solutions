import java.util.*;
import java.io.*;
public class Vertex_280 {
	static int[] parent;
	static boolean[] tried;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		int t=sc.nextInt();
		int test=0;
		while(test++<t) {
			int n=sc.nextInt();
			parent=new int[n+1];
			for(int i=0;i<n; i++)
				parent[sc.nextInt()]=sc.nextInt();
			tried=new boolean[n+1];
			int ans=Integer.MAX_VALUE;
			int max=0;
			for(int i=1;i<=n;i++) {
				if(tried[i])
					continue;
				int count=cycleCount(i);
				if(count>=max) {
					if(count>max)
						ans=i;
					ans=Math.min(ans, i);
					max=count;
				}
			}
			out.printf("Case %d: %d%n",test,ans);
		}
		out.close();
	}
	public static int cycleCount(int start) {
		HashSet<Integer> hs= new HashSet<Integer>();
		tried[start]=true;
		int i=start;
		
		while(!tried[parent[i]]) {		
			hs.add(parent[i]);
			i=parent[i];
			tried[i]=true;
		}
		return hs.size();
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
