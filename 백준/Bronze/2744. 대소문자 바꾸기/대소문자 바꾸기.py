if __name__ == "__main__":
    s = input()
    ans = ''
    for c in s:
        ans += c.lower() if c.isupper() else c.upper()
    print(ans)