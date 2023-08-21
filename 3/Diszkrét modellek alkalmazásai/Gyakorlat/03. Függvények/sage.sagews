︠83c8ab50-92e2-43c8-ba20-b4ec26840148︠
s1 = set([1,2,3]) # módosítható (mutable) osztály
s2 = Set([1,2,3]) # nem módosítható (immutable) osztály

def function_value(f,x):
    for i in f:
        if (x == i[0]):
            return i[1]
    return none  # none == null

f = [[1,2],[2,1],[3,1],[4,3]]
function_value(f,2)
︡81c16afb-5823-4d45-b376-648cc4c169fa︡{"stdout":"1\n"}︡{"done":true}
︠e953d421-630d-456f-8f15-8d0300d19790s︠

def is_surjective(f,A):
    values = set([])
    for i in values:
        values.add(i[1])
    return values == A

is_surjective(f,{1,2,3})
︡2e22050d-5af9-441b-8e50-f0e668bb8904︡{"stdout":"False\n"}︡{"done":true}
︠15b4b5d7-dc9e-456a-99d7-215b0b47de48s︠

def is_injektive(f):
    for i in set([x for [x,y] in f]):
        if (len([y for [x,y] in f if x == i]) != 1):
            return false
    return true

f1 = [[1,'a'],[4,'e'],[5,'d']]
f2 = [[1,'a'],[4,'e'],[5,'d'],[1,'b']]
is_injektive(f1)
is_injektive(f2)
︡7fec3e8d-5c0c-477d-a910-fcb310e5f786︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"done":true}
︠945fccbe-8a13-454e-b6a7-9039a190d2dds︠

def is_irreducible(x):  # felbonthatatlan ha önmaga és 1 osztója csak
    a = abs(x)
    if a == 0 or a == 1:
        return false
    for b in [2..a-1]:
        for c in [2..a-1]:
            if b*c == a:
                return false
    return true

is_irreducible(2)
is_irreducible(3)
is_irreducible(4)
is_irreducible(5)
is_irreducible(9)
is_irreducible(97)
︡0bb6ab8e-66cf-423d-8a55-f3a954cb0a7c︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"done":true}
︠f9feb6af-3de9-4179-aaf4-5591923029c6︠
# n szám-hoz n hosszú lista amiben olyan egymást követő számok vannak ahol mind az n szám összetett
def compound_list(n):
    i = 0
    while(true):
        nums = [j for j in range(i,i+n+1) if not is_irreducible(j)]
        if (len(nums) == n):
            return nums
        i = next_prime(i)
compound_list(10)
︡f295c93f-2ceb-47f5-ab48-87164be171b2︡{"stdout":"[114, 115, 116, 117, 118, 119, 120, 121, 122, 123]"}︡{"stdout":"\n"}︡{"done":true}
︠84abbf4d-0bc3-4e61-b7e1-516ff4dee012︠


# minden kettőnél nagyobb páros szám felírható két pírm szám összegeként + Hibakezelés
# throw = raise
# except = catch
def even_Goldbach(a):
    if a % 2 == 1 or a <= 2:
        raise ValueError()
    for i in prime_range(a/2+1):
        if (is_prime(a-i)):
            return [i, a-i]

try:
    even_Goldbach(6)
    even_Goldbach(1)
except ValueError:
    print("Rossz arugmentum.")
finally:
    even_Goldbach(28)
︡5e1562bc-31e4-4e06-914f-7bfa3dbad667︡{"stdout":"[3, 3]\nRossz arugmentum.\n[5, 23]\n"}︡{"done":true}
︠d133074a-c54d-40aa-8a91-f31d9e807799︠
#írjunk függvényt mely eldönti két szám bartáságos-e (önmagán kívüli osztók összege a másik szám) https://en.wikipedia.org/wiki/Amicable_numbers
def is_amicable(a,b):
    if a <= 0 or b <= 0 or a == b:
        raise ValueError
    return sum(divisors(a))-a == b and sum(divisors(b))-b == a  # divisors listában visszadja a szám osztóit

try:
    is_amicable(-5,1)
    is_amicable(1,1)
except ValueError:
    print("Rossz argumentum.")
finally:
    is_amicable(220,284)
    is_amicable(1184, 1210)
    is_amicable(5,10)
︡9ad984dc-360d-4c49-82af-9db152b37cfb︡{"stdout":"Rossz argumentum.\nTrue\nTrue\nFalse\n"}︡{"done":true}
︠99afca2c-fdd3-4055-8cf8-21874f989f75︠
x = int(raw_input('Enter an integer: '))  # input bekérése (input() nem használható)
y = int(raw_input('Enter another integer: '))
is_amicable(x,y)
︡125c0984-e40b-4ef0-bdbd-1f91e47c6125︡{"raw_input":{"prompt":"Enter an integer: "}}︡{"delete_last":true}︡{"raw_input":{"prompt":"Enter an integer: ","submitted":true,"value":"220"}}︡{"raw_input":{"prompt":"Enter another integer: "}}︡{"delete_last":true}︡{"raw_input":{"prompt":"Enter another integer: ","submitted":true,"value":"284"}}︡{"stdout":"True\n"}︡{"done":true}
︠eb76d103-a8d2-4c6e-bfce-290b13582bc1︠
#tökéletes szám: önmagával barátságos
def is_perfect(x):
    return sum(divisors(x))-x == x;

is_perfect(4)
is_perfect(6)
︡f4d1b5fe-cee5-4bd6-b1dd-32058d6a8485︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"done":true}
︠fadd2587-f015-402a-9194-ed0ad3c879f0︠

# Eratoszthenészi szita algoritmus, megadja az [0..n] intervallumbeli prímeket https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
eratosthenes(11)
︡fa1a4716-9479-4405-a9e3-dc616e4557a6︡{"stdout":"[2, 3, 5, 7, 11]\n"}︡{"done":true}









