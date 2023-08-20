import Data.Char
import Data.List

--listák
listDiff :: Eq a => [a] -> [a] -> [a]
listDiff [] _ = []
listDiff x [] = x
listDiff xs ys = [x | x <- xs, isNotInOther x ys]

isNotInOther :: Eq a => a -> [a] -> Bool
isNotInOther _ [] = True
isNotInOther a (x:xs)
  | a==x = False
  | otherwise = isNotInOther a xs

--szólánc
validGame :: String -> Bool
validGame ls = validGame2 (words ls)

validGame2 :: [String] -> Bool
validGame2 (x:y:xs)
  | last x == head y = validGame2 (y:xs)
  | otherwise = False
validGame2 _ = True


--egyelemű
isSingleton :: [a] -> Bool
isSingleton [a] = True
isSingleton _ = False

countSingletons :: [[a]] -> Int
countSingletons ls = sum([ 1 | x <- ls, isSingleton x])

--paritás
sameParity :: [Int] -> Bool
sameParity [] = True
sameParity ls = isAllEven ls || isAllOdd ls

isAllOdd :: [Int] -> Bool
isAllOdd [] = True
isAllOdd (x:xs) = odd x && isAllOdd xs

isAllEven :: [Int] -> Bool
isAllEven [] = True
isAllEven (x:xs) = even x && isAllEven xs

--leghosszabb
longestChain :: String -> Int
longestChain "" = 0
longestChain ls = maximum(map (length) (group ls))

--normal
normalizeText :: String -> String
normalizeText s = filter (`elem` ['A'..'Z']) (map (toUpper) s)