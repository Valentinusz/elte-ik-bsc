Param(
[Parameter(Position=0,mandatory=$true)]
[string]$fajl
)


(Get-Content $fajl -Encoding UTF8) | % {$_.replace(" ",";")} | Set-Content $fajl -Encoding UTF8