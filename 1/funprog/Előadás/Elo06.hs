--megnézi hogy egy lista legalább n hosszú-e

atLeastNLength :: Int -> [a] -> Bool
atLeastNLength n []
  | n <= 0 = True
  | n > 0 = False
atLeastNLength n (_:xs)
  | n <= 0 = True
  | n > 0 = atLeastNLength (n-1) xs
  
--atLeastNLength 3 [1,2]
--	3 > 0 = atLeastNLength 3-1 xs (2. minta 2. őrfeltétel)
--		atLeastNLength 2 [2]
--			2 > 0 = atLeastNLength 2-1 xs (2. minta 2. őrfeltétel)
--				atLeastNLength 1 []
--					False (1. minta 2. őrfeltétel)
  
--atLeastNLength 3 [1,2,3]
--atLeastNLength 2 [2,3]
--atLeastNLength 1 [3]
--atLeastNLength 0 [] (1. minta 1. őrfeltétel)

atLeastNLengthImproved :: Int -> [a] -> Bool
atLeastNLengthImproved n [] = n <= 0
atLeastNLengthImproved n (_:xs)
  | n<=0 = True
  | otherwise = atLeastNLengthImproved (n-1) xs



--take függvény újraírása első n elemből csinál egy másik listát

take' :: Int -> [a] -> [a]
take' n _ | n <= 0 = []
take' _ [] = []
take' n (x:xs) = x : take' (n-1) xs

--take' 2 [1,2,3]
-- 1 : take' 1 [2,3]
--  2 : take' 0 [3]
--   [1,2]



--drop take ellentetje azokat íratja ki amiket nem takelünk
drop' :: Int -> [a] -> [a]
drop' n ls | n <= 0 = ls
drop' _ [] = []
drop' n (x:xs) = drop' (n-1) xs

--reverse megfordítja a listát

--első megoldás: primitív rekurzió
{-
reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]
-}

--második megoldás: végrekurzív
reverse' :: [a] -> [a]
reverse' ls = rev ls [] where
  rev [] acc = acc
  rev (x:xs) acc = rev xs (x : acc)