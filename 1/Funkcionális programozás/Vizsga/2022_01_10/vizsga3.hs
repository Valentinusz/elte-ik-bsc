import Data.Char
import Data.List
import Data.Maybe

--
squareSum :: Num a => (a, a) -> (a, a, a)
squareSum (a,b) = (a,b,a*a+b*b)


--
names :: [String] -> [String] -> [String]
names [] _ = []
names _ [] = []
names (x:xs) (y:ys) = (x ++ " " ++ y) : names xs ys


--
triangleArea :: (Double, Double, Double) -> Maybe Double
triangleArea (a,b,c)
  | (a > 0) && (b > 0) && (c > 0) && (a + b > c) && (a + c > b) && (b + c > a) && (a*a+b*b == c*c) = Just $ (a*b)/2
  | otherwise = Nothing


--
doubleIdxs:: Eq a  => [(a,a)] -> Maybe [Int]
doubleIdxs [] = Nothing
doubleIdxs ls
  | doubleIdxs2 ls 1 /= [] = Just $ doubleIdxs2 ls 1
  | otherwise = Nothing where
    doubleIdxs2 :: Eq a  => [(a,a)] -> Int -> [Int]
    doubleIdxs2 [] _ = []
    doubleIdxs2 ((a,b):xs) n
      | a == b = n : doubleIdxs2 xs (n+1)
      | otherwise = doubleIdxs2 xs (n+1)


--
snakeToCamel :: String -> String
snakeToCamel "" = ""
snakeToCamel [a] = [a]
snakeToCamel (x:y:xs)
  | x == '_' = toUpper y : snakeToCamel xs
  | otherwise = x : snakeToCamel (y:xs)


--
removeExtremes :: Ord a => [a] -> [a]
removeExtremes [] = []
removeExtremes ls = filter (/= maximum ls) $ filter (/= minimum ls) ls


--
replaceLastOcc :: Eq a => a {-mit-} -> a {-mire-} -> [a] -> Maybe [a]
replaceLastOcc from to ls
  | elemIndex from ls /= Nothing = Just $ reverse $ replaceFirstOcc from to (reverse ls)
  | otherwise = Nothing where
  
  replaceFirstOcc :: Eq a => a {-mit-} -> a {-mire-} -> [a] -> [a]
  replaceFirstOcc from to (x:xs)
    | x == from = to : xs
    | otherwise = x : replaceFirstOcc from to xs


--  
anagram :: String -> String -> Bool
anagram str1 str2 = sort str1 == sort str2


--
checkLength :: Int -> [a] -> Bool
checkLength _ [] = False
checkLength req (_:xs)
  | (req == 1) && (null xs)  = True
  | req < 1                  = False
  | otherwise                = checkLength (req-1) xs

sumWithLenghtN :: Num a => Int -> [[a]] -> a
sumWithLenghtN n ls = (sum $ map (sum) $ filter (checkLength n) ls)


--
isSteady :: Eq b => (a -> b) -> [a] -> Bool
isSteady _ [] = True
isSteady f ls = all (== (head $ map f ls)) (tail $ map f ls)


--
data Parcell = P String Double Int deriving (Show, Eq)


--
deliveryFee :: Parcell -> Maybe Double
deliveryFee (P "Asgard" w _)   = Just $ w*100
deliveryFee (P "Midgard" w _)  = Just $ w*10
deliveryFee (P "Vanaheim" w _) = Just $ w*80
deliveryFee (P "Alfheim" w _)  = Just $ w*50
deliveryFee _                  = Nothing


--
fee :: Parcell -> Maybe Int
fee (P "Asgard" _ p)   = Just p
fee (P "Midgard" _ p)  = Just p
fee (P "Vanaheim" _ p) = Just p
fee (P "Alfheim" _ p)  = Just p
fee _                  = Nothing

valid :: Parcell -> Bool
valid (P "Asgard" _ _)   = True
valid (P "Midgard" _ _)  = True
valid (P "Vanaheim" _ _) = True
valid (P "Alfheim" _ _)  = True
valid _                  = False

delivery :: [Parcell] -> Double
delivery ls = (sum $ map (fromJust . deliveryFee) $ filter (valid) ls) + (fromIntegral $ sum $ map (fromJust . fee) $ filter (valid) ls)


--
inconsistencyInGrowing :: [[a]] -> Maybe (Int, Int)
inconsistencyInGrowing [] = Nothing
inconsistencyInGrowing [[]] = Nothing
inconsistencyInGrowing [[a]] = Nothing
inconsistencyInGrowing ls = checkIncensistency ls 0


--
compareLength :: [a] -> [a] -> Bool
compareLength [] [] = False
compareLength _ [] = False
compareLength [] _ = True
compareLength (_:xs) (_:ys) = compareLength xs ys


--
checkIncensistency :: [[a]] -> Int -> Maybe (Int, Int)
checkIncensistency [] _ = Nothing
checkIncensistency [[]] _ = Nothing
checkIncensistency [a] _ = Nothing
checkIncensistency (x:y:xs) i
  | compareLength x y = checkIncensistency (y:xs) (i+1)
  | otherwise         = Just $ (i,i+1)
  

--
shrinkText :: String -> String
shrinkText "" = ""
shrinkText (x:xs)
   | x == '(' && ')' `elem` xs = shrinkText $ removeWhileEnd xs
   | otherwise = x : shrinkText xs

removeWhileEnd :: String -> String
removeWhileEnd "" = ""
removeWhileEnd (x:xs)
   | x == ')' = xs
   | otherwise = removeWhileEnd xs  