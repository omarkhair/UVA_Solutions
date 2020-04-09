
import java.util.*;

import javafx.util.Pair;

import java.io.*;

public class BFS_PriorityQ_implicitGraph {
	static int[] dx = { 0, 1, 0, -1 }; // up, right, down, left
	static int[] dy = { 1, 0, -1, 0 };
	static int n, m;
	static int[][] minturn, d;
	static boolean[][] path;
	static char[][] grid;

	public static void main(String[] args) throws IOException {
//			Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new char[n][m];
		minturn = new int[n][m];
		d = new int[n][m];
		int xStart = -1, yStart = -1;
		int xEnd = -1, yEnd = -1;
		for (int i = 0; i < n; i++)
			grid[i] = br.readLine().toCharArray();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 'S') {
					xStart = i;
					yStart = j;
				} else if (grid[i][j] == 'T') {
					xEnd = i;
					yEnd = j;
				}
				minturn[i][j] = 9;
				d[i][j] = -1;
			}
		String ans = "NO";
		if (bfs(xStart, yStart, xEnd, yEnd))
			ans = "YES";
		System.out.println(ans);
		out.close();
	}

	public static boolean bfs(int xs, int ys, int xe, int ye) {
		PriorityQueue<Cell> q = new PriorityQueue<Cell>();
//			new Cell(xs,ys,-1,0);
		for (int i = 0; i < 4; i++) {
			int nx = xs + dx[i];
			int ny = ys + dy[i];
			Cell c = new Cell(nx, ny, i % 2, 0);
			if (c.isValid()) {
				q.add(c);
				if (nx == xe && ny == ye)
					return true;
			}
		}
		while (!q.isEmpty()) {
			Cell c = q.remove();
//				System.out.println(c.x+" "+c.y+" "+c.direction+" "+c.turn);
			for (int i = 0; i < 4; i++) {
				int nx = c.x + dx[i];
				int ny = c.y + dy[i];
				int turns = c.turn;
				if (c.direction != i % 2)
					turns++;
				Cell nc = new Cell(nx, ny, i % 2, turns);
				if (nc.isValid()) {
					q.add(nc);
					if (nx == xe && ny == ye)
						return true;
				}
			}
		}
		return false;
	}

	static class Cell implements Comparable<Cell> {
		int x;
		int y;
		int direction;
		int turn;

		public Cell(int x, int y, int d, int t) {
			this.x = x;
			this.y = y;
			direction = d;
			turn = t;
		}

		public boolean isValid() {
			if (x >= n || y >= m || x < 0 || y < 0)
				return false;
			if (grid[x][y] == '*' || turn > 2 || turn > minturn[x][y])
				return false;
			if (turn == minturn[x][y] && direction == d[x][y])
				return false;
			minturn[x][y] = turn;
			d[x][y] = direction;
			return true;
		}

		@Override
		public int compareTo(Cell o) {
			return turn - o.turn;
		}
	}

	public static boolean move(char[][] grid, int x, int y, int turns, int direction) {
		if (x >= n || y >= m || x < 0 || y < 0)
			return false;
		if (grid[x][y] == '*' || (grid[x][y] == '$' && turns > minturn[x][y]) || turns > 2)
			return false;
		if (turns == minturn[x][y] && direction % 2 == d[x][y] % 2)
			return false;
		if (grid[x][y] == 'T')
			return true;
		boolean ans = false;
		if (minturn[x][y] == -1 || turns < minturn[x][y]) {
			minturn[x][y] = turns;
			d[x][y] = direction;
		}
		char origin = grid[x][y];
		grid[x][y] = '$';
//			print(grid);
//			print(turn);
		for (int i = 0; i < 4; i++) {
			int change = 0;
			if (direction >= 0 && direction % 2 != i % 2)
				change = 1;
			ans = ans || move(grid, x + dx[i], y + dy[i], turns + change, i);
		}
//			grid[x][y]=origin;
		return ans;
	}

	public static void print(char[][] grid) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(grid[i][j]);
			System.out.println();
		}
		System.out.println();
	}

	public static void print(int[][] grid) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
}
