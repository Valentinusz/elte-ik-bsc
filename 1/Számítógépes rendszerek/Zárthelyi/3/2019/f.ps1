$szoveg = Read-Host -Prompt "Adj meg egy szöveget"

$db = $szoveg.Length + 4

"*" * $db
"* " + $szoveg + " *"
"*" * $db