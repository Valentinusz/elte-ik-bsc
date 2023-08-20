import Data.Char
import Data.List
import Data.Maybe

whichIsEmpty :: [a] -> [b] -> Maybe Int
whichIsEmpty [] _ = Just 1
whichIsEmpty _ [] = Just 2
whichIsEmpty _ _ = Nothing

match :: Eq a => (a,a) -> (a, a) -> Bool
match (a,b) (c,d)
  | a == c || a == d || b == c || b == d = True
  | otherwise = False
  
indicesOfEmpties :: Eq a => [[a]] -> [Int]
indicesOfEmpties ls = [i | (x,i) <- zip ls [1..], null x]  

applyOnWords :: (String -> String) -> String -> String
applyOnWords f ls = unwords $ map f (words ls)

restUntil :: (a -> [a] -> Bool) -> [a] -> [a]
restUntil _ [] = []
restUntil p (x:xs)
  | p x xs = (x:xs)
  | otherwise = restUntil p xs
  
replaceAll :: Eq a => a {-mit-} -> [a] -> a {-mire-} -> [a]
replaceAll _ [] _ = []
replaceAll f (x:xs) t
  | x == f = t : replaceAll f xs t
  | otherwise = x : replaceAll f xs t
  
  
isUpperWord :: String -> Bool
isUpperWord "" = False
isUpperWord (x:xs)
  | isUpper x = True
  | otherwise = isUpperWord xs
  
listWordsWithUpper :: String -> [String]  
listWordsWithUpper ls = filter (isUpperWord) (words ls)

applyWhile :: (a -> Bool) -> (a -> a) -> [a] -> [a]
applyWhile _ _ [] = []
applyWhile p f (x:xs)
  | p x = f x : applyWhile p f xs
  | otherwise = (x:xs)

replaceWithDefIfNot :: (a -> Bool) -> [a] -> [a] -> [a]
replaceWithDefIfNot _ [] [] = []
replaceWithDefIfNot _ [] _ = []
replaceWithDefIfNot _ ls [] = ls
replaceWithDefIfNot p (x:xs) (y:ys)
  | p x = x : replaceWithDefIfNot p xs (y:ys)
  | otherwise = y : replaceWithDefIfNot p xs ys
  
  
applyAlternately :: (a -> b) -> (a -> b) -> [a] -> [b]
applyAlternately f1 f2 ls = [(if (odd i) then f1 x else f2 x) | (x,i) <- zip ls [1..]]

zipMaybe :: [a] -> [b] -> [(Maybe a, Maybe b)]
zipMaybe [] [] = []
zipMaybe [] (y:ys) = (Nothing, Just y) : zipMaybe [] ys
zipMaybe (x:xs) [] = (Just x, Nothing) : zipMaybe xs []
zipMaybe (x:xs) (y:ys) = (Just x, Just y) : zipMaybe xs ys

data Temperature = Night Double | Daytime Double deriving (Show, Eq)

isDaytime :: Temperature -> Bool
isDaytime (Daytime _) = True
isDaytime _ = False

getTemp :: Temperature -> Double
getTemp (Daytime x) = x

daytimeAvg :: [Temperature] -> Double
daytimeAvg ls = (sum $ map (getTemp) $ filter (isDaytime) ls) / (fromIntegral $ length $ filter (isDaytime) ls)

lackOfLetters :: String -> [Char] -> Maybe [Char]
lackOfLetters "" "" = Nothing
lackOfLetters "" _ = Nothing
lackOfLetters (x:xs) ls
  | toLower x `elem` ls = lackOfLetters xs ls
  | otherwise = Just $ nub $ toLower x : findMissing (map (toLower) xs) ls where
    findMissing :: String -> String -> String
    findMissing "" _ = ""
    findMissing (x:xs) ls 
      | (x `elem` ls) = findMissing xs ls
      | otherwise = x : findMissing xs ls

fixedPointIn :: Eq a => (a -> a) -> a -> Int -> Maybe Int
fixedPointIn f value point