import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int D, W, K; // 두께, 가로크기, 통과기준
	static int map[][]; // 셀
	static int min; // 최소 약품 투입 횟수
	
	// 1. 성능 검사
	// 2. 약품 투입
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			subset(0, 0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	// 부분집합
	static void subset(int index, int ans) {
		if(test()) {
			min = Math.min(min, ans);
			return;
		}
		if(ans > min) return;
		if(index == D) return;
		
		int[] copy = new int[W];
		
		// 주입 없이 다음 행 검사
		for(int i=0; i<W; i++) {
			copy[i] = map[index][i];
		}
		subset(index+1, ans);
		
		// A 주입 후 다음 행 검사
		for(int i=0; i<W; i++) {
			map[index][i] = 0;
		}
		subset(index+1, ans+1);
		
		// B 주입 후 다음 행 검사
		for(int i=0; i<W; i++) {
			map[index][i] = 1;
		}
		subset(index+1, ans+1);
		
		// 원상복구
		for(int i=0; i<W; i++) {
			map[index][i] = copy[i];
		}
	}
	
	
	// 검사
	static boolean test() {
		for(int j=0; j<W; j++) {
			int count  =1;
			boolean isPass = false;
			for(int i=1; i<D; i++) {
				if(map[i][j] == map[i-1][j]) count++;
				else count = 1;
				
				if(count == K) {
					isPass = true;
					break;
				}
			}
			
			if(!isPass) return false;
		}
		return true;
	}


}
