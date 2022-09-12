import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<input.length; j++) {
                if (input[j] >= 65 && input[j] <= 96) {
                    input[j] = (char) (input[j] + 32);
                }
            }
            boolean flag = true;
            for(int j=0; j<input.length/2; j++) {
                if(input[j] != input[input.length-1-j]) {
                    flag = false;
                }
            }
            sb.append(flag?"Yes\n":"No\n");
        }

        System.out.println(sb);
    }
}
