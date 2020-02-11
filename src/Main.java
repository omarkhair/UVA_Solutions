import java.io.*;
import java.util.*;

public class Main {
	static int k ,n;
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        PrintWriter out= new PrintWriter(System.out);
        n=sc.nextInt(); 
        k=sc.nextInt();
        int lo=1; int hi=n;
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
        System.out.println(ans);
       out.flush();
    }

    public static boolean isValid(int v) {
    	int lines=v;
    	
    	for(int i=1;; i++) {
    		int add=v/(int)Math.pow(k, i);
    		if(lines>=n)
    			return true;
    		if(add>0)
    			lines+=add;
    		else
    			break;
    	}
    	return lines>=n;
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
