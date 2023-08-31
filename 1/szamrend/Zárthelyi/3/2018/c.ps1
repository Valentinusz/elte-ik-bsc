Param (
[Parameter()]
[int]$n = 0,
[Parameter(mandatory=$true,ValueFromPipeline)]
$input
)

$line = 1

$input | % {
    $reverse = $false
    $sor = $_.split(";")
    if ($n -ne 0 -and $line % $n -eq 0) {
        $reverse = $true
    }

    if($sor[1] -le $sor[2]) {
        if ($reverse) {
            $sor[0] + ": " + $sor[2]
        } else {
            $sor[0] + ": " + $sor[1]
        }
    } else {
        if ($reverse) {
            $sor[0] + ": " + $sor[1]
        } else {
            $sor[0] + ": " + $sor[2]
        }
    }
    $line++
}