︠907a4449-8755-4c2a-947d-c935685f60c2︠

︡beb472db-010d-4c30-8cfa-a2fd33a3df86︡
︠042f60e2-5fec-4e30-8201-58829acda791︠
#5

s = set([1,5,5,23,25,75,24])

def getRealDivisorCount(s):
    if not s:
        raise ValueError
    result = []
    for i in s:
        divs = [div for div in range(2, i) if i % div == 0]
        result.append((i, divs))
    return result

try:
    empty = set([])
    getRealDivisorCount(empty)
except ValueError:
    print("Set is empty.")
finally:
    getRealDivisorCount(s)
︡d2c4a524-a207-420c-b273-222ab8c557c7︡{"stdout":"Set is empty.\n[(1, []), (5, []), (75, [3, 5, 15, 25]), (23, []), (24, [2, 3, 4, 6, 8, 12]), (25, [5])]\n"}︡{"done":true}
︠769c12d5-38de-4370-a1c2-80d0fd001459s︠

primes = [i for i in primes(112,1000) if 7 % 235 == i % 235]
︡24af4577-83bc-42e5-be68-b6ecfc88da13︡{"done":true}
︠2dc6a2a5-975c-41fc-a383-14e4e74b8392s︠
primes
︡97eb6165-4d71-4775-b66f-929d6de9c4b7︡{"stdout":"[947]\n"}︡{"done":true}
︠02e1db8a-8319-4445-9de9-f5e753fd91f7︠

#6
m = int(raw_input())

vertices = [(a,b,c) for a in [1..m] for b in [a..m] for c in [b..m] if not (a == b) and not (b ==c) and not (a==c)]

vertices

def hasEdge(v1, v2):
    return v1[0] + v1[1] + v1[2] < v2[0] * v2[1] * v2[2]

G = DiGraph([vertices,hasEdge])
G.show()

︡8c604f70-37cc-44ac-a8ff-eed80c785256︡{"raw_input":{"prompt":""}}︡{"delete_last":true}︡{"raw_input":{"prompt":"","submitted":true,"value":"4"}}︡{"stdout":"[(1, 2, 3), (1, 2, 4), (1, 3, 4), (2, 3, 4)]\n"}︡{"file":{"filename":"/home/user/.sage/temp/project-efe88c2b-46f8-402f-908a-a35975fdac04/595/tmp_y50ae01q.svg","show":true,"text":null,"uuid":"facf3a6f-b506-495b-8739-5c0ba75793f3"},"once":false}︡{"done":true}
︠b2aa2660-8451-452f-a4b8-0903ff0cee26s︠
︡0f731df7-b6b8-478e-a526-dd5c4669ecff︡{"done":true}
︠91c8dd7f-c4b1-4d74-bb32-d948e264bb72s︠
#7

def sum_of_squares(n):
    squares = [a^2 for a in [0..sqrt(n)]]
    result = []
    for s in squares:
        if (n-s) in squares:
            tmp = n-s
            if (tmp, s) not in result:
                result.append((s,tmp))
    return result

v = []
i = -1
while len(v) != 3 :
    i = i+1
    v = sum_of_squares(i)

i
v
︡4449f898-5932-4301-b337-8b144e3aba1e︡{"stdout":"325\n"}︡{"stdout":"[(1, 324), (36, 289), (100, 225)]\n"}︡{"done":true}









