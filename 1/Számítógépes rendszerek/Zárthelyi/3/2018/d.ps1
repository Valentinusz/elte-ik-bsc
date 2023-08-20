Param(
[Parameter(mandatory)]
[int]$lomb,
[Parameter()]
[int]$torzs = 1
)


$lombcount= 1
$aircount= 2*($lomb-1)

0..($lomb-1) | % {
' ' * ($aircount/2) + "#" * $lombcount + ' ' * ($aircount/2) | Write-Host -ForegroundColor Green
$aircount -= 2
$lombcount += 2
}

$aircount= 2*($lomb-1)
0..($torzs-1) | % {
' ' * ($aircount/2) + "#" + ' ' * ($aircount/2) | Write-Host -ForegroundColor Gray
}
