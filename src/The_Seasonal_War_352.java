import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class The_Seasonal_War_352 {
	static int n;
	static int[][] grid;
	public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in);
        PrintWriter out= new PrintWriter(System.out);
        int testnum=1;
        Thread.sleep(3000);
        while(sc.ready()) {
        	n=sc.nextInt();
        	grid= new int[n][n];
        	for(int i=0; i<n; i++) {
        		String s= sc.next();
        		for(int j=0; j<n; j++)
        			grid[i][j]=s.charAt(j)-'0';
        	}
        	int count=0;
        	for (int i = 0; i < n; i++) 
				for (int j = 0; j < n; j++) 
					if(grid[i][j]==1) {
						count++;
						floodfill(i,j);
					}
        	out.printf("Image number %d contains %d war eagles.%n", testnum++, count);
         }
        out.close();
	        
	 }
	
	public static void floodfill(int x, int y) {
		int dx[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
		int dy[] = {0,1,1, 1, 0,-1,-1,-1};
		if(x<0||y<0||x>=n||y>=n)
			return;
		if(grid[x][y]!=1)
			return;
		grid[x][y]=-1;
		for (int d = 0; d < 8; d++)
			floodfill(x+dx[d],y+dy[d]);
		
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
