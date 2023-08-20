# Paraméterként várom a ;-vel tagolt fájl nevét

Param(
[Parameter(mandatory)]
[string]$fajl
)

$adat = Import-Csv $fajl -Delimiter ';' -Header 'Name','Value','Grade'

$sum=0
$adat | % {
    if ($_.Grade -gt 1) {
        $sum += [int]$_.Grade * [int]$_.Value 
    }
}
$sum/30
