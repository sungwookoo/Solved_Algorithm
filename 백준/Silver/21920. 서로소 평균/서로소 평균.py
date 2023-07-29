import math

N = int(input())
lists = list(map(int, input().split(" ")))
X = int(input())
dp = [0]*1000001

sum = 0
count = 0
for d in lists:
    if dp[d]==0:
        if math.gcd(d, X)==1:
        # 서로소인 경우
            dp[d] = 1
            sum+=d
            count+=1
        else:
            dp[d] = 2

    elif dp[d]==1:
        sum+=d
        count+=1

print(sum/count)