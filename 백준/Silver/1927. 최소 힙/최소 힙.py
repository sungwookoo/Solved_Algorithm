import sys
import heapq

array = []

def minfind():
    if len(array) == 0:
        return 0
    return heapq.heappop(array)


n = int(sys.stdin.readline())
for i in range(n):
    x = int(sys.stdin.readline())
    if x == 0:
        print(minfind())
    else:
        heapq.heappush(array, x)
