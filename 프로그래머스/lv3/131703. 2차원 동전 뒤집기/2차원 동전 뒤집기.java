import java.util.*;

class Solution {
    static int[][] originMap;
    static int[][] targetMap;
    static int N, M, answer;

    public int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;

        originMap = beginning;
        targetMap = target;

        // 초기 답을 최대로 설정
        answer = Integer.MAX_VALUE;

        // 각 행에 대해 뒤집을 것인지 여부를 결정
        for (int i = 0; i < (1 << N); i++) {
            // 각 열에 대해 뒤집을 것인지 여부를 결정
            for (int j = 0; j < (1 << M); j++) {
                int cnt = 0;
                // 새로운 상태를 저장할 배열 생성
                int[][] newMap = new int[N][M];

                // 기존 배열 복사
                for (int k = 0; k < N; k++)
                    newMap[k] = Arrays.copyOf(originMap[k], M);

                // 행에 대해 뒤집는 연산 수행
                for (int k = 0; k < N; k++) {
                    if (((1 << k) & i) > 0) {
                        cnt++;
                        flipRow(newMap, k);
                    }
                }

                // 열에 대해 뒤집는 연산 수행
                for (int k = 0; k < M; k++) {
                    if (((1 << k) & j) > 0) {
                        cnt++;
                        flipColumn(newMap, k);
                    }
                }

                // 새로운 상태와 목표 상태가 같다면 답 업데이트
                if (isSame(newMap, targetMap))
                    answer = Math.min(answer, cnt);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    void flipRow(int[][] map, int row) {
        for (int j = 0; j < M; j++)
            map[row][j] = map[row][j] == 0 ? 1 : 0;
    }

    void flipColumn(int[][] map, int column) {
        for (int i = 0; i < N; i++)
            map[i][column] = map[i][column] == 0 ? 1 : 0;
    }

    boolean isSame(int[][] map1, int[][] map2) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map1[i][j] != map2[i][j])
                    return false;

        return true;
    }
}