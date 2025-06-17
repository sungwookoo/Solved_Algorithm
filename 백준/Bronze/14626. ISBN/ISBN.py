import sys


def solve():
    input_str = sys.stdin.readline().rstrip()
    sum_without_star = 0
    star_position = -1

    for i in range(12):
        if input_str[i] == "*":
            star_position = i
            continue
        if i % 2 == 0:
            sum_without_star += int(input_str[i]) * 1
        else:
            sum_without_star += int(input_str[i]) * 3

    for i in range(10):
        temp_sum = sum_without_star
        if star_position % 2 == 0:
            temp_sum += i * 1
        else:
            temp_sum += i * 3

        if (10 - (temp_sum % 10)) % 10 == int(input_str[-1]):
            answer = i
            break

    print(answer)


if __name__ == "__main__":
    solve()
