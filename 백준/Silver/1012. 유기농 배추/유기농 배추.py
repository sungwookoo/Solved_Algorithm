import sys
from collections import deque


def bfs(arr, x, y, m, n):
    q = deque()
    q.append([x, y])
    arr[x][y] = 0

    dirX = [-1, 1, 0, 0]
    dirY = [0, 0, -1, 1]

    while q:
        x, y = q.popleft()
        for i in range(4):
            dx = x + dirX[i]
            dy = y + dirY[i]
            if 0 <= dx < m and 0 <= dy < n and arr[dx][dy] == 1:
                q.append([dx, dy])
                arr[dx][dy] = 0


def solve():
    t = int(sys.stdin.readline().rstrip())
    for _ in range(t):
        m, n, k = map(int, sys.stdin.readline().split())
        arr = [[0] * n for _ in range(m)]
        ans = 0

        for _ in range(k):
            x, y = map(int, sys.stdin.readline().split())
            arr[x][y] = 1

        for i in range(m):
            for j in range(n):
                if arr[i][j] == 1:
                    bfs(arr, i, j, m, n)
                    ans += 1
        sys.stdout.write(str(ans) + '\n')


if __name__ == "__main__":
    solve()
