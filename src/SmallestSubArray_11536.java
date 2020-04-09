import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SmallestSubArray_11536 {


        public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        PrintWriter out= new PrintWriter(System.out);
        int t=sc.nextInt();
        int c=0;
        while(c++<t){
            int n=sc.nextInt();
            int m=sc.nextInt();
            int k=sc.nextInt();
            if(k<4) {
                out.printf("Case %d: %d%n", c, k); continue;
            }
            int[] arr= new int[n];
            for(int i=0;i<n; i++)
                if(i<3)
                    arr[i]=i+1;
                else
                    arr[i]=(arr[i-1]+arr[i-2]+arr[i-3])%m +1;
            //    System.out.println(Arrays.toString(arr));
            HashMap<Integer, Integer> hm= new HashMap<>();
            int i=0; int j=0; boolean first=true;
            int ansStart=0; int ansEnd=k+1; int size=n+1;
            while(j<=n && i<n) {
                if (hm.size() < k) {
                    if (j<n && arr[j] <= k)
                        hm.put(arr[j], hm.getOrDefault(arr[j], 0) + 1);
                    j++;
                } else {
                    //        System.out.println(hm.toString());
                    if (first) {
                        ansStart = i;
                        ansEnd = j;
                        size = j - i ;
                        first = false;
                    }
                    else if (j - i < size) {
                        ansStart = i;
                        ansEnd = j;
                        size = j - i;
                    }
                    if (arr[i] <= k) {
                        if (hm.get(arr[i]) > 1)
                            hm.put(arr[i], hm.get(arr[i]) - 1);
                        else
                            hm.remove(arr[i]);
                    }
                    //      System.out.println(ansStart+" "+ansEnd+" "+size);
                    i++;
                }
            }
            if(first)
                out.printf("Case %d: sequence nai%n",c);
            else
                out.printf("Case %d: %d%n",c,size);

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
