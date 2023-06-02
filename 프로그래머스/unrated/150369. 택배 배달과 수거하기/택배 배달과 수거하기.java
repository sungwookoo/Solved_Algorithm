import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        /*
        cap = 4
        1 2 3 4 5
        1 0 3 1 2
        0 3 0 4 0
        
        1 4 
        1 3
        
        */
        long answer = 0;
        int d = 0;
        int p = 0;
        
        deliveries = reverseArray(deliveries);
        pickups = reverseArray(pickups);
        
        for(int i=0; i<n; i++) {
            d += deliveries[i];
            p += pickups[i];
            
            while(d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                answer += (n-i) * 2;
            }
            
        }
        
        return answer;
    }
    
    static int[] reverseArray(int[] array) {
        for(int i=0; i < (array.length/2); i++) {
            int temp = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = temp;
        }
        
        return array;
    }
}