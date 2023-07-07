import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// int ans = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		int[] input = new int[N+1];
		for(int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 1;
		int sum = 0;
		
		while(i<=K) sum += input[i++];
        int ans = sum;
		while(i<=N) {
			sum += input[i]-input[i-K];
			if(ans < sum) ans = sum;
			i++;
		}
		
		System.out.println(ans);
	}
}
