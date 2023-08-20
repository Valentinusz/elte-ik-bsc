Param(
[Parameter(mandatory=$false)]
[int]$ar = 40,
[Parameter(mandatory,ValueFromPipeline)]
$input
)

$adat=@()
$input | % {
    $sor = $_.split(":")
    $adat += [pscustomobject]@{Name=$sor[0];Value=[int]$sor[1]*$ar}
}
$adat | Sort-Object -Property "Value" -Descending