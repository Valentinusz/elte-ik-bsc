import Data.Char

--Feladatsor/óravázlat a 7. gyakorlathoz

-----------------------------
-- Magasabbrendű függvények--
-----------------------------
--olyan függvény melynek paramétere egy másik függvény

--Számológép

calc :: Int -> Int -> (Int -> Int -> Int) -> Int
calc a b f = a `f` b 


-- Definiálj egy függvényt, amely kap egy tetszőleges paramétert és arra alkalmazható függvények egy listáját, és sorban aélkalmazza a függvényeket a paraméterre!
chainApply :: a -> [a->a] -> a
chainApply a [] = a
chainApply a (x:xs) = chainApply (x a) xs


-- map, filter

-- Definiáljuk újra rekurzióval az elemenkénti feldolgozást! (map')
map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = f x : map' f xs

-- Definiáljuk újra listagenerátorral az elemenkénti feldolgozást! (map'')
map'' :: (a -> b) -> [a] -> [b]
map'' f (x:xs) = [f x | x <- xs]

-- Definiáljuk újra rekurzióval a szűrést! (filter')
filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' p (x:xs)
  | p x = x : filter' p xs
  | otherwise = filter' p xs

-- Definiáljuk újra listagenerátorral a szűrést! (filter'')
filter'' :: (a -> Bool) -> [a] -> [a]
filter'' p (x:xs) = [x | x <- xs, p x]

-- Számoljuk meg egy adott tulajdonságú elem előfordulásait egy listában!
filtercount :: (a -> Bool) -> [a] -> Int
filtercount _ [] = 0
filtercount p (x:xs)
  | p x = 1 + filtercount p xs
  | otherwise = filtercount p xs
filtercount' :: (a -> Bool) -> [a] -> Int
filtercount' p ls = sum([1 | x <- ls,p x])

-- Definiáljuk újra a takeWhile függvényt!
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' _ [] = []
takeWhile' p (x:xs)
  | p x = x : takeWhile' p xs
  | otherwise = []

------------------------
-- Rekurziós feladatok--
------------------------

--Listában, rendezett párokban tároljuk egy pizza összetevőinek nevét és árát. Adjuk meg a kész pizza árát, ha tudjuk, hogy az alapanyagokon túl 500 Ft munkadíjat számolunk fel.

--pizza [("teszta", 200), ("paradicsomszosz", 150), ("pepperoni", 200), ("cheddar", 300)] == 1350
--pizza [("teszta", 200), ("sajtszosz", 130), ("bacon", 200), ("chili", 300), ("tukortojas", 200)] == 1530
--pizza' [("vekony teszta", 180), ("besamel", 120), ("bazsalikom", 200), ("kaviar", 2000), ("gorgonzola", 800)] == 3800

pizza :: [(String, Int)] -> Int
pizza [] = 500
pizza ((a,b):xs) = b + pizza xs

pizza' :: [(String, Int)] -> Int
pizza' ls = 500 + sum([b | (a,b) <- ls]) 

--Egy titkosított kódból nyerjük ki az első, olyan két karakter hosszú betűsort, amit szám követ.
--Az egyes karakterek azonosításához használjuk a [Data.Char](http://lambda.inf.elte.hu/haskell/doc/libraries/base-4.11.1.0/Data-Char.html#g:1) függvényeit. (isLetter, isDigit)
--(import Data.Char)

--cipher "PYdg7iT4vdO0n4AgmGfUpRzogAf" == "dg"
--cipher "4vkYyAO174midQTt0" == "AO"
--cipher "BwxwEwqCKHuMTAaPn" == ""
--cipher "dM7" == "dM"
--cipher "Kmz" == ""
--cipher "Zk"  == ""
--cipher "T4"  == ""
--cipher "" == ""

cipher :: String -> String
cipher (x:y:z:xs)
  | isLetter x && isLetter y && isDigit z = x:y:[]
  | otherwise = cipher (y:z:xs)
cipher _ = []
