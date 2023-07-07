import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[] input;
	static int[] numbers;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		input = new int[N];
		numbers = new int[K];
		ans = 0;
		for(int i=1; i<=N; i++) {
			input[i-1] = i;
		}
		
		comb(0,0);
		
		System.out.println(ans);
	}
	
	static void comb(int start, int count) {
		if(count == K) {
			ans++;
			return;
		}
		
		for(int i=start; i<N; i++) {
			numbers[count] = input[i];
			comb(i+1, count+1);
		}
		
	}

}
