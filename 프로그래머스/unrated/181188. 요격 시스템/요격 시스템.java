import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> (o1[1] - o2[1]));
        
        int end = -1;
        for(int[] target : targets) {
            int s = target[0];
            int e = target[1];
            
            if(end <= s) {
                end = e;
                answer++;
            }
        }
                    
        return answer;
    }
}