︠5814a164-f939-4afb-909e-a88072857a7c︠
# Írjon függvényt mely visszaadja az n szám valódi ósztóösszegét (nála kisebb osztóinak összegét)
def real_aliquot_sum(n):
    return sum(divisors(n)) - n

real_aliquot_sum(6)
︡c2b20761-0497-4e9a-87ae-271422f08899︡{"stdout":"6\n"}︡{"done":true}
︠6e09ae16-971c-46a0-8118-0dcb71b14907︠
# írjon függvényt ami előállítja egy szám osztóösszeg sorozatát
# https://hu.wikipedia.org/wiki/Oszt%C3%B3%C3%B6sszeg-sorozat
# az eredmény formátuma [[osztóösszeg sorozat tömbként], (True ha a sorozat véges | False ha a sorozat végtelen, periódus hossza | None ha nem ismétlődik)]
# pl. [[6], (False, 1)]

def aliquot_sequence(n):
    divs = [n]
    new_item = real_aliquot_sum(n)
    while new_item != 0 and new_item not in divs:
        divs.append(new_item)
        new_item = real_aliquot_sum(new_item)
    if new_item == 0:
        return [divs,(True, None)]
    else:
        return [divs,(False, len(divs) - divs.index(new_item))]

aliquot_sequence(6)
aliquot_sequence(12)
aliquot_sequence(220)
aliquot_sequence(284)
aliquot_sequence(1000)
aliquot_sequence(960)
︡747357e3-0ddf-4ca7-b22c-2558dc685719︡{"stdout":"[[6], (False, 1)]\n"}︡{"stdout":"[[12, 16, 15, 9, 4, 3, 1], (True, None)]\n"}︡{"stdout":"[[220, 284], (False, 2)]\n"}︡{"stdout":"[[284, 220], (False, 2)]\n"}︡{"stdout":"[[1000, 1340, 1516, 1144, 1376, 1396, 1054, 674, 340, 416, 466, 236, 184, 176, 196, 203, 37, 1], (True, None)]\n"}︡{"stdout":"[[960, 2088, 3762, 5598, 6570, 10746, 13254, 13830, 19434, 20886, 21606, 25098, 26742, 26754, 40446, 63234, 77406, 110754, 171486, 253458, 295740, 647748, 1077612, 1467588, 1956812, 2109796, 1889486, 953914, 668966, 353578, 176792, 254128, 308832, 502104, 753216, 1240176, 2422288, 2697920, 3727264, 3655076, 2760844, 2100740, 2310856, 2455544, 3212776, 3751064, 3282196, 2723020, 3035684, 2299240, 2988440, 5297320, 8325080, 11222920, 15359480, 19199440, 28875608, 25266172, 19406148, 26552604, 40541052, 54202884, 72270540, 147793668, 228408732, 348957876, 508132204, 404465636, 303708376, 290504024, 312058216, 294959384, 290622016, 286081174, 151737434, 75868720, 108199856, 101437396, 76247552, 76099654, 42387146, 21679318, 12752594, 7278382, 3660794, 1855066, 927536, 932464, 1013592, 1546008, 2425752, 5084088, 8436192, 13709064, 20563656, 33082104, 57142536, 99483384, 245978376, 487384824, 745600776, 1118401224, 1677601896, 2538372504, 4119772776, 8030724504, 14097017496, 21148436904, 40381357656, 60572036544, 100039354704, 179931895322, 94685963278, 51399021218, 28358080762, 18046051430, 17396081338, 8698040672, 8426226964, 6319670230, 5422685354, 3217383766, 1739126474, 996366646, 636221402, 318217798, 195756362, 101900794, 54202694, 49799866, 24930374, 17971642, 11130830, 8904682, 4913018, 3126502, 1574810, 1473382, 736694, 541162, 312470, 249994, 127286, 69898, 34952, 34708, 26038, 13994, 7000, 11720, 14740, 19532, 16588, 18692, 14026, 7016, 6154, 3674, 2374, 1190, 1402, 704, 820, 944, 916, 694, 350, 394, 200, 265, 59, 1], (True, None)]\n"}︡{"done":true}
︠15fa6ebb-04c9-45bc-ad23-12f48ba79f42︠
# Írjon függvényt, mely megkeresi azt a legkisebb pozitív egész számot, amelynek véges osztóösszeg-sorozatában előfordul a paraméterként megadott n szám. Írja ki ezt a számot, a sorozat hosszát és a sorozatot is.
# Tesztelje a függvényt az 179931895322 számmal.
def find_num_in_aliquot_sequence(n):
    i = 1
    while True:
        list = aliquot(i)
        if n in list[0]:
            return i, len(list), list
        i += 1
    return None

