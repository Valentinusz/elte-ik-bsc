-- Lambda kifejezések

-- (\ x ->x * 2 + 1)

-- modul szinten nem jelenhet meg csak kifjezés szinten
-- gyakorlatilag inline függvény

example ls = map (\ x -> x*2+1) ls

-- "névtelen függvény" máshol nem is hivatkozhatunk rá


-- :t típuskikövetkező jól kitalálja a típusát

-- (\ (x,y) -> x * 2 + y)

-- mire jó?
-- kifejezésszelettel már nem lehet kifejezni az adott dolgot, de még nem érdemes segédfüggvényt használni

-- maguk az operátorszeletek átírhatók lambdává

-- (\x -> x + 1)

reverseF :: [a] -> [a]
--reverseF ls = foldL (flip (:)) [] ls
reverseF ls = foldl (\ acc x -> x : acc) [] ls

flip' :: (a -> b -> c) -> (b -> a -> c)
--flip' f y x = f x y
flip' f = (\a b -> f b a)

-- mi történik: flip' helyére behelyettesítődik (\a b -> elem b a)

(+:+) :: [a] -> [a] -> [a]

{-
k = [4,5]
l = [1,2,3]
l ++ k
== 1:2:3:k
-}

--(+:+) ls ks = foldr (:) ks ls
(+:+) = flip $ foldr (:)

length' :: [a] -> Int
length' ls = foldr (\_ acc -> 1 + acc) 0 ls

mapF :: (a -> b) -> [a] -> [b]
mapF f ls = foldr(\e xs -> f e : xs) [] ls

mapF' :: (a -> b) -> [a] -> [b]
mapF' f ls = foldr step [] ls where
  step e xs = f e : xs

--esetszétválasztáskor nem használhatunk lambdát
filterF :: (a -> Bool) -> [a] -> [a]
filterF p ls = foldr step [] ls where
  step e xs
    | p e = e : xs
    | otherwise = xs

and' :: [Bool] -> Bool
and' = foldr (&&) True
and'' ls = foldl (&&) True ls 

--foldr sokkal hatékonyabb
--ha tudjuk hogy a függvényunk futás közbe valami miatt meg tud állni célszerűbb foldr-t használni
--ha mindenképp végig kell menni a listán nem számít melyiket használjuk

--univerzális kvantor függvény
all' :: (a -> Bool) -> [a] -> Bool
all' p ls = foldr (\e xs -> p e && xs) True ls

-- Függvénykompozíció

-- (f(g x)) -> (f o g) x 
-- (.) függvénykompozíció magasabbrendű operátor
-- két függvényből alkot egy harmadikat

(.) :: (b -> c) -> (a -> b) -> (a -> c) 
(.) f g x = f (g x)

(.) f g = h where
  h x = f (g x)

(.) f g = (\x -> f (g x))
