import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] has = new int[N];
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(hm.get(num) != null) hm.put(num, hm.get(num)+1);
			else hm.put(num, 1);
		}

		int M = Integer.parseInt(br.readLine());
		int[] answers = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(hm.get(num) != null) answers[i] = hm.get(num);
		}

		for(int a : answers) {
			sb.append(a).append(" ");
		}
		
		System.out.println(sb);
	}
}
