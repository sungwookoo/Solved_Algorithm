if __name__ == "__main__":
    # n, m = map(int, input().split())
    target = [1, 1, 2, 2, 2, 8]
    arr = list(map(int, input().split()))
    for i in range(6):
        print(target[i] - arr[i], end=" ")
