import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] belt = new int[N];

		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}

		// K 가지 다른 초밥을 먹은 경우 (쿠폰에 적혀있지 않은) 최대

		// 초밥의 가짓수 d (2<=d<=3000)
		int[] kinds = new int[d + 1];

		int i = 0;

		int max = 0;

		// 처음 위치에서 K개 검사

		int count = 0;

		int coupon = 0;


		while (i < k) {

			if (belt[i] == c) {
				coupon++;
			}

			kinds[belt[i]]++;
			if (kinds[belt[i]] == 1) {
				count++;
			}

			i++;
		}
		
		if(coupon == 0) max = count + 1;
		else max = count;

		while (i < N * 2) {

			boolean bonus = false;

			if (belt[(i - k) % N] == c) {
				coupon--;
			}

			if (belt[i%N] == c) {
				coupon++;
			}

			kinds[belt[i%N]]++;
			if (kinds[belt[i%N]] == 1) {
				count++;
			}

			kinds[belt[(i - k)%N]]--;
			if (kinds[belt[(i - k)%N]] == 0) {
				count--;
			}

			i++;
			
			if (coupon == 0) {
				bonus = true;
			}
			
			if (bonus) {
				max = Math.max(max, count + 1);
			}

			else {
				max = Math.max(max, count);
			}
		}

		System.out.println(max);

	}
}
