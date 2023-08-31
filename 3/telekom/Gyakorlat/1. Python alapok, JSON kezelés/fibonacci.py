def fibonacci(n):
    if n <= 0:
        return 0

    before_previous = 0
    previous = 1
    current_iteration = 2

    while current_iteration <= n:
        temp = before_previous
        before_previous = previous
        previous += temp
        current_iteration += 1
    return previous


def fibonacci_recursive(n):
    if n <= 0:
        return 0

    if n == 1:
        return 1

    return fibonacci_recursive(n-2) + fibonacci_recursive(n-1)


for i in range(0, 10):
    print(fibonacci(i))

for i in range(0, 10):
    print(fibonacci_recursive(i))
