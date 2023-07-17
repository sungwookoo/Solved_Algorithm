import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Ans[] answers = new Ans[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            answers[i] = new Ans(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        // 모든 숫자 중에 질문에 대한 답과 같은 스트라이크, 볼 개수인 경우를 세자.
        for(int i=123; i<=987; i++) { // i 는 정답인지 검사할 숫자
            if(checkDup(i)) continue;

            int count = 0; // 현재 숫자가 스트라이크, 볼 모두 같은 경우의 수


            for(int j=0; j<N; j++) { // 질문한 숫자와 검사할 숫자(i)와 비교
                Ans ans = answers[j];
                int s = 0; // 스트라이크 카운트
                int b = 0; // 볼 카운트

                String ansNum = Integer.toString(ans.num);
                String curNum = Integer.toString(i);

                for(int k=0; k<3; k++) {
                    if(ansNum.charAt(k) == curNum.charAt(k))
                        s++;
                }

                for(int k=0; k<3; k++) {
                    for(int l=0; l<3; l++) {
                        if(k == l) continue;
                        if(curNum.charAt(k) == ansNum.charAt(l))
                            b++;
                    }
                }

                if(ans.s == s && ans.b == b) count ++;

            }

            if(count == N) { // 모든 질문을 정답 숫자에 대조 했을때, 입력한 질문에 대한 답
                result ++;
            }
        }

        System.out.println(result);
    }

    static boolean checkDup(int num) {
        char[] numArr = String.valueOf(num).toCharArray();
        for(int i=0; i<numArr.length;i++) {
            if(numArr[i] == '0') return true;
        }
        if(numArr[0] == numArr[1] || numArr[1] == numArr[2] || numArr[0] == numArr[2]) {
            return true;
        }

        return false;
    }

    static class Ans {
        int num, s, b;

        public Ans(int num, int s, int b) {
            this.num = num;
            this.s = s;
            this.b = b;
        }
    }

}