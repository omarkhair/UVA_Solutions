import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DynamicFrog_11157
{

    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(System.out);
        Scanner sc=new Scanner(System.in);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Thread.sleep(3000);
        int t=sc.nextInt();
        int c=0;
        while(c++<t) {
            int n=sc.nextInt();
            int d=sc.nextInt();
            int max=0;
            ArrayList<Integer> l =new ArrayList<Integer>();
            ArrayList<Boolean> small =new ArrayList<Boolean>();
            l.add(0);	small.add(false);
            for(int i=0; i<n; i++) {
                String s=sc.next();
                int x=Integer.parseInt(s.substring(2));
                if(s.charAt(0)=='B') {
                    small.add(false);
                }
                else
                    small.add(true);
                l.add(x);
            }
            l.add(d);	small.add(false);
            for(int i=0; i<l.size()-1;i++) {

                if(small.get(i+1)) {
                    max=Math.max(max, l.get(i+2)-l.get(i));
                    if(small.get(i)) {
                        l.remove(i);	small.remove(i);
                        i--;
                    }
                    i++;
                    continue;
                }
                max=Math.max(max, l.get(i+1)-l.get(i));
                if(small.get(i)) {
                    l.remove(i);	small.remove(i);
                    i--;
                }
            }
            for(int i=l.size()-1; i>0; i--) {
                max=Math.max(max, l.get(i)-l.get(i-1));
            }
            out.printf("Case %d: %d%n",c,max);
        }
        out.flush();
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
