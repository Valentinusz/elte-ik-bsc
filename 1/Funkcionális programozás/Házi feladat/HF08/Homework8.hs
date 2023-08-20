sortMerge :: Ord a => [a] -> [a] -> [a]
sortMerge [] ls = ls
sortMerge ls [] = ls
sortMerge (x:xs) (y:ys)
  | x>y = y : sortMerge (x:xs) ys
  | otherwise = x : sortMerge xs (y:ys)
  
removeDuplicateSpaces :: String -> String
removeDuplicateSpaces = unwords . words

differences :: Num a => [a] -> [a]
differences [] = []
differences (x:xs) = zipWith (-) xs (x:xs)