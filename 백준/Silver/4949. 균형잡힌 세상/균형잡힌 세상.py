import sys

if __name__ == "__main__":
    while True:
        s = input()
        ans = 'yes'
        if s == ".":
            break
        
        stack = []
        
        for i in range(len(s)):
            if s[i] == '(' or s[i] == '[':
                stack.append(s[i])
            elif s[i] == ')':
                if len(stack) == 0 or stack[-1] != '(':
                    ans = 'no'
                    break
                stack.pop()
            elif s[i] == ']':
                if len(stack) == 0 or stack[-1] != '[':
                    ans = 'no'
                    break
                stack.pop()
        if len(stack) != 0:
            ans = 'no'
            
        print(ans)