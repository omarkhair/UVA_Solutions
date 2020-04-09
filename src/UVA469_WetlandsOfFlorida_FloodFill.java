
import java.io.*;
import java.util.*;

public class UVA469_WetlandsOfFlorida_FloodFill {
	static int[][] grid;
	static boolean[][]visited;
	static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // trick to explore an implicit 2D grid
	static int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors
	static int n,m;
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc= new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		Thread.sleep(2000);
		int t=sc.nextInt();
		String s=null;
		int testnum=1;
		while(t-->0) {
		ArrayList<String> l= new ArrayList<String>();
		if(s!=null)
			l.add(s);
		while((s=sc.next()).charAt(0)=='L' || s.charAt(0)=='W') 
			l.add(s);
		n=l.size(); m=l.get(0).length();
		grid= new int[n][m];
		visited= new boolean[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++)
				grid[i][j]=l.get(i).charAt(j)=='W'?1:0;
		}
		if(testnum++>1)
			out.println();
		int i=Integer.parseInt(s)-1;
		int j=sc.nextInt()-1;
		int count=floodfill(i,j);
		out.println(count);
		while(sc.ready()) {
			s=sc.next();
			if(s.charAt(0)=='W' || s.charAt(0)=='L')
				break;
			
			i=Integer.parseInt(s)-1;
			j=sc.nextInt()-1;
			visited=new boolean[n][m];
			count=floodfill(i,j);
			out.println(count);
		}
		}
		out.close();
	}
	
	public static int floodfill(int r, int c) {
		if(r<0 || c<0 || r>=n || c>=m || visited[r][c] || grid[r][c]==0)
			return 0;
		int count=grid[r][c];
		visited[r][c]=true;
		for(int d=0;d<8;d++) {
			count+=floodfill(r+dr[d], c+dc[d]);
		}
//		visited[r][c]=false;
		return count;
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
