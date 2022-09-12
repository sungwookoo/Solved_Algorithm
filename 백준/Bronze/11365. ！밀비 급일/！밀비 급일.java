import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        StringTokenizer st;
//        st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
        while(true) {
            StringBuilder sb = new StringBuilder();
            String str = br.readLine();
            if(str.equals("END")) break;
            char[] input = str.toCharArray();
            for(int i=input.length-1; i>=0; i--) {
                sb.append(input[i]);
            }
            System.out.println(sb);
        }

    }
}
