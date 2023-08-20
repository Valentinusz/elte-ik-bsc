some :: [Bool] -> Bool
some [] = False
some (True:_) = True
some (x:xs) = some xs

sublist :: Int -> Int -> [b] -> [b]
sublist n m ls = take m (drop n ls)

password :: [Char] -> [Char]
password [] = []
password (_:xs) = '*' : (password xs)
--password s = ['*' | c <- s]

lookup' :: Eq a => a -> [(a,b)] -> b
lookup' n (x:xs)
  | fst x==n = snd x
  | otherwise = lookup' n xs  
  
toBin :: Integer -> [Int]
toBin 0 = []
toBin n
  | n `mod` 2==0 = 0 : (toBin (n `div` 2))
  | otherwise = 1 : (toBin (n `div` 2))
