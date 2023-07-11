import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, K;
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0;
		
		int sum = 0;
		
		while(i < K) {
			sum += arr[i++];
		}
		
		int max = sum;
		
		while(i < (N*2)) {
			sum += arr[i%N] - arr[(i-K) % N];
			max = Math.max(max, sum);
			i++;
		}
		
		System.out.println(max);
		
	}

}
