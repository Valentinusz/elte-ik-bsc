Param(
[Parameter(Position=0,mandatory=$true)]
[string]$szoveg
)

$szam = $szoveg.Length + 4

"*" * $szam 
"* " + $szoveg + " *"
"*" * $szam