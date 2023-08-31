Param(
[Parameter(mandatory)]
[int]$sebzes
)

$adat = Import-Csv 'Harc.csv' -Encoding UTF8 -Delimiter ',' -Header 'Name','Attack','Rolls'

$sum = 0
foreach($sor in $adat) {
    $dobas = $sor.rolls.split("d")
    $roll = Get-Random -Minimum (1 * [int]$dobas[0]) -Maximum (([int]$dobas[0] * [int]$dobas[1]) + 1)
    $sum += $roll
    $till = $sebzes - $sum
    $sor.Name + " rolled " + $dobas[0] + " times with a d" + $dobas[1] + " dealing a total of " + $roll + " damage using: " + $sor.Attack + "(" + $till + " DMG left.)" 
}

if($sum -ge $sebzes) {
    return $true
} else {
    return $false
}

#Jobban felosztott változat:
<#
$adat = Import-Csv 'Harc.csv' -Encoding UTF8 -Delimiter ',' -Header 'Name','Attack','Rolls'

$sum = 0
foreach($sor in $adat) {
    $dobas = $sor.rolls.split("d")
    $rollsum = 0
    (1..$dobas[0]) | % {
        $roll = Get-Random -Minimum 1 -Maximum ([int]$dobas[1]+1)
        $sum += $roll
        $till = $sebzes - $sum
        $sor.Name + " dealt " + $roll + " damage with " + $sor.Attack + "(DMG left: " + $till + ")"
    }
}

if($sum -ge $sebzes) {
    return $true
} else {
    return $false
}
#>