import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int tc=1; tc<=T; tc++) {
            List<String> sounds = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                sounds.add(st.nextToken());
            }

            List<String> list = new ArrayList<>();

            while(true) {
                String data = br.readLine();
                if(data.equals("what does the fox say?")) {
                    break;
                }

                st = new StringTokenizer(data);
                st.nextToken();
                st.nextToken();
                String sound = st.nextToken();

                list.add(sound);
            }

            StringBuilder sb = new StringBuilder();

            for(String s : sounds) {
                if(!list.contains(s)) sb.append(s).append(" ");
            }

            System.out.println(sb);
        }


    }

}