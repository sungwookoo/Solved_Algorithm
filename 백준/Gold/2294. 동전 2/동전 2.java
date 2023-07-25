import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] dp = new int[K + 1];

        /*
        dp[k] = k원을 만드는데 필요한 최소 동전 개수
        dp[1] = 1원짜리 1개
        dp[2] = 1원짜리 2개
        dp[3] = 1원짜리 3개
        dp[4] = 1원짜리 4개
        dp[5] = 1원짜리 5개 or 5원짜리 1개
        dp[index] = min(dp[index], dp[index-동전금액]+1)
         */

        Arrays.fill(dp, 10000000);
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int index = 1;

        while (index <= N) {
            int coin = arr[index];
            for (int i = coin; i <= K; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin]+1);
            }
//            System.out.println(arr[index]+"원 동전 사용 : ");
//            for(int i=1; i<=K; i++) {
//                System.out.print(i+"원:"+dp[i]+" ");
//            }
//            System.out.println();
            index++;
        }

        System.out.println(dp[K] == 10000000 ? -1 : dp[K]);
    }
}
