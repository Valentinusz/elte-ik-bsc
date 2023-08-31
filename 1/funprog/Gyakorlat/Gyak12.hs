import Data.List
--Feladatsor/óravázlat a 11. gyakorlathoz

-- Elmélet ism.
--Tekintsük az alábbi definíciót! Mi a B 42 kifejezés típusa?

data D = A Int | B Int [Char]

--String -> D
--D
--B Int
--D -> String

-------------------------
-- Névtelen függvények --
-------------------------

f x = map (\x -> even x) [1..10]
-- két x más lambdabeli változónevek elfedik

-- Írj a következő függvényekkel ekvivalens névtelen függvényt!

f1 :: Int -> Bool
f1 a = even a && a `mod` 5 == 0
-- (\a -> (even a) && (a `mod` 5 == 0))

f2 :: Num a => a -> a -> a
f2 a b = a^2 + b^2
-- (\a b -> a^2 + b^2)

f3 :: (b, a) -> (a, b, a)
f3 (a,b) = (b,a,b)
-- (\(a,b) -> (b,a,b))

f4 :: [b] -> ([b], b)
f4 (x:xs) = (xs, x)
-- (\(x:xs) -> (xs,x))

-- Válogasd ki a hosszú szavakat egy listából (egy szó hosszúnak számít, ha legalább 8 betű)

-- filterLongWords ["alma", "almafa","cseresznye","cseresznyefa"] == ["cseresznye","cseresznyefa"]
filterLongWords :: [String] -> [String]
filterLongWords ls = filter (\y -> length y > 7) ls  

-- Válogasd ki azokat a párokat egy listából, ahol a pár második komponense nagyobb, mint az első!
filterIncPairs :: Ord a => [(a, a)] -> [(a, a)]
filterIncPairs ls = filter (\(x,y) -> x < y) ls

--Definiáld a monogram függvényt, mely egy név monogramját adja meg! Használd a wordsöt és magasabb rendű függvényt.
--monogram "Jim Carrey" == "J. C."
--monogram "Ilosvai Selymes Péter" == "I. S. P."
monogram :: String -> String
monogram ls = unwords $ map (\x -> (take 1 x) ++ ".") (words ls)
monogram' ls = unwords $ map (\(x:_) -> [x] ++ ".") (words ls)
monogram'' ls = unwords $ map (\(x:_) -> x : '.' : []) (words ls)
monogram''' ls = unwords $ map (\(x:_) -> [x, '.']) (words ls)


--Definiáld a `uniq` függvényt, mely elhagyja az ismétléseket! (Tipp: használható a Data.List-ből a sort és a group)

-- uniq "Mississippi" == "Mips"
-- uniq "papagaj" == "agjp"
-- uniq "" == ""

uniq :: Ord a => [a] -> [a]
uniq ls =  map (head) (group $ sort ls)


-- Definiáld a repeated függvényt, mely csak az ismétlődő elemeket tartja meg egy listában!
--repeated "Mississippi" == "ips"
--repeated [1,2,3,4,2,5,6,7,1] == [1,2]
--repeated "" == ""

repeated :: Ord a => [a] -> [a]
repeated ls = map (head) $ filter (\x -> length x > 1) (group $ sort ls)



-- Futamhossz kódolás/dekódolás
-- Az `encode` egy szövegben egymás után ismétlődő elemeket keresve tömörítse a paraméterként kapott listát!
-- encode "abbccccb" == [(1, 'a'), (2, 'b'), (4, 'c'), (1, 'b')]
-- encode (sort "abrakadabra") == [(5, 'a'), (2, 'b'), (1, 'd'), (1, 'k'), (2, 'r')]

--encode :: String -> [(Int, Char)]

-- A decode függvény visszaalakítja az előző encode függvény által kódolt listát szöveggé.

--decode :: [(Int, Char)] -> String


--Állítsunk elő súlyozott összeget! A súlyok az értékek elé vannak párosítva egy listában.

-- weightedSum [(1,1), (1,2)] == 3
-- weightedSum [(1,1), (2,2)] == 5

--weightedSum :: Num a => [(a,a)] -> a

--Definiáld a `replacePred` függvényt, amely kap egy predikátumot, a cserélendő elemet, és egy listát. Minden elemre amelyikre teljesül a predikátum, ott a függvény kicséréli a lista elemét a cserélendő elemmel.

--replacePred even 55 [1,2,3,4,5] == [1,55,3,55,5]
--replacePred isUpper 'x' "DdDd" == "xdxd"
--replacePred (\x -> even x && mod x 3 == 0) 42 [10,20,30] == [10,20,42]

replacePred :: (a -> Bool) -> a -> [a] -> [a]
replacePred p e xs = map (\x -> if p x then e else x) xs

--Fibonacci párok: Állítsuk elő a (0,1), (1,1), (1,2), (2,3), (3,5), (5,8), … (végtelen) sorozatot! A sorozat előállítási szabálya: (a, b) -> (b, a+b).
fibPairs :: [(Integer, Integer)]
fibPairs = foldr (:) []


--Pascal háromszög: Állítsuk elő a Pascal-háromszöget: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1],…].

--pascalTriangle :: [[Integer]]

-- Közelítsük az a négyzetgyökét az \x->(x+a/x)/2 függvénnyel.


