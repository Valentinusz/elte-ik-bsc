︠47be6474-0242-4249-bf1e-e24c95784a31︠
#Python:
#dinamikusan típusos értelmezett nyelv
#erősen típusos programozás nyelv
#a nyelv nem használ ;-t és {}-t a blokkokat behúzás jelöli, a sorok végét pedig az új sor karakter (; használatával azonban megtehetjük azt, hogy több utasítást 1 sorba írjunk)
x = 3 ; print(x)
︡7f3f5695-95cb-4cae-acfb-9b30868c2fda︡{"stdout":"3\n"}︡{"done":true}
︠8c40460c-11d3-424a-bd0d-86615880efces︠
x = "asd"
x #az értelmezőben opcionális a print függvényhívás
︡b7612062-4581-4fa8-9b18-2c0529c0754e︡{"stdout":"'asd'\n"}︡{"done":true}
︠90a29c26-83e7-4e83-a95d-aadf510252db︠

#a Python önmagában kifejezetten sok matematikai funkcióval rendelkezik például:
interval1 = range(1,10) #intervallum jelölés, kiíratás bugos
interval1
︡f1a492d0-aa29-4e55-aeb6-1086fe5e4253︡{"stdout":"range(1, 10)\n"}︡{"done":true}
︠64437038-8223-4b70-9dc3-996323a9bf24s︠
#a használt sageMath könyvtár biztosít egy alternatív jelölést
interval2 = [1..10]
interval2
︡2b425d64-2c30-430a-a14f-aaf9ae955bdf︡{"stdout":"[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]\n"}︡{"done":true}
︠e4126774-13c5-433e-888d-92cea1e99889︠
#mindkét jelölési formánál megadható lépésfok
interval3 = range(1,10,2)
interval3
︡3c120fe1-2cf0-45ea-a89f-a1e946259302︡{"stdout":"range(1, 10, 2)\n"}︡{"done":true}
︠3daae18a-af0d-40f5-892a-e1765f496534s︠
interval4 = [1,3..10]
interval4
︡668cc5e3-6e62-412c-9f13-5be32d4be393︡{"stdout":"[1, 3, 5, 7, 9]\n"}︡{"done":true}
︠f437e29f-b9f3-46ea-a70f-bd7816bcff80︠
set1 = {1,2,3} #halmaz típus
set2 = {i for i in [2..10]} #halmaz építő jelölés
set1
set2
set1.union(set2)
set1.intersection(set2)
︡9ff03456-30f9-4a81-aebd-507dd749418a︡{"stdout":"{1, 2, 3}\n"}︡{"stdout":"{2, 3, 4, 5, 6, 7, 8, 9, 10}\n"}︡{"stdout":"{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}\n"}︡{"stdout":"{2, 3}\n"}︡{"done":true}
︠b893dd0c-a756-483d-9cf2-78c0dcf24135︠
set3 = {i for i in [2..10] if i % 2 == 0} #halmaz épító jelölésben megadható predikátum is
set3
︡04c9b37a-1ecb-4b04-afbc-09545581261c︡{"stdout":"{2, 4, 6, 8, 10}\n"}︡{"done":true}
︠562d1e6e-fc7f-43cd-98e2-98fea07b7982︠
list1 = [i*i for i in [2..10]] #az elemeket módosíthatjuk is
list1
︡3144e4a2-6e41-4b7d-b02f-4c6a06cbf51a︡{"stdout":"[4, 9, 16, 25, 36, 49, 64, 81, 100]\n"}︡{"done":true}
︠9f225047-e2a9-44fe-a2dd-5de2c113b898s︠
sum(list1)
︡45344955-7809-408c-b8fa-0dd7bf2b9ce0︡{"stdout":"384\n"}︡{"done":true}
︠c39c6103-07ca-423d-9eda-8b5879f11670s︠
#logika
true and true
true or false
true and false
︡08ef9860-fef5-4082-8b80-8e8253efd8d8︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"done":true}
︠85ea39fe-7fc3-49fa-9df3-8a988ea5d085s︠
11 & 10 #bitenkénti és
11 | 10 #bitenkénti vagy
︡1cd2ceb3-1caa-4121-9b98-78ca968b697b︡{"stdout":"10\n"}︡{"stdout":"11\n"}︡{"done":true}
︠ba6036a6-cb26-45a6-acef-904b7c2a53b4︠
#függvények
def fun1(x):
    print(x)
fun1(5)
︡d402aa4e-c309-4d77-a1cc-37b0a7b712fc︡{"stdout":"5\n"}︡{"done":true}
︠3924f95c-de4a-4ed6-98b0-54a061c160dd︠
#alapértelmezett paraméter, mindig csak a jobbról balra hagyhatjuk el a paramétereket
def fun2(x=1):
    print(x)
fun2()
︡d1ef0d23-3b74-4157-8e3e-8e7e240dc5e7︡{"stdout":"1\n"}︡{"done":true}
︠9ff9644a-4079-4c64-b92a-74d554200832s︠
def fun3(x=1,y=0):
    print(x+y+x)
