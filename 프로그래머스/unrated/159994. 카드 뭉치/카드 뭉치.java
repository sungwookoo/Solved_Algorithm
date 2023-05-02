class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int index1 = 0;
        int index2 = 0;
        
        for(int i=0; i<goal.length; i++) {
            if(index1 < cards1.length && goal[i].equals(cards1[index1])) {
                index1 ++;
            }
            else if(index2 < cards2.length && goal[i].equals(cards2[index2])) {
                index2 ++;
            }
            else {
                return "No";
            }
        }
        
        
        
        return answer;
    }
}