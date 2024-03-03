import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M, B;
    static int minTime, minHeight, maxHeight;

    static HashMap<Integer, Integer> heightMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        minTime = Integer.MAX_VALUE;
        heightMap = new HashMap<>();
        minHeight = Integer.MAX_VALUE;
        maxHeight = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        for(int target=minHeight; target<maxHeight+1 ; target++) {
            int time = 0;
            int block = B;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    int curHeight = map[i][j];
                    if(curHeight == target)
                        continue;

                    if(curHeight < target) {
                        int diff = target - curHeight;
                        block -= diff;
                        time += diff;
                    } else {
                        int diff = curHeight - target;
                        block += diff;
                        time += diff * 2;
                    }
                }
            }
            if(block < 0) break;
            if(time > minTime) continue;
            minTime = Math.min(minTime, time);
            heightMap.put(minTime, target);
        }

        System.out.println(minTime+" "+heightMap.get(minTime));
    }
}