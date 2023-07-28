import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        long[] dists = new long[N];
        long[] prices = new long[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N-1; i++) {
            dists[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        long price = prices[0];
        long sum = prices[0] * dists[0];
        for(int i=1; i<N; i++) {
            price = Math.min(price, prices[i]);
            sum += price * dists[i];
        }
        System.out.println(sum);
    }
}
