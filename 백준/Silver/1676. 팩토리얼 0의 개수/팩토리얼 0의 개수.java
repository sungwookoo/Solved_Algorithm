import java.util.*;
import java.io.*;
import java.math.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= N; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        char[] arr = res.toString().toCharArray();
        int count = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] == '0')
                count++;
            else
                break;
        }

        System.out.println(count);
    }

}
