--Feladatsor/óravázlat a 8. gyakorlathoz

--Ismétlés

--Definiálj egy függvényt, amely egy számpárokat tartalmazó listából előállít egy olyan listát, amely a számpárok összegeit tartalmazza!
sumPairs ::  Num  a => [(a,a)] -> [a]
sumPairs [] = []
sumPairs ((a,b):xs) = (a+b) : sumPairs xs 

-- Számoljuk meg egy adott tulajdonságú elem előfordulásait egy listában!
count :: (a -> Bool) -> [a] -> Int
count p [] = 0
count p (x:xs)
  | p x= 1 + count p xs
  | otherwise= count p xs

count' :: (a -> Bool) -> [a] -> Int
count' p ls = sum[1 | x <- ls,p x]

-- Definiáljuk újra a takeWhile' függvényt!
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' p [] = []
takeWhile' p (x:xs)
  |p x= x:takeWhile' p xs
  |otherwise=[]

    
-- Definiáljuk egy `dropSpaces` függvényt, amely eldobja a szóközöket egy szöveg elejéről! 
dropSpaces' :: String -> String
dropSpaces' "" = ""
dropSpaces' (c:ss)
  | c==' ' = dropSpaces' ss
  | otherwise = (c:ss)
  
dropSpaces'' :: String -> String
dropSpaces'' s = dropWhile (==' ') s


-- Adott egy nyelvazonosító (például hu-HU vagy en-US ), mely két részből, nyelvből és régióból tevődik össze. Bonts fel egy nyelvazonosítót nyelvre és régióra! Feltesszük, hogy jól formázott a bemenet. Mindkét rész két-két karakterből áll.


-- Adott egy szöveg, válasszuk szét a kötőjelek mentén szövegek egy listájává!
-- separate "Hello-world-hello" == ["Hello","world","hello"]
--separate :: String -> [String]



-- Definiáljuk újra a zipWith' függvényt!
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x:xs) (y:ys) = f x y : zipWith f xs ys

-- Definiáljuk újra a zip függvényt a zipWith segítségével!

zip' :: [a] -> [b] -> [(a,b)]
zip' _ [] = []
zip' [] _ = []
zip' xs ys = zipWith (,) xs ys

-- Definiáljuk újra az iterate függvényt!
iterate' :: (a -> a) -> a -> [a]
iterate' f x = x : iterate' f (f x)

-- Definiáljunk függvényt, amely előállíja a következő végtelen sorozatot: [1,-1,1,-1,1,-1 .. ]
--(Tipp: használjuk az előbb definiált iterate függvényt!)
alternating :: [Int]
alternating = iterate (*(-1)) 1

           
-- Definiáljunk függvényt, amely előállíja a következő végtelen sorozatot: [1,-2,3,-4,5,-6,7,-8,9,-10 .. ]
alternatingMultiple :: [Int]
alternatingMultiple = zipWith (*) [1..] alternating


-- Függvényalkalmazás operátor ($)
-- http://lambda.inf.elte.hu/Higherorder.xml#feladat-a-doll%C3%A1r-oper%C3%A1tor
-- $ alkalmazza a függvényt
-- infix
-- zárójelezés helyett használható

calcExample = abs ((+6) (negate (sqrt 100)))
calcExample' = abs $ (+6) $ negate $ sqrt 100

-- Függvénykompozíció operátor (.)
-- függvények kombinálása mint matek az f o g csak kör helyett . 

calcExample'' = (abs . (+6) . negate . sqrt) 100

-- Definiálj egy függvényt, amely egy egész számokból álló lista minden elemét negatív számmá transzformál!
toNegative :: [Int] -> [Int]
toNegative [] = []
toNegative (x:xs) = (negate . abs) x : toNegative xs 


trim :: String -> String
trim s = reverse $ dropWhile (==' ') $ reverse $ dropWhile (==' ') s

trim' :: String -> String
trim' s = (reverse . dropWhile (==' ') . reverse . dropWhile (==' ')) s