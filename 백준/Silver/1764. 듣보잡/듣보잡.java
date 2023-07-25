import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hm = new HashMap<>();

        for(int i=0; i<N+M; i++) {
            String str = br.readLine();
            if(hm.get(str) != null) {
                int temp = hm.get(str);
                hm.put(str,temp+1);
            }
            else hm.put(str,1);
        }

        List<String> list = new ArrayList<>();

        for(String s : hm.keySet()) {
            if(hm.get(s) > 1) list.add(s);
        }

        System.out.println(list.size());
        Collections.sort(list);
        for(String s : list) {
            System.out.println(s);
        }


    }
}
