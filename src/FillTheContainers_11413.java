import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FillTheContainers_11413 {
	static int[] arr;
	static 		int n, m;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		Thread.sleep(2000);
		while(sc.ready()) {
			n=sc.nextInt();
			m=sc.nextInt();
			arr= new int[n];
			int lo=0; int hi=0;
			for (int i=0; i<n; i++) {
				int x=sc.nextInt();
				arr[i]=x;
				lo=Math.max(lo, x);
				hi+=x;
			}
			int ans=-1;
			while(lo<=hi) {
				int mid=(lo+hi)/2;
				if(isValid(mid)) {
					ans=mid;
					hi=mid-1;
				}
				else
					lo=mid+1;
			}
			out.println(ans);
		}
		out.flush();
	}
	
	public static boolean isValid(int x) {
		int count=1;
		int cur=x;
		for(int i=0; i<n; i++) {
			if(cur-arr[i]<0) {
				count++;
				cur=x;
				i--;
			}
			else
				cur-=arr[i];
		}
		return count<=m;
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
