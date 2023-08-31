# erősen dinamikusan típusos értelemzett nyelv
# értelmező megynitható paranccsorból a py/python (windows) python 3 (unix) paranccsal
# a nyelv nem használ ;-őt és {}-et (sor végét új sor a blokkokat pedig a behúzás mértéke határozza meg)

# változók, kiiratás
a = 42  # változó deklarálás
b = 32

print(type(a))  # típus lekérése

c = 'valami'  # string lehet '-el és "-el is, van többsoros string is """
d = """abv
sdasds
sdadsd"""
print(c)
print("d: %s" % d)  # formázott kiiratás (c szerű specifier)
print(f"a/b = {a/b}")  # formázott kiiratás (fstring)
# print(a + c) nem működne mert a típusrendszer erős
print(str(a) + c)  # str konvertáló függvény (többi típushoz is van ilyen)


# matematikai operátorok
# megszokott matematikai műveletek elérhetők
print(a+b)  # kiiratás: print függvény
print(a*b)
print(a/b)
print(a**2)  # extra: hatványozás
print(10 % 2)
a += 1  # nincs ++/-- operátor

round(3.88, 1)  # kerekítés

import math  # modul importálása
math.ceil(3.14)  # felső egészrész
math.floor(3.2)  # alsó egészrész


# string műveletek
print('alma'.upper())
print('alma'.lower())
print("LO" in "HELLO")  # in operátor tartalmazást adja vissza
print(len('alma'))  # stringhossz

# listák
# heterogén adatszerkezetek
players = [12, 31, 27, '48', 54]

print(players[0])       # 0-tól indexelünk
print(players[-1])      # hátulról is lehet indexelni
print(players[2:4])     # intervallumot is megadhatunk (balról zárt jobbról nyitott)
print(players[:-1])     # intervallum kezdete alapból az első elem
print(players[1:])      # intervallum vége alapból az utolsó elem
print(len(players))     # listahossz
players.append(89)      # hozzáadás a listához
del players[2]          # törlés index alapján
players[2] = 48
print(sorted(players))  # lista rendezése (csak ha homogén)
print(42 in players)    # tartalmazásvizsgálat


# rendezett n-esek (tuple)
# heterogén módosíthatatlan adatszerkezet
playerst1 = (12, 31, 27, '48', 54)
playerst2 = tuple(players)  # konvertálás más adatszerkezetből

# halmazok
mylist = [8, 3, 2, 3, 2, 4, 6, 8, 2]
myset1 = set(mylist)  # konvertálás más adatszerkezetből
myset2 = {8, 3, 2, 3, 2, 4, 6, 8, 2}
# halmazműveletek
myset1.intersection(myset2)
myset1.union(myset2)
myset1.difference(myset2)


# szótár
team = {
    91: "Ayers, Robert",
    13: "Beckham Jr,",
    3: "Brown, Josh",
    54: "Casillas, Jonathan",
    21: "Collins, Landon"}
print(len(team))  # hossz
print(team[3])  # adott kulcsú elem lekérése
print(team.keys())  # kulcshalmaz lekérése (list() függvénnyel listává alakítható)
print(team.values())  # értékek lekérése
print(91 in team)  # tartalmazásvizsgálat

# elágazások
if 100 in team:
    print('100: Yes')
elif 76 in team:
    print('76: Yes')
else:
    print('Neither')


# ciklus
# for-each
for i in players:
    print(i)

# for emulálása
for i in range(1, 10): # range intervallum
    print(i)

for i in range(2, 2, 10): # lépés megadható
    print(i)

j = 3
while j < 5:
    j = j+1

# rendezett n-esek is lehetnek ciklusváltozók
for (k, v) in team.items():
    print(f"Playername: {v}, #: {k}")

# függvények


def is_even(num):
    if (num % 2) == 0:
        return True
    else:
        return False


# több visszatérési érték lehet a tuple-ök miatt

def multiple_return_values(x):
    return x**2, x**3, x**4


print(multiple_return_values(2))
a, b, c = multiple_return_values(2)  # tuple kibontása
print(a, b, c)


# funkcionális elemek
# generátorok
myGenList = [i**2 for i in range(1, 10)]
print(myGenList)

myGenDict = {i: i**2 for i in range(1, 5)}
print(myGenDict)

myGenTuple = (i for i in range(1, 4))
print(tuple(myGenTuple))

# map és lambda
temperatures = (36.5, 37, 37.5, 38, 39)
F = map(lambda x: (float(9)/5)*x + 32, temperatures)
print(list(F))

nums = [2, 32, 32, 32, 3, 123, 21, 321, 312, 32, 31, 23, 2, 32, 13, 23]
even_nums = filter(lambda x: not is_even(x),nums)
print(list(even_nums))


# fájlkezelés ('r' - olvasás, 'w' - írás, 'a' - append)
# with blokkből kilépéskor a fájl bezáródik
with open('teszt.txt', 'r') as file:
    # f.readline()
    # f.read()
    fdict = {}
    for i in file:
        line = i.split()  # split listává bont egy stringet egy karakter mentén default: space
        fdict[line[0]] = line[1]

    for k in fdict.keys():
        print(fdict[k])


# standard input
x = int(input("Szám: "))  # mindig str kerül beolvasásra


# parancssori paraméterek
import sys
print(sys.argv[0])  # script neve
#print(sys.argv[1])  # első paraméter ...


# osztályok


class Hallgato:
    nev = ' '
    ZHpont = 0

    def __init__(self, _name, _point):  # konstruktor
        self.nev = _name  # self == this
        self.ZHpont = _point

    def __str__(self):  # __str__ = toString
        return self.nev+"("+str(self.ZHpont)+")"


p = Hallgato("Ford", 20)
print(p)
print(p.nev)
p.nev = "Fort"
print(p.nev)

# lehet definiálni main függvényt


def main():
    print("Ez a main")


if __name__ == "__main__":
    print ("Ez akkor fog lefutni ha scriptkent hivod meg, ha beimportálod másba nem!")
    main()

# JSON (Javascript object notation)

import json

data = {
    "president": {
    "name": "Zaphod Beeblebrox",
    "species": "Betelgeusian"
    }
}

with open("data_file.json", "w") as write_file:
    json.dump(data, write_file)  # json fájlba mentése

json_string = json.dumps(data)  # json stringgé alakítása


# beolvasás

# fájlból
with open("data_file.json", "r") as read_file:
    data = json.load(read_file)
    print( data["president"]["name"] )

#stringből
json_string = """
{
    "researcher": {
        "name": "Ford Prefect",
        "species": "Betelgeusian",
        "relatives": [
            {
                "name": "Zaphod Beeblebrox",
                "species": "Betelgeusian"
            }
        ]
    }
}
"""
data = json.loads(json_string)
for rel in data["researcher"]["relatives"]:
    print('Name: %s (%s)' % ( rel["name"], rel["species"] ) )