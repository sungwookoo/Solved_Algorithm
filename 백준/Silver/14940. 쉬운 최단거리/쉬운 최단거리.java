import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static int[][] map;
	static int[] dirX = {-1,1,0,0};
	static int[] dirY = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		Queue<Pair> q = new ArrayDeque<>();
		 
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value != 0 ? -1 : -2; 
				if(value == 2) {
					q.add(new Pair(i, j));
					map[i][j] = 0;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			for(int d=0; d<4; d++) {
				int dx = cur.x + dirX[d];
				int dy = cur.y + dirY[d];
				
				if(isOut(dx, dy)) continue;
				if(map[dx][dy] == -2) continue;
				if(map[dx][dy] > -1) continue;
				map[dx][dy] = map[cur.x][cur.y] + 1;
				q.add(new Pair(dx, dy));
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == -2) sb.append(0);
				else sb.append(map[i][j]);
				sb.append(' ');
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		

	}
	
	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static boolean isOut(int dx, int dy) {
		return dx < 0 || dy < 0 || dx > N-1 || dy > M-1;
	}
	
	

}
