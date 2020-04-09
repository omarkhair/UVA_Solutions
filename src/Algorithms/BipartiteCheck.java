package Algorithms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BipartiteCheck {
	static ArrayList<Integer>[] graph;
	static int[] color;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt();
		int m=sc.nextInt();
		graph = new ArrayList[n];
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList<Integer>();
		while (m-->0) {
			int from=sc.nextInt()-1;
			int to = sc.nextInt() - 1;
			graph[from].add(to);
			graph[to].add(from);
		}
		boolean possible = true;
		color = new int[n];
		for (int j = 0; j < n; j++) {
			if (color[j] == 0) {
				if (!isBipartite(j)) {
					possible = false;
					break;
				}
			}
		}
		out.println(possible ? "YES" : "NO");
		out.close();

	}

	public static boolean isBipartite(int source) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(source);
		color[source] = 1;
		while (!q.isEmpty()) {
			int u = q.remove();
			for (int v : graph[u]) {
				if (color[v] == 0) {
					color[v] = 3 - color[u];
					q.add(v);
				} else if (color[v] == color[u])
					return false;
			}
		}
		return true;
	}
}
