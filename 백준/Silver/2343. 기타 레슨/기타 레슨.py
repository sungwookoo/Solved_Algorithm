import sys

if __name__ == "__main__":
    input = sys.stdin.readline
    n, m  = map(int, input().split())
    arr = list(map(int, input().split()))
    
    left = max(arr)
    right = sum(arr)
    ans = -1
    
    while left <= right: 
        mid = (left + right) // 2 
        
        index = 1
        remain = mid
        
        for i in range(n):
            if remain < arr[i]:
                index += 1
                remain = mid
            remain -= arr[i]
        
        if index <= m:
            ans = mid
            right = mid - 1
        else :
            left = mid + 1
            
    print(ans)
