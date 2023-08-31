$adat = Import-Csv kave.txt -Delimiter ';' -Header 'Nev', 'Fogyasztas'
$adat2=@()
ForEach($item in $adat) {
    $sum = 0
    $fogy = $item.Fogyasztas.split(" ")
    $fogy | % {
    $sum += [int]$_
    }
    $adat2 += $item.nev + ': ' + $sum
}
$adat2