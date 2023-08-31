--Feladatsor/óravázlat a 4. gyakorlathoz


--------------------
--Listagenerátorok-- (list comprehension)
--------------------
--http://lambda.inf.elte.hu/Comprehensions.xml

listComp = [n^2 | n <- [1..10], even n]

--Állítsuk elő a 2 hatványait növekvő sorrendben 1-től 2^10-ig!
pow2 = [2^n | n <- [1..10]]

--Soroljuk fel az első 10 négyzetszám kétszeresét!
dsqnum = [2*n*n | n <- [0..9]]

--Soroljuk fel 1 és 100 között azokat a számokat, amelyek oszthatóak 3-mal, de nem oszthatóak 5-tel!
d3n5 = [n | n <- [1..100], n `mod` 3 == 0, n `mod` 5 /= 0]

--Definiálj egy függvényt, amely összeadja a páros számokat egy paraméterül megkapott n számig!
--pl.: sumEven 1 == 0; sumEven 4 == 6 stb.
sumEven :: Integral a => a -> a 
sumEven 1 = 0
sumEven n = sum([n | n <- [2..n], even n])

--Definiálj egy függvényt, amely egy tetszőleges paraméterül kapott számokat tartalmazó listából előállít egy olyan listát, amelynek minden eleme 5-tel nagyobb, mint az eredeti lista megfelelő eleme!
--pl.: plus5 [1,2,3,4,5] == [6,7,8,9,10]
plus5 :: Num a => [a] -> [a]
plus5 n = [x+5 | x <- n]

--Definiálj egy függvényt, amely egy tetszőleges paraméterül kapott egészeket tartalmazó listából kiválogatja a páros számokat!
--pl.: filterEven [1, 4, 7, 42, 99] == [4,42]
filterEven :: Integral a => [a] -> [a]
filterEven n = [x | x <- n, even x] 

--Definiálj egy függvényt, amely egy tetszőleges paraméterül kapott Stringből eltávolítja az 'e' betűket!
--pl.: removeLetterE "feketeribizli" == "fktribizli"
removeLetterE :: String -> String
removeLetterE s = [l | l <- s, l /= 'e']


--Definiálj egy függvényt, amely megszámlálja, hogy egy tetszőleges paraméterül kapott String hány 'e' betűt tartalmaz!

countE :: String -> Int
--countE s = sum([1 | c <- s, c == 'e'])
--countE s = length([1 | c <- s, c == 'e'])
countE s = sum([1 | 'e' <- s])
--mintaillesztéses megoldás

--Soroljuk fel a 60 osztóit!
div60 :: Integral a => [a]
div60 = [n | n <- [1..60], 60 `mod` n == 0]

--Írjunk egy függvényt, amely eldönti egy tetszőleges paraméterként kapott egész számról, hogy prím-e!
--Tipp: a 'null' függvény segítségével eldönthető egy listáról, hogy üres-e!
isPrime :: Integral a => a -> Bool
isPrime 1 = False
isPrime p = null([n | n <- [2..(p `div` 2)], p `mod` n == 0])


--Állítsunk elő 1-50 között a számokat és 3-mal való osztási maradékukat egy rendezett párokat tartalmazó listában.
trem :: Integral a => [(a,a)]
trem = [(n,n `mod` 3) | n <- [1..50]]

--Állítsuk elő azt a listát, amely sorrendben tartalmazza az összes (óra, perc) párt!
ora :: Integral a => [(a,a)]
ora = [(n,m) | n <- [0..23], m <- [0..59]]

--Adott egy (x,y) koordinátákat tartalmazó lista.
--Válogassuk ki azokat a koordinátákat, amelyek az x-tengelyen vannak!
onX :: Integral a => [(a,a)] -> [(a,a)]
onX n = [(x,0) | (x,0) <- n]

--Számoljuk meg azokat a párokat egy listában, amelyekben a pár elemei közötti távolság legalább 2!
countPairCon :: Integral a => [(a,a)] -> a
countPairCon n = sum([1 | (x,y) <- n, abs(x-y) >= 2])

-------------------
--Minták listákra--
-------------------
--http://lambda.inf.elte.hu/Patterns.xml#mint%C3%A1k-list%C3%A1kra

--Definiáld a null' függvényt mintaillesztés segítségével, amely eldönti egy listáról, hogy üres-e!
--null' :: [a] -> Bool

--Definiáld a head' függvényt mintaillesztés segítségével, amely visszaadja egy lista első elemét!

--Definiáljuk az “1 elemű-e a lista” függvényt mintaillesztéssel!
--isSingleton :: [a] -> Bool


