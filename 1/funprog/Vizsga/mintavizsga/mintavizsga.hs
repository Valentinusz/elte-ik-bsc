import Data.Char

-- 1
splitQuadruple :: (a,b,c,d) -> ((a,b),(c,d))
splitQuadruple (a,b,c,d) = ((a,b),(c,d))


-- 2
dist1 :: Num a => a -> a -> a
dist1 x y = abs $ x - y 


-- 3
kroeneckerDelta :: Eq a => a -> a -> Int
kroeneckerDelta a b
  | a == b    = 1
  | otherwise = 0


-- 4
freq :: Eq a => a -> [a] -> Int
freq _ [] = 0
freq e (x:xs)
  | e == x    = 1 + freq e xs
  | otherwise = freq e xs


-- 5  
hasUpperCase :: String -> Bool
hasUpperCase "" = False
hasUpperCase (x:xs)
  | isUpper x = True
  | otherwise = hasUpperCase xs


-- 6 
identifier :: String -> Bool
identifier "" = False
identifier (x:xs)
  | isLetter x = and $ map (isValid) xs
  | otherwise  = False where
  isValid :: Char -> Bool
  isValid c
    | isLetter c || isDigit c || c == '_' = True
    | otherwise                           = False


-- 7
replace :: Int -> a -> [a] -> [a]
replace _ e [] = [e]
replace n e ls
  | n < 0           = e : ls
  | n > (length ls) = ls ++ [e]
  | otherwise       = [(if n == i then e else x) | (x,i) <- zip ls [0..]]


-- 8 
paripos :: [Int] -> Bool
paripos [] = True
paripos ls = and $ [if (even x && even i) || (odd x && odd i) then True else False | (x,i) <- zip ls [0..]]


-- 9
safeDiv :: Int -> Int -> Maybe Int
safeDiv _ 0 = Nothing
safeDiv a b = Just $ a `div` b


-- 10
parseCSV :: String -> [String]
parseCSV "" = [""]
parseCSV ls = reverse $ map (reverse) $ parseCSV2 ls [] [] where
  parseCSV2 :: String -> String -> [String] -> [String]
  parseCSV2 (x:xs) acc1 acc2
    | x == ';' && null xs = ("":acc1:acc2)
    | x == ';'            = parseCSV2 xs [] (acc1:acc2)
    |             null xs = ((x:acc1):acc2)
    | otherwise           = parseCSV2 xs (x:acc1) (acc2)

-- 11
c :: (a -> b -> c) -> (b -> a -> c)
c f a b = f b a


-- 12
selectUnless :: (t -> Bool) -> (t -> Bool) -> [t] -> [t]
selectUnless _ _ [] = []
selectUnless p1 p2 (x:xs)
  | p1 x && (not . p2) x = x : selectUnless p1 p2 xs
  | otherwise            =     selectUnless p1 p2 xs


-- 13
w :: (a -> a -> a) -> a -> a
w f a = f a a


-- 14
ntimes :: (a -> a -> a) -> a -> Int -> a
ntimes _ e 1 = e
ntimes f e n = e `f` ntimes f e (n-1)


-- 15
data Binary = On | Off deriving (Show, Eq)

switch :: Binary -> Binary
switch On  = Off
switch Off = On


-- 16
bxor :: [Binary] -> [Binary] -> [Binary]
bxor [] [] = []
bxor (x:xs) (y:ys)
  | (x == On || x == Off) && x == y = On  : bxor xs ys
  | otherwise                       = Off : bxor xs ys