import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, K, B;
		
		N = Integer.parseInt(st.nextToken()); // 총 개수 1~100,000
		K = Integer.parseInt(st.nextToken()); // 연속
		B = Integer.parseInt(st.nextToken()); // 고장난 신호등 개수
		
		int[] arr = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			arr[i] = i;
		}
		
		for(int i=1; i<=B; i++) {
			arr[Integer.parseInt(br.readLine())] = -1; 
		}
		
		int min = Integer.MAX_VALUE;
		
		int i = 1;
		
		int count = 0;
		
		while(i <= K) {
			if(arr[i++] == -1) count ++;
		}
		
		if(count == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		while(i <= N) {
			
			if(arr[i-K] == -1) {
				count --;
			}
			
			if(arr[i] == -1) {
				count ++;
			}
			
			min = Math.min(min, count);
			i++;
		}
		
		System.out.println(min);		
	}

}
