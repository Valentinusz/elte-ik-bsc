data Vector3 = V Int Int Int deriving (Show, Eq) 

componentSum :: Vector3 -> Int
componentSum (V x y z) = x+y+z

crossProduct :: Vector3 -> Vector3 -> Vector3
crossProduct (V a1 a2 a3) (V b1 b2 b3) = (V (a2*b3-a3*b2) (a3*b1-a1*b3) (a1*b2-a2*b1))

vectorListSum :: [Vector3] -> Vector3
vectorListSum ls = (V (sum [x | (V x y z) <- ls]) (sum [y | (V x y z) <- ls]) (sum [z | (V x y z) <- ls]))

{-
getX :: Vector3 -> Int
getX (V x y z) = x

getY :: Vector3 -> Int
getY (V x y z) = y

getZ :: Vector3 -> Int
getZ (V x y z) = z
-}

{-
vectorListSum ls = (V (addX ls) (addY ls) (addZ ls)) where
  addX [] = 0
  addX (x:xs) = (getX x) + (addX xs)
  addY [] = 0
  addY (x:xs) = (getY x) + (addY xs)
  addZ [] = 0
  addZ (x:xs) = (getZ x) + (addZ xs)
-}


data Storage = HDD String Int Int | SSD String Int deriving (Show, Eq)

capacity :: Storage -> Int
capacity (HDD _ _ x) = x
capacity (SSD _ x) = x

isHDD :: Storage -> Bool
isHDD (HDD _ _ _) = True
isHDD _ = False 

hugeHDDs :: [Storage] -> [Storage]
hugeHDDs ls = [x | x <- ls, capacity(x)>getSSDMax ls && isHDD x] where
  getSSDMax :: [Storage] -> Int
  getSSDMax ls = maximum $ map (capacity) $ filter (not . isHDD) ls