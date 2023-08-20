$tomb = @()
Get-Content .\adatok.txt | % {
    $sor=$_.split(" ")
    $tomb += $sor[0] + " " + $sor[1]
}
(($tomb | Sort-Object | Group-Object | Select-Object Name)[0]).name