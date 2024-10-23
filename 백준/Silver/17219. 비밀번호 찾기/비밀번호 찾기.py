import sys
n, m = map(int, input().split())

address = {}

for _ in range(n):
    site, pw = sys.stdin.readline().split()
    address[site] = pw

for _ in range(m):
    print(address[sys.stdin.readline().rstrip()])
