import Data.List

--Feladatsor/óravázlat a 12. gyakorlathoz

-- foldr_ (+) 0 [1,2,3] == 1 + (2 + (3 + (0)))
foldr_ :: (a -> b -> b) -> b -> [a] -> b
foldr_ f acc []     = acc 
foldr_ f acc (x:xs) = x `f` (foldr_ f acc xs)

-- foldl_ (+) 0 [1,2,3] == (((0) + 1) + 2) + 3 
foldl_ :: (b -> a -> b) -> b -> [a] -> b
foldl_ f acc []     = acc 
foldl_ f acc (x:xs) = foldl_ f (f acc x) xs

--Definiáld a sum-ot foldr/foldl segítségével!
sum' :: Num a => [a] -> a
sum' ls = foldr (+) 0 ls

--Definiáld az or-t a foldr/foldl segítségével!
or' :: [Bool] -> Bool
or' ls = foldr (||) False ls

--Definiáld az and-et a foldr/foldl segítségével!
and' :: [Bool] -> Bool
and' ls = foldr (&&) True ls

--Definiáld a concat-ot a foldr/foldl segítségével!
concat' :: [[a]] -> [a]
concat' xs = foldr (++) [] xs

--Definiáld a length-et a foldr/foldl segítségével!
length' :: [a] -> Int
length' = foldr (\_ acc -> acc + 1) 0

--Definiáld a head és last függvényeket a foldr és foldl segítségével! Üres lista esetén error legyen az eredmény. Melyiket mivel lehet?
last' :: [a] -> acc
last' xs = foldr (\_ acc -> acc) (error "empty list") xs



--Definiáld a maximum-ot a foldr/foldl segítségével!
maximum' :: Ord a => [a] -> a
maximum' (x:xs) = foldr (max) x xs

--Definiáld a map-et a foldr segítségével! (És foldl segítségével?)
map' :: (a -> b) -> [a] -> [b]
map' f xs = foldr (\x acc -> f x : acc) [] xs

--Definiálj egy beszúrásos rendezést a foldr segítségével! (Használd az insert-et egy elem rendezett listába való beszúrúsához)
--isort :: Ord a => [a] -> [a] 


-- Definiáld a (++) operátort a foldr segítségével!
--(+++) :: [a] -> [a] -> [a]

-- Definiáld a count függvényt foldr segítségével, amely megszámolja, hogy egy adott tulajdonsággal rendelkező elemből hány található egylistában!
--count :: (a -> Bool) -> [a] -> Int

reverse' :: [a] -> [a]
reverse' xs = rev [] xs where
    rev :: [a] -> [a] -> [a]
    rev done [] = done
    rev done (x:xs) = rev (x:done) xs


-- Definiáld a reverse-t a foldr vagy foldl segítségével!
--reverseWithFoldl :: [a] -> [a]

--Definiáld az elem-et foldr segítségével!
--elem' :: Eq a => a -> [a] -> Bool

-- Definiáld az all függvényt foldr segítségével!
--all'' :: Eq a => [a] -> Bool