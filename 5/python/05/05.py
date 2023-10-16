import math


class Stack[T]:
    # self az aktuális példányra hivatkozik (más nyelvekben this)
    def __init__(self):
        # privát adattag __ kezdés
        self.__items = []

    def pop(self) -> T:
        return self.__items.pop()

    def push(self, new_item: T):
        return self.__items.append(new_item)


# öröklődés
class CountingStack[T](Stack):
    def __init__(self):
        Stack.__init__(self)
        self.__counter = 0

    def push(self, new_item: T):
        Stack.push(self, new_item)
        self.__counter += new_item

    def pop(self) -> T:
        value = Stack.pop(self)
        self.__counter -= value
        return value

    def get_count(self):
        return self.__counter


counting_stack = CountingStack()
counting_stack.push(1)
counting_stack.push(2)

print(counting_stack.get_count())


class EmptyQueueException(Exception):
    def __str__(self):
        return f'EmptyQueueException: {self.__cause} called on empty queue.'

    def __init__(self, cause: str):
        Exception.__init__(self)
        self.__cause = cause


class Queue[T]:
    def __init__(self):
        self.__items = []

    def put(self, item: T):
        self.__items.append(item)

    def get(self) -> T:
        if len(self.__items) == 0:
            raise EmptyQueueException("get()")

        return self.__items.pop(0)


queue = Queue()
queue.put(5)
print(queue.get())

try:
    print(queue.get())
except EmptyQueueException as exception:
    print(exception)


class Point:
    def __init__(self, x: float = 0.0, y: float = 0.0):
        self.__x = x
        self.__y = y

    def get_x(self) -> float:
        return self.__x

    def get_y(self) -> float:
        return self.__y

    def distance_from_coordinates(self, x, y) -> float:
        return math.sqrt((self.__x - x) ** 2 + (self.__y - y) ** 2)

    def distance_from_point(self, point) -> float:
        return self.distance_from_coordinates(point.__x, point.__y)


point1 = Point(0, 0)
point2 = Point(1, 1)
print(point1.distance_from_point(point2))
print(point2.distance_from_coordinates(2, 0))
