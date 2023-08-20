import Data.Maybe
import Data.List


{-
  ###
 #   #
 #   #
 #   #
  ###
-}

type Matrix = [[Int]]

--"kicsomagolja" a mátrixot egy listává az 1-től kezdődés könnyebb ellenőrzéséhez (2.) és a cost egyszerűbb számításához (3.)
unpackMatrix :: Matrix -> [Int]
unpackMatrix [[]] = []
unpackMatrix [] = []
unpackMatrix (x:xs) = x ++ unpackMatrix xs

{-
    #
   ##
  # #
    #
	#
	#
-}
isSameSize :: Matrix -> Matrix -> Bool
isSameSize [] [] = True
isSameSize [_] [] = False
isSameSize [] [_] = False
isSameSize (x:xs) (y:ys)
  | rowSizeMatch x y = isSameSize xs ys
  | otherwise = False
  where
  rowSizeMatch :: [Int] -> [Int] -> Bool
  rowSizeMatch [] [] = True
  rowSizeMatch (x:xs) (y:ys) = rowSizeMatch xs ys
  rowSizeMatch _ _ = False

{-
  ###
 #   #
    #
   #
  #
 #####
-}
magicSquare :: Matrix -> Maybe Int
magicSquare [[x]] = Just x
magicSquare x
  | (isFrom1toN x) && (rowSumMatch x) && (rowSumMatch $ transpose x) && (sum $ getMainDiagonal 0 x) == (sum $ getSideDiagonal (getSquareMatrixSize x) x) = Just $ sum $ getMainDiagonal 0 x
  | otherwise = Nothing
  where
  
  --Megadja egy négyzetes mátrix méretét
  getSquareMatrixSize :: Matrix -> Int
  getSquareMatrixSize [] = 0
  getSquareMatrixSize (x:xs) = 1 + getSquareMatrixSize xs
  
  --Ellenőrzi hogy a mátrix 1-tól a méretéig tartalmazza-e a számjegyeket
  isFrom1toN :: Matrix -> Bool
  isFrom1toN [] = False
  isFrom1toN x
    | (sort $ unpackMatrix x) == [1..(getSquareMatrixSize x)^2] = True
    | otherwise                                                 = False

  --Ellenőrzi, hogy megegyezik-e a sorok összege
  rowSumMatch :: Matrix -> Bool
  rowSumMatch [] = True
  rowSumMatch [x,y] = (sum x ==  sum y)
  rowSumMatch (x:y:xs) = (sum x == sum y) && rowSumMatch (y:xs)

  --Kiszámítja a főátlóbeli elemek összegét
  getMainDiagonal :: Int -> Matrix -> [Int]
  getMainDiagonal _ [] = []
  getMainDiagonal n (x:xs) = (head $ drop n x) : getMainDiagonal (n+1) xs

  --Kiszámítja a mellékátlóbeli elemek összegét
  getSideDiagonal :: Int -> Matrix -> [Int]
  getSideDiagonal _ [] = []
  getSideDiagonal n (x:xs) = (head $ drop (n-1) x) : getSideDiagonal (n-1) xs

{-
 ####
     #
     #
 ####
     #
     #
 ####
-}
minimalCost :: Matrix -> Maybe Int
minimalCost [[a,b,c],[d,e,f],[g,h,i]] = safeMinimum $ getCost (unpackMatrix [[2,9,4],[7,5,3],[6,1,8]]) $ map (unpackMatrix) $ getTransformations [[a,b,c],[d,e,f],[g,h,i]] where

  --cost függvény
  cost :: [Int] -> [Int] -> Int
  cost [] _ = 0
  cost _ [] = 0
  cost (x:xs) (y:ys) = abs (x-y) + (cost xs ys)
 
  --"mátrixtranszformációk" 
  transformMatrix :: Int -> Int -> Matrix -> Matrix

  --csak forgatás
  transformMatrix 0 0   x = x
  transformMatrix 0 90  x = map (reverse) $ transpose x
  transformMatrix 0 180 x = map (reverse) $ transpose $ transformMatrix 0 90 x
  transformMatrix 0 270 x = transpose $ map (reverse) x

  --forgatás és transzponálás
  transformMatrix 1 0   x = transpose x
  transformMatrix 1 90  x = transpose $ transformMatrix 0 90  x
  transformMatrix 1 180 x = transpose $ transformMatrix 0 180 x
  transformMatrix 1 270 x = transpose $ transformMatrix 0 270 x
  
  --transzformációk előállítása
  getTransformations :: Matrix -> [Matrix]
  getTransformations x = [transformMatrix t r x | t <- [0..1], r <- [0,90..270]]  
  
  --költségek kiszámítása
  getCost :: [Int] -> [[Int]] -> [Int]
  getCost x ys = [cost x y | y <- ys]
  
  --minimum függvény biztonságos változata
  safeMinimum :: [Int] -> Maybe Int
  safeMinimum [] = Nothing
  safeMinimum xs = Just $ minimum xs
  
minimalCost _ = Nothing