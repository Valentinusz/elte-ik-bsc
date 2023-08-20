import random
# A szöveges adatfile így épüljön fel: GPS koordináták(szélesség, hosszúság), dátum, idő, mért érték
allomasok = ["47.6498634 19.1404118", "47.5127777 19.0261111", "47.5111111 19.0280556"]
napok = ["2021.11.01", "2021.11.02", "2021.11.03", "2021.11.04", "2021.11.05", "2021.11.06", "2021.11.07"]
idopontok = ["04:20", "09:11", "08:00", "14:00", "16:20", "22:00"]

sorok_szama = int(input('Add meg a sorok számát(max. 42 mert lusta voltam normális programot írni): '))
# de viszonylag könnyen bővíthető

adatok = []
for i in napok:
    for j in idopontok:
        adatok.append([allomasok[random.randint(0, 2)], i, j, random.randrange(0, 20)])

with open("adatok.txt", "w") as file:
    for i in range(sorok_szama):
        for j in adatok[i]:
            print(j, file=file, end=" ")
        print('', file=file)

