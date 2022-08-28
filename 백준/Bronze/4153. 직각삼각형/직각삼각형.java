import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] input = new int[3];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<3; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input);
			if(input[0] == 0 && input[1] == 0 && input[2] == 0) break;
			
			if(input[0]*input[0] + input[1]*input[1] == input[2]*input[2]) sb.append("right\n");
			else sb.append("wrong\n");
		}
		
		System.out.println(sb);
		
	}
}
