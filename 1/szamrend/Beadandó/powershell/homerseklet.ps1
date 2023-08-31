Param(
[Parameter(Position = 0, mandatory = $true)]
[string]$szel,
[Parameter(Position = 1, mandatory = $true)]
[string]$hossz
)

$tomb = @()
Get-Content .\adatok.txt | % {
    $sor=$_.split(" ")
    if ($szel -eq $sor[0] -and $hossz -eq $sor[1]) {
        $tomb += $sor[2]
    }
}
($tomb | Get-Unique).count