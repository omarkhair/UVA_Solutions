import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
public class The_forest_for_the_trees_599 {
	static LinkedList<Integer>[] graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out= new PrintWriter(System.out);
		int t=sc.nextInt();
		while(t-->0) {
			visited= new boolean[26];
			graph=new LinkedList[26];
			for(int i=0; i<26; i++) {
				graph[i]=new LinkedList<Integer>();
				visited[i]=true;
			}
			String line;
			while((line=sc.next()).charAt(0)!='*') {
				int firstchar= line.charAt(1)-'A';
				int secchar= line.charAt(3)-'A';
				graph[firstchar].add(secchar);
				graph[secchar].add(firstchar);
			}
			line=sc.next();
			for(int i=0; i<line.length();i+=2)
				visited[line.charAt(i)-'A']=false;
			int tree=0, acorn=0;
			for(int i=0; i<26; i++) {
				if(!visited[i]) {
					visited[i]=true;
					if(graph[i].size()==0)
						acorn++;
					else {
						tree++;
						dfs(i);
					}
				}
			}
			out.printf("There are %d tree(s) and %d acorn(s).%n",tree,acorn);
		}
		out.close();
	}
	
	public static void dfs(int node) {
		for(int x: graph[node]) {
			if(!visited[x]) {
				visited[x]=true;
				dfs(x);
			}
		}
		
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
