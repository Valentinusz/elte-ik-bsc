Param(
[Parameter(mandatory=$true)]
[string]$fajl,

[Parameter()]
[switch]$keves,

[Parameter()]
[switch]$atlag
)

$adat = Import-Csv $fajl -Delimiter ";" -Header 'Nev','Jegyek'

$kevesj=@()
$eleg=@()

$adat | % {
    $nev = $_.nev
    $jegyek = $_.jegyek.split(" ")
    if ($jegyek.length -lt 5) {
        $kevesj += $nev
    } else {
        $eleg += $_
    }
}


if($keves) {
    $kevesj | % {
    $_
    }
}

if ($atlag) {
    $eleg | % {
        $avg=0
        foreach($jegy in $_.jegyek.split(" ")) {
            [int]$avg += [int]$jegy
        }
        $avg /= $_.jegyek.split(" ").length
        $_.nev + ";" + $avg + ";" + $_.jegyek
    }
}