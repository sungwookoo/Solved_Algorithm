import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String str;
		char[] arr;
		int sum;
		int ans = 0;
		for (int i = 1; i <= 1000000; i++) {
			if (i > N) {
				ans = 0;
				break;
			}
			str = i + "";
			arr = str.toCharArray();
			sum = i;
			for (char c : arr) {
				sum += c - '0';
			}

			if (sum == N) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}

}
