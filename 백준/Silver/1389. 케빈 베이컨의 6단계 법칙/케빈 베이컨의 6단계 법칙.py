import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())


def bfs(graph, start):
    counts = [0] * (N + 1)
    q = deque()
    visited = [start]
    q.append(start)

    while q:
        cur = q.popleft()
        for i in graph[cur]:
            if i not in visited:
                counts[i] = counts[cur] + 1
                visited.append(i)
                q.append(i)
    return sum(counts)
        

def solve():
    graph = [[] for _ in range(N + 1)]
    for i in range(M):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        graph[b].append(a)

    ans = []
    for i in range(1, N + 1):
        ans.append(bfs(graph, i))

    print(ans.index(min(ans)) + 1)


if __name__ == '__main__':
    solve()
