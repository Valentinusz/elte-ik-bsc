Get-Content ./adatok.txt | % {
    $sor=$_.split(" ")
    if ([int]$sor[4] -gt [int]$max) {
        $max=$sor[4]
        $maxsor=$sor
    }
}
$maxsor[0..3]