find_num_in_aliquot_sequence(179931895322)
︡828bb923-9451-4c6e-9068-613771923be7︡{"stdout":"(138, 2, [[138, 150, 222, 234, 312, 528, 960, 2088, 3762, 5598, 6570, 10746, 13254, 13830, 19434, 20886, 21606, 25098, 26742, 26754, 40446, 63234, 77406, 110754, 171486, 253458, 295740, 647748, 1077612, 1467588, 1956812, 2109796, 1889486, 953914, 668966, 353578, 176792, 254128, 308832, 502104, 753216, 1240176, 2422288, 2697920, 3727264, 3655076, 2760844, 2100740, 2310856, 2455544, 3212776, 3751064, 3282196, 2723020, 3035684, 2299240, 2988440, 5297320, 8325080, 11222920, 15359480, 19199440, 28875608, 25266172, 19406148, 26552604, 40541052, 54202884, 72270540, 147793668, 228408732, 348957876, 508132204, 404465636, 303708376, 290504024, 312058216, 294959384, 290622016, 286081174, 151737434, 75868720, 108199856, 101437396, 76247552, 76099654, 42387146, 21679318, 12752594, 7278382, 3660794, 1855066, 927536, 932464, 1013592, 1546008, 2425752, 5084088, 8436192, 13709064, 20563656, 33082104, 57142536, 99483384, 245978376, 487384824, 745600776, 1118401224, 1677601896, 2538372504, 4119772776, 8030724504, 14097017496, 21148436904, 40381357656, 60572036544, 100039354704, 179931895322, 94685963278, 51399021218, 28358080762, 18046051430, 17396081338, 8698040672, 8426226964, 6319670230, 5422685354, 3217383766, 1739126474, 996366646, 636221402, 318217798, 195756362, 101900794, 54202694, 49799866, 24930374, 17971642, 11130830, 8904682, 4913018, 3126502, 1574810, 1473382, 736694, 541162, 312470, 249994, 127286, 69898, 34952, 34708, 26038, 13994, 7000, 11720, 14740, 19532, 16588, 18692, 14026, 7016, 6154, 3674, 2374, 1190, 1402, 704, 820, 944, 916, 694, 350, 394, 200, 265, 59, 1], (True, None)])\n"}︡{"done":true}
︠5e639e5b-f019-44e9-8fe6-729a543bc313︠
# Írjon függvényt, amely a paraméterként kapott egész szám osztóösszeg-sorozata alapján eldönti, hogy a szám barátságos-e.
# https://hu.wikipedia.org/wiki/Bar%C3%A1ts%C3%A1gos_sz%C3%A1mok

def is_amicable(n):
    list = aliquot_sequence(n)
    if len(list[0]) == 2 and list[1][0] == False and list[1][1] == 2:
        return True
    return False

is_amicable(220)
is_amicable(284)
is_amicable(60)
︡268a13fa-81f4-46e5-aee5-6c84676d13fb︡{"stdout":"'4. Írjon függvényt, amely a paraméterként kapott egész szám osztóösszeg-sorozata alapján eldönti, hogy a szám barátságos-e.'\n"}︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"done":true}
︠2276f7c1-d91a-4a9a-9ec4-79e50cb739cd︠
################################
#                              #
#          Függvények          #
#                              #
################################

# a var függvénnyel egy változótnevet szimbolikussá tehetünk
var(x)

