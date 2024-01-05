import java.io.*;
import java.math.BigInteger;

public class Main {

    static int a, b, v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        a = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        
        BigInteger ans = new BigInteger("0");
        for (int i = 0; i < arr.length; i++) {
            ans = ans.add(BigInteger.valueOf(arr[i] - 96).multiply(BigInteger.valueOf(31).pow(i)));
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(ans.remainder(BigInteger.valueOf(1234567891)));

        System.out.println(sb);
    }
}
