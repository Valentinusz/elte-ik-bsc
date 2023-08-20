

--Feladatsor/óravázlat a 11. gyakorlathoz

--------------------------
-- Maybe segédfüggvények--
--------------------------

isJust :: Maybe a -> Bool
isJust (Just _) = True
isJust _ = False

fromJust :: Maybe a -> a
fromJust Nothing = error "Can't get value from nothing." 
fromJust (Just x) = x

catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes ((Just x):xs)
  |isJust (Just x) = x : catMaybes xs
  |otherwise = catMaybes xs

mapMaybe :: (a -> Maybe b) -> [a] -> [b]
mapMaybe f ls = catMaybes $ map f ls
    
-- Reprezentáció    
    
type Username = String
type Password = String

data Privilege = Simple | Admin
    deriving (Eq, Show)
    
data Cookie = LoggedOut | LoggedIn Username Privilege
    deriving (Eq, Show)
    
data Entry = Entry Password Privilege [Username]
    deriving (Eq, Show)
    
type Database = [(Username, Entry)]

-- Példa adatok

richard, charlie, carol, david, kate :: (Username, Entry)
richard = ("Richard", Entry "password1" Admin ["Kate"])
charlie = ("Charlie", Entry "password2" Simple ["Carol"])
carol = ("Carol", Entry "password3" Simple ["David", "Charlie"])
david = ("David", Entry "password4" Simple ["Carol"])
kate = ("Kate", Entry "password5" Simple ["Richard"])
testDB :: Database
testDB = [ richard, charlie, carol, david, kate ]
testDBWithoutCarol :: Database
testDBWithoutCarol =
    [ ("Richard", Entry "password1" Admin ["Kate"])
    , ("Charlie", Entry "password2" Simple [])
    , ("David", Entry "password4" Simple [])
    , ("Kate", Entry "password5" Simple ["Richard"])
    ]
 
------------------------ 
-- Szelektorfüggvények--
------------------------

password :: Entry -> Password
password (Entry x _ _) = x  

privilege :: Entry -> Privilege
privilege (Entry _ x _) = x

friends :: Entry -> [Username]
friends (Entry _ _ xs) = xs

-------------------------
-- Adatbázis műveletek --
-------------------------

mkCookie :: Username -> Password -> Entry -> Cookie
mkCookie user pass entry
  | pass == (password entry) = (LoggedIn user (privilege entry))
  | otherwise = (LoggedOut)
    
login :: Username -> Password -> Database -> Cookie
login user pwd db = maybe LoggedOut (mkCookie user pwd) (lookup user db)


updateEntry :: Username -> (Username, Entry) -> Maybe (Username, Entry)
updateEntry user1 (user2, entry)
  | user1 == user2 = Nothing
  | otherwise = Just (user2, (Entry


--deleteUser :: Cookie -> Username -> Database -> Database
