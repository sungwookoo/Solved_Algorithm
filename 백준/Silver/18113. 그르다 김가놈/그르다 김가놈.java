import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 김밥 개수
		int K = Integer.parseInt(st.nextToken()); // 자르는 꼬다리 길이
		int M = Integer.parseInt(st.nextToken()); // 최소 M개 
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			int length = Integer.parseInt(br.readLine()); 
			if(length > (2*K)) list.add(length - (2*K));
			if(2 * K > length && length > K) list.add(length - K);
		}
		
		// Collections.sort(list);
		
		int left = 1;
		int right = 1000000000;
		int ans = -1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			int count = 0;
			for(int n : list) {
				count += (n / mid);
			}
			
			if(count >= M) {
				ans = mid;
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
	
	}

}
