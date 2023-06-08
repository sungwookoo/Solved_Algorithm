class Solution {
    static int maxIncome;
    static int maxUser;
    static int[] input = {10,20,30,40};
    static int[] numbers;
    static int N, U;
    static int[][] user;
    static int[] emoticon;
    static int[] answer;
        
    public int[] solution(int[][] users, int[] emoticons) {
        user = users;
        emoticon = emoticons;
        answer = new int[2];
        maxIncome = 0;
        maxUser = 0;
        N = emoticons.length;
        U = users.length;
        numbers = new int[N];
        /*
         users[0][0] = 40
         users[0][1] = 10000
         40% 이상 할인하면 모두 구매
         10000원이 넘으면 이모티콘 플러스 구매
         
         할인율 : 10/20/30/40%
         
        */ 
        
        comb(0);
        
        answer[0] = maxUser;
        answer[1] = maxIncome;
        
        return answer;
    }
    
    // 할인율 정하기
    static void comb(int index) {
        if(index == N) {
            // numbers에 할인율 저장
            int[] ans = new int[2];
            ans = calculate();
            
            if(ans[0] == maxUser) {
                maxIncome = Math.max(ans[1], maxIncome);
            }
            else if(ans[0] > maxUser) {
                maxUser = ans[0];
                maxIncome = ans[1];
            }
            
            // 출력
            // for(int a:numbers) {
            //     System.out.print(a+" ");
            // }
            // System.out.println();
            
            return;
        }
        
        for(int i=0; i<4; i++) {
            numbers[index] = input[i];
            comb(index+1);
        }
        
    }
    
    // 계산하기
    static int[] calculate() {
        int[] ans = new int[2];
        
        int userCount = 0;
        int income = 0;
        for(int i=0; i<U; i++) {
            int temp = 0;
            for(int j=0; j<N; j++) {

                // 해당 이모티콘의 할인율이 user의 비율 이상이라면
                if(numbers[j] >= user[i][0]) {
                    // 구매하고 수입 증가
                    temp += emoticon[j] - (emoticon[j] / 100 * numbers[j]);        
                }
                
                // 구매금액이, 예산을 초과하면
                if(temp >= user[i][1]) {
                    // 플러스 가입
                    temp = 0;
                    userCount ++;
                    break;
                }
                
            }
            
            income += temp;
            
        }
        
        ans[0] = userCount;
        ans[1] = income;
        return ans;   
    }
    
    
}