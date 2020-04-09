import java.util.*;
import java.io.*;

public class UVA12160_UnlockTheLock {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
//		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
		Thread.sleep(2000);
		int n=10000;
		ArrayList<Integer>[] graph= new ArrayList[n];
		String s;
		int test=1;
		while(!(s=sc.next()).equals("0")) {
			int from=Integer.parseInt(s);
			int to=sc.nextInt();
			int[] button=new int[sc.nextInt()];
			boolean[] visited= new boolean[n];
			int[] level= new int[n];
			for(int i=0;i<button.length;i++)
				button[i]=sc.nextInt();
			for(int i=0;i<n;i++) {
				graph[i]=new ArrayList<Integer>();
				for(int j=0;j<button.length;j++)
					graph[i].add((i+button[j])%n);
			}
			Queue<Integer> q= new LinkedList<Integer>();
			q.add(from);
			visited[from]=true;
			level[from]=0;
		loop:	while(!q.isEmpty()) {
				int u=q.remove();				
				for(int v: graph[u]) {
					if(!visited[v]) {
						visited[v]=true;
						level[v]=level[u]+1;
						if(v==to)
							break loop;
						q.add(v);
					}
				}
			}
			if(visited[to])
				out.printf("Case %d: %d%n",test++, level[to]);
			else
				out.printf("Case %d: Permanently Locked%n",test++);
		}
		out.close();
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