fun3()
︡7a50355f-d7b6-4db3-93c7-689a4906c73f︡{"stdout":"2\n"}︡{"done":true}
︠990a3966-82bd-4ee3-a4ea-d60d140e80bbs︠
#a paraméterekre függvényhíváskor nevükkel is hivatkozhatunk
fun3(y=5,x=2)
︡cfcc265f-a0a0-406c-9325-cad31bcc02a0︡{"stdout":"9\n"}︡{"done":true}
︠f71cf3e1-41f6-490d-9280-88400d4d735bs︠

#SageMath
#matematikai Python könyvtár, mely a matematika számos területét támogatja
#számos függvényt biztosít
factorial(100)
is_prime(100)
numerator(2/3) #számláló
denominator(2/3) #nevező
pi
cos(pi)
prod(list1) #produktum
next_prime(2^100) #paraméterként megadott szám utáni első prímet adja vissza
nth_prime(20) #paraméterként megadott számadik prímet
︡72ba6341-2c4f-4cda-a08a-9fca33bb92e1︡{"stdout":"93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000\n"}︡{"stdout":"False\n"}︡{"stdout":"2\n"}︡{"stdout":"3\n"}︡{"stdout":"pi\n"}︡{"stdout":"-1\n"}︡{"stdout":"13168189440000\n"}︡{"stdout":"1267650600228229401496703205653\n"}︡{"stdout":"71\n"}︡{"done":true}
︠59f87a1b-d60d-408c-9d01-d7515da9c38cs︠
#a könyvtár számos kényelmi funkciót biztosít
2^8 #megszokott hatványozás operátor
_ #legutolsó művelet eredménye
︡210a475c-9c16-45cf-bbeb-5107f3645dc1︡{"stdout":"256\n"}︡{"stdout":"256\n"}︡{"done":true}
︠7e48377f-4b9f-4bd4-ada6-49d2b6ecae33s︠
#a különböző SageMath-os függvények információit a ? operátorral kérhetjük le
n?
︡9913ab55-a028-43c6-bc81-adb418b16fe1︡{"code":{"filename":null,"lineno":-1,"mode":"text/x-rst","source":"File: /ext/sage/9.6/local/var/lib/sage/venv-python3.10.3/lib/python3.10/site-packages/sage/misc/functional.py\nSignature : n(x, prec=None, digits=None, algorithm=None)\nDocstring :\nReturn a numerical approximation of \"self\" with \"prec\" bits (or\ndecimal \"digits\") of precision.\n\nNo guarantee is made about the accuracy of the result.\n\nNote:\n\n  Lower case \"n()\" is an alias for \"numerical_approx()\" and may be\n  used as a method.\n\nINPUT:\n\n* \"prec\" -- precision in bits\n\n* \"digits\" -- precision in decimal digits (only used if \"prec\" is\n  not given)\n\n* \"algorithm\" -- which algorithm to use to compute this\n  approximation (the accepted algorithms depend on the object)\n\nIf neither \"prec\" nor \"digits\" is given, the default precision is\n53 bits (roughly 16 digits).\n\nEXAMPLES:\n\n   sage: numerical_approx(pi, 10)\n   3.1\n   sage: numerical_approx(pi, digits=10)\n   3.141592654\n   sage: numerical_approx(pi^2 + e, digits=20)\n   12.587886229548403854\n   sage: n(pi^2 + e)\n   12.5878862295484\n   sage: N(pi^2 + e)\n   12.5878862295484\n   sage: n(pi^2 + e, digits=50)\n   12.587886229548403854194778471228813633070946500941\n   sage: a = CC(-5).n(prec=40)\n   sage: b = ComplexField(40)(-5)\n   sage: a == b\n   True\n   sage: parent(a) is parent(b)\n   True\n   sage: numerical_approx(9)\n   9.00000000000000\n\nYou can also usually use method notation:\n\n   sage: (pi^2 + e).n()\n   12.5878862295484\n   sage: (pi^2 + e).numerical_approx()\n   12.5878862295484\n\nVectors and matrices may also have their entries approximated:\n\n   sage: v = vector(RDF, [1,2,3])\n   sage: v.n()\n   (1.00000000000000, 2.00000000000000, 3.00000000000000)\n\n   sage: v = vector(CDF, [1,2,3])\n   sage: v.n()\n   (1.00000000000000, 2.00000000000000, 3.00000000000000)\n   sage: _.parent()\n   Vector space of dimension 3 over Complex Field with 53 bits of precision\n   sage: v.n(prec=20)\n   (1.0000, 2.0000, 3.0000)\n\n   sage: u = vector(QQ, [1/2, 1/3, 1/4])\n   sage: n(u, prec=15)\n   (0.5000, 0.3333, 0.2500)\n   sage: n(u, digits=5)\n   (0.50000, 0.33333, 0.25000)\n\n   sage: v = vector(QQ, [1/2, 0, 0, 1/3, 0, 0, 0, 1/4], sparse=True)\n   sage: u = v.numerical_approx(digits=4)\n   sage: u.is_sparse()\n   True\n   sage: u\n   (0.5000, 0.0000, 0.0000, 0.3333, 0.0000, 0.0000, 0.0000, 0.2500)\n\n   sage: A = matrix(QQ, 2, 3, range(6))\n   sage: A.n()\n   [0.000000000000000  1.00000000000000  2.00000000000000]\n   [ 3.00000000000000  4.00000000000000  5.00000000000000]\n\n   sage: B = matrix(Integers(12), 3, 8, srange(24))\n   sage: N(B, digits=2)\n   [0.00  1.0  2.0  3.0  4.0  5.0  6.0  7.0]\n   [ 8.0  9.0  10.  11. 0.00  1.0  2.0  3.0]\n   [ 4.0  5.0  6.0  7.0  8.0  9.0  10.  11.]\n\nInternally, numerical approximations of real numbers are stored in\nbase-2. Therefore, numbers which look the same in their decimal\nexpansion might be different:\n\n   sage: x=N(pi, digits=3); x\n   3.14\n   sage: y=N(3.14, digits=3); y\n   3.14\n   sage: x==y\n   False\n   sage: x.str(base=2)\n   '11.001001000100'\n   sage: y.str(base=2)\n   '11.001000111101'\n\nIncreasing the precision of a floating point number is not allowed:\n\n   sage: CC(-5).n(prec=100)\n   Traceback (most recent call last):\n   ...\n   TypeError: cannot approximate to a precision of 100 bits, use at most 53 bits\n   sage: n(1.3r, digits=20)\n   Traceback (most recent call last):\n   ...\n   TypeError: cannot approximate to a precision of 70 bits, use at most 53 bits\n   sage: RealField(24).pi().n()\n   Traceback (most recent call last):\n   ...\n   TypeError: cannot approximate to a precision of 53 bits, use at most 24 bits\n\nAs an exceptional case, \"digits=1\" usually leads to 2 digits (one\nsignificant) in the decimal output (see\nhttps://trac.sagemath.org/11647):\n\n   sage: N(pi, digits=1)\n   3.2\n   sage: N(pi, digits=2)\n   3.1\n   sage: N(100*pi, digits=1)\n   320.\n   sage: N(100*pi, digits=2)\n   310.\n\nIn the following example, \"pi\" and \"3\" are both approximated to two\nbits of precision and then subtracted, which kills two bits of\nprecision:\n\n   sage: N(pi, prec=2)\n   3.0\n   sage: N(3, prec=2)\n   3.0\n   sage: N(pi - 3, prec=2)\n   0.00"}}︡{"done":true}
︠c036fd5f-3216-4316-aac6-4489cb1cb3b0︠
#az n() függvény egy alias a numerical_approx() függvényre, mely egy lebegőpontos közleítését adja meg egy adott kifejezésnek
n(1/(99^2),200) #alapjáraton bitekben méri a pontosságot
︡dfa23eee-f10b-4f0a-b4d4-48e178ae95bd︡{"stdout":"0.00010203040506070809101112131415161718192021222324252627282930\n"}︡{"done":true}
︠a73cb7e2-c446-49ef-8abc-6d8d70aadc13s︠
numerical_approx(pi,digits=200)
︡3dd4a1c4-cece-4a61-90dd-437e668f179f︡{"stdout":"3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303820"}︡{"stdout":"\n"}︡{"done":true}
︠435a09b4-bc35-4746-b8f8-df23367e7906︠

