︠884a160b-7475-4b57-847a-22ce27de3765︠
# +/-
# !1. ZH 2022. 10. 17!
def isTransitive(set,rel):
    cprod = [(i,j,k) for i in set for j in set for k in set]
    for (i,j,k) in cprod:
        if [i,j] in rel and [j,k] in rel and not [i,k] in rel:
            return False
    return True

def While_Not_Tranzitive(A,R):
    full = [[a,b] for a in A for b in A]

    i = 0
    while(not isTransitive(A,R)):
        if full[i] not in R:
            R.append(full[i])
        i += 1
    return R


#Készítsünk eljárásokat, melyek az ún. 3n+1 problámát viszgálják.
#Legyen f az a pozitív egész számokon értelmezett függvény, mely páros n-re n/2, páratlan n-re 3n+1 értéket vesz fel.
#Egy sejtés szerint ezt kellően sokszor ismételten alkalmazva, bármilyen kiindulási n-re lesz olyan, hogy 1-et kapunk (pl. 7-22-11-34-17-52-26-13-40-20-10-5-16-8-4-2-1).
#Írjunk egy olyan eljárást, mely meghatározza, hogy a paraméterként megadott n-re hány lépés múlva következik ez be (pl. 7-re 16 lépés kell). Egy másik eljárással pedig számoljuk meg, hogy mindeközben hány páros szám volt a sorozatban (a kiindulási értékkel együtt).

def three_n_plus_one(n):
    if is_even(n):
        return n/2
    else:
        return 3*n+1

def repeat_while_not_one(n):
    list = []
    list.append(n)
    while (list[-1] != 1):
        list.append(three_n_plus_one(list[-1]))
    return list

def even_while_not_one(n):
    return len(list(filter(is_even, repeat_while_not_one(n))))

def number_of_steps(n):
    return len(repeat_while_not_one(n))-1

repeat_while_not_one(7)
even_while_not_one(7)
number_of_steps(7)
︡1b3f4f69-e555-4672-8f0d-bc71ab5bb204︡{"stdout":"[7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1]\n"}︡{"stdout":"11\n"}︡{"stdout":"16\n"}︡{"done":true}
︠600dc9e9-8198-4342-95e9-09d2aef56019s︠


def lnko(a,b):
    if a == b:
        return a
    if a < b:
        a, b = b, a
    while (b > 0):
        a, b = b, a % b
    return a


lnko(30,70)
lnko(126,150)
lnko(105,231)
lnko(132,275)
lnko(33,21)
︡b5290b0d-14d3-40e5-bb75-0c321ddcf56a︡{"stdout":"10\n"}︡{"stdout":"6\n"}︡{"stdout":"21\n"}︡{"stdout":"11\n"}︡{"stdout":"3\n"}︡{"done":true}









