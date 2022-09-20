import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int stress = 0;
		int done = 0;

		for (int i = 0; i < 24; i++) {
			if (stress + a > m) {
				stress -= c;
				if (stress <= 0)
					stress = 0;
			} else {
				stress += a;
				done += b;
			}
		}
		
		System.out.println(done);
	}
}
