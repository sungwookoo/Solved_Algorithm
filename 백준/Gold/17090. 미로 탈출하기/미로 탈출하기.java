import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static char[][] map;
	static int[][] dp;
	static boolean[][] visited;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		
		dp = new int[N][M];
		map = new char[N][M];
		

		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
 			for(int j=0; j<M; j++) {
 				map[i][j] = input[j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(dfs(i,j)) ans++;
			}
		}

		sb.append(ans);
		System.out.println(sb);
	}
	
	static boolean dfs(int x, int y) {
		
		if(x<0 || y<0 || x>=N || y>=M) {
			return true;
		}
		
		if(map[x][y] == 'T') return true;
		if(map[x][y] == 'F') return false;
		
		if(visited[x][y]) {
			return false;
		}
		
		visited[x][y] = true;
		
		char dir = map[x][y];
		boolean flag = false;
		
		switch (dir) {
		case 'U':
			flag = dfs(x-1,y);	
			break;
		case 'R':
			flag = dfs(x,y+1);
			break;
		case 'D':
			flag = dfs(x+1,y);
			break;
		case 'L':
			flag = dfs(x,y-1);
			break;

		default:
			break;
		}
		
		map[x][y] = flag? 'T' : 'F';
		return flag;
		
		
	}
}
