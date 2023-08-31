 --Mintaillesztés

not' True = False
not' False = True

--minta lehet:
--	változó: x, y
-- 	joker: _ (bármilyen típus illeszkedik rá, de az érétkét nem lehet felhasználni)
-- típus specifikus minták: True

or' :: Bool -> Bool -> Bool
--or' True True = True
--or' True False = True
--or' False True = True
--or' False False = False

--or' _ True = True
--or' True _ = True
--or' _ _ = False

or' False False = False
or' _ _ = True


and' :: Bool -> Bool -> Bool
--and' True True = True
--and' True False = False
--and' False True = False
--and' False False = False

and' True True = True
and' _ _ = False

whatNumber :: Integer -> String
whatNumber 1 = "Egy"
whatNumber 2 = "Kettő"
whatNumber 3 = "Három"
whatNumber x = "Nem 1 és 3 közötti."

--függvény ami sortörést szóközre cserél
replaceNewLine :: Char -> Char
replaceNewLine '\n' = ' '
replaceNewLine x = x

--tuple: rendezett n-es
--fst első rendezett párból
--snd második a rendezett párból

first :: (p1,p2) -> p1
first pair = fst pair

first' :: (p1,p2) -> p1
first' (a, b) = a

swap :: (p1, p2) -> (p2, p1)
swap (a, b) = (b, a)

triplicate :: a -> (a, a, a)
triplicate n = (n, n, n)

isEven :: Integral a => a -> (a, Bool)
isEven n = (n, even(n))

addPair :: Num a => (a, a) -> (a, a) -> (a, a)
addPair (n, m) (x, y) = (n+x, m+y)

divAndMod :: Integral a => a -> a -> (a, a)
divAndMod n m = (n `mod` m, n `div`m)

distFromOrigo :: Floating a => (a, a) -> a
distFromOrigo (n, m) = sqrt(n*n+m*m)

distance :: Floating a => (a, a) -> (a, a) -> a
distance (n, m) (x, y) =  sqrt((n-x)*(n-x)+(m-y)*(m-y))

--listák

pelda1 :: Num a => [a]
pelda1 = [1, 2, 3, 4 ,5]
-- a listák homogének, csak egyféle típust tartalmazhatnak

pelda2 :: [Bool]
pelda2 = [True, False, True, True]

pelda3 :: [Char]
pelda3 = ['a', 'b', 'c']
-- az ugyan az mint az "abc" String

pelda4 :: [(Integer, Bool)]
pelda4 = [(2, True), (1, False)]

exerciseList1 :: [(Integer, Integer)]
exerciseList1 = [(4,3), (0,2)]

exerciseList2 :: Integral a => [(a, Char)]
exerciseList2 = [(4, 'x'), (7, 'f')]

exerciseList3 :: (Fractional a, Integral b) => [(a, a, b)]
exerciseList3 = [(2.5, 4.2, 1),(2.325, 3.421, 5)]

exerciseList4 :: Num a => [[a]]
exerciseList4 = [[2,4],[3,5,6],[0]]

-- ++ operátor két lisát összefűz

pelda5 = [1,2] ++ [3,4]

-- .. kifejezések

pelda6 = [1..10]
-- intervallum vége is benne van

pelda7 = [9,8..0]

pelda8 = ['a'..'z']

exerciseList5 = [10,9..(-10)]

exerciseList6 = [10,9.. -10]
