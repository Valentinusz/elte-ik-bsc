$adat = Import-Csv kave.txt -Delimiter ';' -Header 'Nev', 'Fogyasztas'

Param(
[Parameter(mandatory=$false)]
[int]$ar = 40,
[Parameter(mandatory,ValueFromPipeline)]
$input
)

$input | % {
$random = Get-Random -Minimum 1 -Maximum 10
$string = $_ + " " + $random
$string | Out-File random.txt -Append
}

(Get-Content $fajl -Encoding UTF8) | % {$_.replace(" ",";")} | Set-Content $fajl -Encoding UTF8

... | Write-Host -ForegroundColor Green

"#" * $_

Param(
[Parameter(mandatory=$true)]
[string]$fajl,

[Parameter()]
[switch]$keves,

[Parameter()]
[switch]$atlag
)

$szoveg = Read-Host -Prompt "Adj meg egy szöveget"