# ezt követően a változót használhatjuk függvények változóinak kifejezésére
f(x)=2*x+4
f
︡6d92c3dd-6d69-4d7c-89c2-f0958b1563ba︡{"stdout":"x\n"}︡{"stdout":"2*x + 1\n"}︡{"stdout":"x |--> 2*x + 4\n"}︡{"done":true}
︠f1269559-2464-44d3-a0ba-893695fbd7af︠
f(x)
︡df384ca2-d88f-4718-a68a-7dac997f288f︡{"stdout":"2 \\, x + 4\n"}︡{"done":true}
︠70aef7b3-6d3f-4385-a556-cde1b26c6568︠
type(f)
︡ab46d610-b23c-4e95-9eab-60f33fe2d21a︡{"stdout":"<class 'sage.symbolic.expression.Expression'>\n"}︡{"done":true}
︠a4a28c49-d173-4cd7-b746-5b8791574bba︠
f(-1)  # helyettesítési értel
︡52d1dd60-686c-4f56-9014-b0e6f1a651b0︡{"stdout":"2\n"}︡{"done":true}
︠2a738d0f-d7e2-4458-a5db-e4fc20938612︠
g(x) = sin(x)  # szögfüggvények
g(0)
g(pi / 2)  # pi konstans
︡3a6e7fdb-fbd3-44a8-9e12-ff5649364b3b︡{"stdout":"0\n"}︡{"stdout":"1\n"}︡{"done":true}
︠ac9cce17-6083-4a8b-83c3-058a30c13991︠
g.roots()  # zérushelyek lekérése
︡fce606dc-edd9-4aeb-af79-dde4653dabab︡{"stdout":"[(0, 1)]"}︡{"stdout":"\n"}︡{"done":true}
︠6a56445c-ccf5-4569-9d49-26ff1bd222d9︠
g.plot()
︡04bab7b9-c484-4b83-a923-ecb3e215cee2︡{"file":{"filename":"/home/user/.sage/temp/project-48908dd2-23b3-4b66-bbbf-be30830d5128/791/tmp_odgg_w6i.svg","show":true,"text":null,"uuid":"0d52045c-220e-41bc-80a7-909a1fc7d059"},"once":false}︡{"done":true}
︠cc8bcb32-6e8a-48a0-81e6-b3322b9db04d︠
# Megadható hogy egy függvény milyen gyűrű fölött legyen értelmezve
# https://hu.wikipedia.org/wiki/Gy%C5%B1r%C5%B1_(matematika)
var('a')
p1 = ZZ['a']((a^2) +5*a + 12) # ZZ, egész számok
p1.roots()

p2 = QQ['a']((a^2) +5*a + 12) # QQ, racionális számok
p2.roots()

p3 = CC['a']((a^2) +5*a + 12) # CC, racionális számok
p3.roots()
︡5312670b-0c04-40bb-bc10-8ded900390bc︡{"stdout":"a\n"}︡{"stdout":"[]\n"}︡{"stdout":"[]\n"}︡{"stdout":"[(-2.50000000000000 - 2.39791576165636*I, 1), (-2.50000000000000 + 2.39791576165636*I, 1)]\n"}︡{"done":true}
︠cb0f9fd1-5953-4a6d-b29a-fc5358dbfcea︠
p1.roots(ring = CC)  # nem muszály magát a függvényt leszűkíteni megadhatjuk a zérushely lekérdezésnél is milyen gyűrűbeli gyököket adjon meg
︡9d8aeef8-aeef-4329-9c04-1d5c6af8bb1b︡{"stdout":"[(-2.50000000000000 - 2.39791576165636*I, 1), (-2.50000000000000 + 2.39791576165636*I, 1)]\n"}︡{"done":true}
︠32a15f55-5d51-469e-8807-2b4cbc826084︠
p = ZZ['x']([1,2,0,4]) # elem indexe lesz a tag hatványa, az elem pedig az együtthatója
p
︡663dafa4-f47d-4be2-944c-2faa3dafaaa7︡{"stdout":"4*x^3 + 2*x + 1\n"}︡{"done":true}
︠cdfb6138-aa1e-4d30-aea8-b927c7f163cf︠
p[1] # együtthatót ad vissza
︡23f49954-5aab-4dbe-ae75-39e8f96c0055︡{"stdout":"2\n"}︡{"done":true}
︠600b4ec1-6416-4a03-bbcc-8f6daae0dbe0︠
p = RR['x']([1,2,0,4])
p.leading_coefficient() # főegyüttható
p.constant_coefficient() # konstans tag
p.degree() # fok
︡708f3285-816c-4abc-ab6b-a44deb47a771︡{"stdout":"4.00000000000000\n"}︡{"stdout":"1.00000000000000\n"}︡{"stdout":"3\n"}︡{"done":true}
︠049d5b42-5726-49d9-9dc0-1f65698fed78︠
p.list()
p.dict()
p.roots()

