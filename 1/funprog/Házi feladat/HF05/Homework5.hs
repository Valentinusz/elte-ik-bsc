putIntoList :: a -> [a]
putIntoList x = x:[]

interval :: Int -> Int -> [Int]
interval n m = [n..m]

headTail :: [a] -> (a, [a])
headTail n = (head n, tail n)

doubleHead :: [a] -> [b] -> (a,b)
doubleHead n m = (head n, head m)

--hasZero :: [Int] -> Bool
--hasZero [] = False
--hasZero (0:_) = True
--hasZero n = hasZero (tail n)

hasZero :: [Int] -> Bool
hasZero [] = False
hasZero (0:_) = True
hasZero (x:xs) = hasZero xs

--hasEmpty :: [[a]] -> Bool
--hasEmpty [] = False
--hasEmpty ([]:_) = True
--hasEmpty n = hasEmpty (tail n)

hasEmpty :: [[a]] -> Bool
hasEmpty [] = False
hasEmpty ([]:_) = True
hasEmpty (x:xs) = hasEmpty xs

doubleAll :: [Int] -> [Int]
doubleAll [] = []
--doubleAll [x] = [2*x]
doubleAll (x:xs) = (2*x):(doubleAll xs) 

isLonger :: [a] -> [b] -> Bool
isLonger [] [] = False
isLonger [] (_:_) = False
isLonger (_:_) [] = True
isLonger (x:xs) (y:ys) = isLonger xs ys

evens :: [Int] -> [Int]
evens x = [n | n <- x, even n]

sumFirst :: Int -> [Int] -> Int
sumFirst 0 _ = 0
sumFirst n (x:xs) = x + sumFirst (n-1) xs
