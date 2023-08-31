def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0:
            if year % 400 == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False


with open('years.txt', 'r') as file:
    for i in file:
        if is_leap_year(int(i)):
            print("%s igen" % i.strip())
        else:
            print("%s nem" % i.strip())
