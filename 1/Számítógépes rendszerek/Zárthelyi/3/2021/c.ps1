Param(
[Parameter()]
[string]$path = $PSScriptRoot
)

if(Test-Path -Path $path) {
    Get-ChildItem -Path $path | % {
        Get-ChildItem -Path $path\$_
    }
} else {
    Get-ChildItem | % {
        Get-ChildItem $_
    }
}