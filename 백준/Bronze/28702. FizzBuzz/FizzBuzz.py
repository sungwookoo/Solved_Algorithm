c1 = "FizzBuzz"
c2 = "Fizz"
c3 = "Buzz"


for i in range(3, 0, -1):
    s = input()
    if s not in [c1, c2, c3]:
        n = int(s) + i


if n % 15 == 0:
    print(c1)
elif n % 3 == 0:
    print(c2)
elif n % 5 == 0:
    print(c3)
else:
    print(n)
