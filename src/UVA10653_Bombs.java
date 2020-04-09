import java.util.*;
import java.io.*;

public class UVA10653_Bombs {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
//			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//			StringTokenizer st= new StringTokenizer(br.readLine());
//		Thread.sleep(2000);
		int[] dr= {0,1,0,-1}; 
		int[] dc= {1,0,-1,0};
		int n,m;
		while((n=sc.nextInt())!=0) {
			m=sc.nextInt();
			int[][] time= new int[n][m];
			boolean[][] visited= new boolean[n][m];
			int rows=sc.nextInt();
			while(rows-->0) {
				int i=sc.nextInt();
				int cols=sc.nextInt();
				while(cols-->0)
					visited[i][sc.nextInt()]=true;			
			}
			Queue<Pair> q= new LinkedList<Pair>();
			Pair start=new Pair(sc.nextInt(), sc.nextInt());
			Pair end= new Pair(sc.nextInt(), sc.nextInt());
			q.add(start);
			visited[start.r][start.c]=true;
	loop:		while(!q.isEmpty()) {
				Pair p=q.remove();
				for(int d=0;d<4;d++) {
					int r=p.r+dr[d];
					int c=p.c+dc[d];
					if(r>=0 && c>=0 && r<n && c<m && !visited[r][c]) {
						visited[r][c]=true;
						time[r][c]=time[p.r][p.c]+1;
						q.add(new Pair(r,c));
						if(r==end.r && c==end.c)
							break loop;
					}
				}
			}
			out.println(time[end.r][end.c]);
		}
		out.close();
	}
	static class Pair{
		int r,c;
		public Pair(int row, int col) {
			r=row;
			c=col;
		}
	}
	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}
}
