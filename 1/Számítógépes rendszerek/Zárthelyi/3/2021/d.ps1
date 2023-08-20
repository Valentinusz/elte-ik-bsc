$wnums = 79,42,4,29,48

$counter = 0
foreach ($number in $input) {
    foreach ($wnumber in $wnums) {
        if ($wnumber -eq $number) {
            $counter++
        }
    }
}
$counter