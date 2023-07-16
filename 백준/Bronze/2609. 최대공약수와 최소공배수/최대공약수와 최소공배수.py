from sys import stdin

n, m = map(int, stdin.readline().split())


def gcd(a, b):
    if a % b == 0:
        return b
    return gcd(b, a % b)


def lcm(a, b):
    return (int)(a * b / gcd(a, b))


print(gcd(n, m))
print(lcm(n, m))
