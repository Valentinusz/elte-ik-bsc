Param(
[Parameter(mandatory=$true)]
[int]$darab
)

$also = 0
$felso = 10

1..$darab | % {
$random = Get-Random -Minimum $also -Maximum $felso
$random
$also = [int]$random
$felso = [int]$also+10
}