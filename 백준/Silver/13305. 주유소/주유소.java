import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;

        long[] dists = new long[N-1];
        long[] prices = new long[N];

        st = new StringTokenizer(br.readLine());

        long totalDist = 0;
        long maxPrice = Integer.MIN_VALUE;
        long minPrice = Integer.MAX_VALUE;

        for(int i=0; i<N-1; i++) {
            dists[i] = Integer.parseInt(st.nextToken());
            totalDist += dists[i];
        }

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
            maxPrice = Math.max(maxPrice, prices[i]);
            minPrice = Math.min(minPrice, prices[i]);
        }

        if(prices[0] == minPrice) {
            System.out.println(totalDist * minPrice);
            System.exit(0);
        }

        long sum = 0;

        for(int i=0; i<N; i++) {
            if(prices[i] > prices[i+1]) {
                sum += prices[i] * dists[i];
            }
            else {
                int index = i+1;
                while(true) {
                    if(index == N) break;
                    if(prices[index] < prices[i]) {
                        if(index != N-1) {
                            break;
                        }
                    }
                    sum += prices[i] * dists[index-1];
                    index++;
                }
                i = index;
            }
        }

        System.out.println(sum);
        
        



    }
}
