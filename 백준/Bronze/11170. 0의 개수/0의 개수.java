import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int N, M;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			int count = 0; 
			for(int i=N; i<=M; i++) {
				char[] arr = Integer.toString(i).toCharArray();
				
				for(char c : arr) {
					if(c == '0') count++;
				}
			}
			
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

}
