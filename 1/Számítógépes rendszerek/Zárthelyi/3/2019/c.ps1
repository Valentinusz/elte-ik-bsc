$input | % {
$_.replace(" ","").toLower().replace("á","a").replace("é","e").replace("í","i").replace("ó","o").replace("ö","o").replace("ő","o").replace("ú","u").replace("ü","u").replace("ű","u") + "@szervezet.elte.hu"
}