# a var() függvénnyel levédhetünk neveket, melyekkel szimbolikus változókat jelölünk
var('x')
︡faf7042f-64e8-4e42-b6bb-9f1ec9eceb78︡{"stdout":"x\n"}︡{"done":true}
︠e4c3d1e5-564f-4eb6-a3b2-f9fb7baf7de3s︠
derivative(2*x+3)
︡91a65d86-8ef2-4266-9af9-8645359941e0︡{"stdout":"2\n"}︡{"done":true}
︠320ac76c-3b5d-4c34-bd43-8524353a56a7s︠
# diff aliasa
diff(5*x^3+2*x^2)
︡a4ac1318-3cc2-4291-8a67-62caf54a9cb5︡{"stdout":"15*x^2 + 4*x\n"}︡{"done":true}
︠451cfbf2-8aed-4a3f-928e-22d4b25de613s︠
#lehetőség van a magasabb szintű derivált kiszámolására is
diff(5*x^3+2*x^2,x,x)
diff(5*x^3+2*x^2,x,2)
︡0c12c647-0dbe-4c81-80d6-4d01e9f08064︡{"stdout":"30*x + 4\n"}︡{"stdout":"30*x + 4\n"}︡{"done":true}
︠15dfd17e-8aa0-44e9-81df-a1796b468573︠

# a könyvtár támogatja az relációk láncolását is
1 < 5 < 10
1 == 2/2 == 9/9
︡b6e396f0-fcab-42f8-9a5e-5a247c5a7f5a︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"done":true}









