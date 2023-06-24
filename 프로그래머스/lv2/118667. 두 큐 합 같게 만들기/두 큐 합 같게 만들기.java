import java.util.*;
class Solution {
    static int N;
    static long sum, sum1;
    static Queue<Integer> q1,q2;
    public int solution(int[] queue1, int[] queue2) {
        sum = 0;
        sum1 = 0;
        q1 = new ArrayDeque();
        q2 = new ArrayDeque();
        N = queue1.length;
        
        for(int i=0; i<N; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum += queue1[i]; 
            sum += queue2[i];
        }
        long half = sum/2;
        int max = N * 3;
        
        while(sum1 != half) {
            
            if(max == 0) {
                return -1;
            }
            
            if(sum1 > half) {
                move2();
            }
            else {
                move1();
            }
            
            max--;
            
        }
        
        return queue1.length * 3 - max;
    }
    
    static void move2() {
        int temp = q1.poll();
        q2.add(temp);
        sum1 -= temp;
    }
    
    static void move1() {
        int temp = q2.poll();
        q1.add(temp);
        sum1 += temp;
    }
}