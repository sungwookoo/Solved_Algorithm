class Solution {
	static int numberOfArea;
	static int maxSizeOfOneArea;
	static boolean[][] visited;
	static int count = 0;
	static int[] dirX = { -1, 1, 0, 0 };
	static int[] dirY = { 0, 0, -1, 1 };
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
		numberOfArea =0;
	    maxSizeOfOneArea=0;
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] || picture[i][j] == 0)
					continue;
				numberOfArea++;
				dfs(i, j, m, n, picture);
            	maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
			    count = 0;
			}

		}

		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;

		return answer;
    }
    
    public static void dfs(int x, int y, int m, int n, int[][] picture) {
		if (visited[x][y])
			return;

		visited[x][y] = true;
		count++;

		for (int d = 0; d < 4; d++) {
			int dx = x + dirX[d];
			int dy = y + dirY[d];

			if (isOut(m, n, dx, dy))
				continue;

			if(visited[dx][dy] || picture[x][y] != picture[dx][dy]) continue;
				dfs(dx, dy, m, n, picture);
		}

	}

	public static boolean isOut(int m, int n, int dx, int dy) {
		return dx < 0 || dy < 0 || dx > m - 1 || dy > n - 1;
	}
}