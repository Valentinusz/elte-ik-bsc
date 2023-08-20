[int]$num = Read-Host -Prompt "Adj meg egy számot"

if($num -ge 0) {
    $five = [Math]::Truncate($num / 5)

    $one = $num % 5

    "-" * $five + "." * $one
} else {
    "A negatív számokat nem ismerik. Vagy én nem ismerem hogy ismerik."
}