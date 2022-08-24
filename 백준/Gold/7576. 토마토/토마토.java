import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] arr;
	static int[] dirX = {-1,1,0,0};
	static int[] dirY = {0,0,-1,1};
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<T> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) {
					q.offer(new T(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			T poll = q.poll();
			for(int d=0; d<4; d++) {
				int dx = poll.x + dirX[d];
				int dy = poll.y + dirY[d];
				
				if(dx<0 || dx>=N || dy<0 || dy>=M) {
					continue;
				}
				
				if(arr[dx][dy] != 0) {
					continue;
				}
				
				arr[dx][dy] = arr[poll.x][poll.y] +1;
				q.offer(new T(dx,dy));
			}
		}
		
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) {
					ans = -1;
					return;
				}
				
				max = Math.max(max, arr[i][j]);
			}
		}
		
		ans = max-1;
		
	}
	static class T {
		int x,y;

		public T(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}


