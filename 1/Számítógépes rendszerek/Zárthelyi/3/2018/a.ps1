$input | % {
$random = Get-Random -Minimum 1 -Maximum 10
$string = $_ + " " + $random
$string | Out-File random.txt -Append
}