gcd = p.gcd(2*p) # legnagyobb közös osztó polinomokra
︡8217e6c9-13d9-4b0c-96d4-2a02e0100366︡{"stdout":"[1, 2, 0, 4]\n"}︡{"stdout":"{0: 1, 1: 2, 3: 4}\n"}︡{"stdout":"[]\n"}︡{"done":true}
︠4e1bbbaa-717b-4bae-8cdc-c0635b65dbf9︠
gcd
︡a5cd5f1f-36bb-46a0-b887-7f2f19481cd3︡{"stdout":"4*x^3 + 2*x + 1\n"}︡{"done":true}
︠7be3904b-6a57-47de-aad9-5a9524f73de2︠
p.divides(gcd)
︡fd323030-3f94-4480-8afd-64c5d735720c︡{"stdout":"True\n"}︡{"done":true}
︠02ec1aa3-8871-414f-afac-fd267b897fac︠
# Készítsen olyan valós együtthatós polinomot, melynek gyökei 3, 12.5, -45, 12, -2
# Ha a egy f függvény gyöke akkor (x-a)-val osztható f
p(x) = (x-3)*(x-12.5)*(x+45)*(x-12)*(x+2)
expand(p)
︡cf2c808e-b521-4d7c-96ef-44dea88b18e5︡{"stdout":"x |--> x^5 + 19.5000000000000*x^4 - 979.000000000000*x^3 + 7579.50000000000*x^2 - 1035.00000000000*x - 40500.0000000000\n"}︡{"done":true}
︠02c8ab0a-5fa1-4a5b-a9d2-1a57344490c1︠
p.plot() # Grafikon kirajzolása
︡e2688957-bfee-4b9a-a000-947559873743︡{"file":{"filename":"/home/user/.sage/temp/project-48908dd2-23b3-4b66-bbbf-be30830d5128/791/tmp_15337oin.svg","show":true,"text":null,"uuid":"1e73a4b9-0bc1-4eb8-a94c-51c88fd85033"},"once":false}︡{"done":true}
︠59c586b7-2c6b-4e8d-855a-773beef03742︠
# Állítson elő egy polinomot, melynek együtthatói n, (n-1), .., 1
def decreasing_coefficient(n,var):
    list = [i for i in range(0,n+1)]
    p = RR[var](list)
    return p

o = decreasing_coefficient(5,'x')
o
︡3fed4052-912b-4d2f-9430-c00a6c745be9︡{"stdout":"5.00000000000000*x^5 + 4.00000000000000*x^4 + 3.00000000000000*x^3 + 2.00000000000000*x^2 + x\n"}︡{"done":true}
︠4cf9e8b8-746f-4eb9-8524-c30cd51f5951︠
plot(((x^3)/3).diff(), 3, 6) # ábrázolás tartományának megadása
︡c712da41-0652-4346-b23d-6a953fa4b5a3︡{"file":{"filename":"/home/user/.sage/temp/project-48908dd2-23b3-4b66-bbbf-be30830d5128/620/tmp_2hcvm_h_.svg","show":true,"text":null,"uuid":"e5c8c72a-6735-4fc7-b7f7-348317b61468"},"once":false}︡{"done":true}









