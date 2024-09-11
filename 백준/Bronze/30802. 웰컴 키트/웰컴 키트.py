import sys

if __name__ == "__main__":
    n = int(input())
    data = list(map(int, sys.stdin.readline().split()))
    t, p = map(int, sys.stdin.readline().split())
    a = 0
    b = n // p
    c = n % p
    for i in data:
        if i % t == 0:
            a += i // t
        else:
            a += (i // t) + 1

    print(str(a) + "\n" + str(b) + " " + str(c))
