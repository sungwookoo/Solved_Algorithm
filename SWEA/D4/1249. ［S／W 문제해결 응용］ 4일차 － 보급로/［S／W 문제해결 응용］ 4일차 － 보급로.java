import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	// SWEA 1249 보급로
	
	static class Pair implements Comparable<Pair> {
		int x,y,t;

		public Pair(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
		@Override
		public int compareTo(Pair o) {
			return this.t - o.t;
		}	
	}
	
	static int ans;
	static int[] dirX = {-1,0,1,0}; // 상우하좌
	static int[] dirY = {0,1,0,-1};
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			ans = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j=0; j<N; j++) {
					map[i][j] = input[j] - '0';
				}
			}
			
			bfs();
			
			sb.append("#"+tc +" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.offer(new Pair(0, 0, 0));
		
		while(!q.isEmpty()) {
			Pair poll = q.poll();
			
			if(poll.x == N-1 && poll.y == N-1) {
				ans = poll.t;
				return;
			}
			
			for(int d=0; d<4; d++) {
				int dx = poll.x + dirX[d];
				int dy = poll.y + dirY[d];
				
				if(isOut(dx, dy)) continue;
				if(visited[dx][dy]) continue;
				
				visited[dx][dy] = true;
				q.offer(new Pair(dx, dy, poll.t + map[dx][dy]));
			}
		}
		
		
	}
	
	static boolean isOut(int dx, int dy) {
		return dx<0 || dx>=N || dy<0 || dy>=N;
	}
	
}