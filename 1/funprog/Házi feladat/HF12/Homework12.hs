import Data.Char

type Line = String
type File = [Line]

testFile0 :: File
testFile0 = ["asd  qwe", "-- Foo", "", "\thello world "]

--1
countWordsInLine :: Line -> Int
countWordsInLine l = length $ words l

--2
countWords :: File -> Int
countWords f = sum $ map (countWordsInLine) f

--3
countChars :: File -> Int
countChars f = length $ concat f

--4
capitalizeWordsInLine :: Line -> Line
capitalizeWordsInLine l = unwords $ map (capitalize) (words l) where
  capitalize :: String -> String
  capitalize "" = ""
  capitalize (x:xs) = (toUpper x) : xs

--5
isComment :: Line -> Bool
isComment "" = False
isComment [_] = False
isComment (x:y:_)
  | (x:y:[]) == "--" = True
  | otherwise = False

--6
dropComments :: File -> File
dropComments f = filter (not . isComment) f

--7
numberLines :: File -> File
numberLines f = zipWith (++) (numbers (length f)) f where
  numbers :: Int -> [String]
  numbers n = [(show x ++ ": ") | x <- [1..n]]

--8
dropTrailingWhitespaces :: Line -> Line
dropTrailingWhitespaces l = reverse $ dropWhile (isSpace) $ reverse l

--9
replaceTab :: Int -> Char -> [Char]
replaceTab n c
  | c == '\t' = [' ' | _ <- [1..n]]
  | otherwise = c:[]

--10
replaceTabs :: Int -> File -> File
replaceTabs n f = map (replaceTabLine n) f where
  replaceTabLine :: Int -> Line -> Line
  replaceTabLine n l = concat $ map (replaceTab n) l