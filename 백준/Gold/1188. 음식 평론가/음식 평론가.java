import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N % M == 0) {
            System.out.println(0);
            System.exit(0);
        }

        System.out.println(M - gcd(N, M));
    }

    static int gcd(int a, int b) {
        return a%b == 0 ? b : gcd(b, a%b);
    }
}
