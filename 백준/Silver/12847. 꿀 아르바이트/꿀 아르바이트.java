import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		Long arr[] = new Long[N+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		long sum = 0;
		int i=1;
		
		while(i<=M) {
			sum += arr[i++];
		}
		
		long max = sum;
		
		while(i<=N) {
			sum += arr[i] - arr[i-M];
			i++;
			max = Long.max(max, sum);
		}
		System.out.println(max);
	}

}
