import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T, H, W, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		int floor = 0, number = 0;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if (N % H == 0) {
				floor = H;
				number = H==1?N:N/H;
			} else {
				floor = N%H;
				number = H==1?N:N/H+1;
			}

			sb.append(floor).append(number < 10 ? "0" + number : number).append("\n");
		}

		System.out.println(sb);
	}

}
