import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int ans = 0;
		
		if(N == 1) {
			System.out.println(1);
			System.exit(0);
		}
		
		char[] seat = str.toCharArray();
		int cnt = 0;
		for(int i=0; i<seat.length; i++) {
			if(seat[i] == 'L' && cnt == 1) {
				cnt = 0;
				continue;
			}
			if(seat[i] == 'L') cnt++;

			ans ++;
		}
		ans ++;
		if(ans > N) ans = N;
		
		System.out.println(ans);
		
		// * * * * * * * * * *
		// S L L L L S S L L
	}
}

// * * 0 * *
// S L L S