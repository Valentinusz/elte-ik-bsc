︠6fa65a25-2784-4293-8438-1aafaae67a12s︠
#3
def digitSum(n):
    return sum(list(map(int,str(n))))

digitSum(12)

def repeating(n):
    if (n < 1):
        raise ValueError
    ls = [n]
    num = int((ls[-1] / 2) + digitSum(ls[-1]))
    while num not in ls:
        ls.append(num)
        num = int((ls[-1] / 2) + digitSum(ls[-1]))
    return [ls, len(ls) - ls.index(num)]

try:
    repeating(32)
    repeating(5)
    repeating(16)
    repeating(100)
    repeating(0)
    repeating(2) # nem fut le
except ValueError:
    print("wrong input")
finally:
    repeating(1)
#4

m = 4

def f(e,o):
    return (e[0] >= o[0] and e[1] == e[2] == o[1] == o[2]) or (e[2] > (o[0] + o[1] + o[2]))

def makeGraph(m):
    vertices = [(a,b,c) for a in [0..m] for b in [a..m] for c in [b..m]]
    return DiGraph([vertices,f],loops=true)

G = makeGraph(m)
G.show()
︡e67fe801-11f1-4523-a391-affaf459ce50︡{"stdout":"3\n"}︡{"stdout":"[[32, 21, 13, 10, 6, 9], 4]\n[[5, 7, 10, 6, 9, 13], 4]\n[[16, 15, 13, 10, 6, 9], 4]\n[[100, 51, 31, 19], 1]\nwrong input\n[[1], 1]\n"}︡{"file":{"filename":"/home/user/.sage/temp/project-e1900240-7de3-40e3-ae13-bd248a694085/467/tmp_qvz4r62m.svg","show":true,"text":null,"uuid":"5dc7463e-53af-48b8-a845-458ffde8807a"},"once":false}︡{"done":true}
︠94ea0063-6a4a-41b0-a318-aaa009e4d4c5s︠

m = int(raw_input())
G2 = makeGraph(m)
G2.show()

#5
def mersenne(n):
    counter = 0
    current = 1
    while(counter < n):
        next = next_prime(current)
        if is_prime(2**next-1):
            counter += 1
        current=next
    return 2**next-1

mersenne(1)
mersenne(2)
mersenne(3)
mersenne(4)
mersenne(5)
mersenne(15)
︡fb86552a-4f20-4a87-a1fc-ad7fc4b1b6f5︡{"raw_input":{"prompt":""}}︡{"delete_last":true}︡{"raw_input":{"prompt":"","submitted":true,"value":"4"}}︡{"file":{"filename":"/home/user/.sage/temp/project-e1900240-7de3-40e3-ae13-bd248a694085/467/tmp_64i59gxw.svg","show":true,"text":null,"uuid":"3cb3507f-fe66-4aca-aeea-06a906fb74c2"},"once":false}︡{"stdout":"3\n"}︡{"stdout":"7\n"}︡{"stdout":"31\n"}︡{"stdout":"127\n"}︡{"stdout":"8191\n"}︡{"stdout":"10407932194664399081925240327364085538615262247266704805319112350403608059673360298012239441732324184842421613954281007791383566248323464908139906605677320762924129509389220345773183349661583550472959420547689811211693677147548478866962501384438260291732348885311160828538416585028255604666224831890918801847068222203140521026698435488732958028878050869736186900714720710555703168729087"}︡{"stdout":"\n"}︡{"done":true}









