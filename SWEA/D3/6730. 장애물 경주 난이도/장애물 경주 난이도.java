import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, up, down;
	static int[] blocks;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			up = 0;
			down = 0;
			blocks = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				blocks[i] = Integer.parseInt(st.nextToken());
			}
			
			solution();
			
			sb.append("#").append(tc).append(" ").append(up).append(" ").append(down).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	static void solution() {
		for(int i=1; i<N; i++) {
			if(blocks[i] > blocks[i-1]) {
				// 올라가는
				up = Math.max(up, Math.abs(blocks[i] - blocks[i-1]));
			}
			else if(blocks[i] < blocks[i-1]) {
				// 내려가는
				down = Math.max(down, Math.abs(blocks[i] - blocks[i-1]));
			}
		}
	}
}
