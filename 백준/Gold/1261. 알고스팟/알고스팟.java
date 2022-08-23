import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 백준 1261 : 알고스팟
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	// 우하좌상
	static int[] dirX = { 0, 1, 0, -1 };
	static int[] dirY = { 1, 0, -1, 0 };
	static int ans;

	static class Pair implements Comparable<Pair> {
		int x,y,count;

		public Pair(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public int compareTo(Pair o) {

			return count - o.count;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1][M+1];
		arr = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = input[j-1]-'0';
			}
		}

		bfs();

		System.out.println(ans);
	}
	
	static void bfs() {
//		Queue<Pair> q = new LinkedList<Pair>();
		PriorityQueue<Pair> q = new PriorityQueue<>();
		q.offer(new Pair(1, 1, 0));
		visited[1][1] = true;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			if(cur.x==N && cur.y == M) { 
				ans = cur.count;
				return;
			}
			
			
			for(int d=0; d<4; d++) {
				int dx = cur.x+dirX[d];
				int dy = cur.y+dirY[d];
				if(!check(dx,dy)) continue;
				
				if(arr[dx][dy] == 0 && !visited[dx][dy]) {
					visited[dx][dy] = true;
					q.offer(new Pair(dx, dy, cur.count));
				}
				
				if(arr[dx][dy] == 1 && !visited[dx][dy]) {
					visited[dx][dy] = true;
					q.offer(new Pair(dx, dy, cur.count+1));
				}
			}
			
		}
		
	}
	
	static boolean check(int x, int y) {
		return x>0 && x<=N & y>0 && y<=M;
	}
}
