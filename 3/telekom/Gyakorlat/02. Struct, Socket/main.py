import struct
# A struct modul lehetőséget biztosít arra, hogy a értékeket (a python bytes objektumain keresztül)
# C-s structokká konvertáljunk.
# https://docs.python.org/3/library/struct.html

# A konverziót a következő formátummegadások határozzák meg:
# https://docs.python.org/3/library/struct.html#struct-format-strings

# Egy bytes objektumot ad vissza, mely a megadott v1, v2, ... értékeket tartalmazza a megadott format formátumban.
# struct.pack(format, v1, v2, ...)
values = (1, b'ab', 2.7)

# * spread operátor
packed = struct.pack('l 2s f', *values)  # formátum jelentése: int, char[2], float
print(packed)

# Bytes-ként tárolt struct kicsomagolása a megadott formátum alapján
# struct.unpack(format, buffer)
unpacked = struct.unpack('l 2s f', packed)
print(unpacked)


# struct méretének lekérése
print(struct.calcsize('l 2s f'))  # int: 4 char[2]: 2 float: 4
# látszólag hibás eredmény (10 != 12) mert struct natív igazítást használ, a számokat olyan helyre teszi a memóriában,
# melynek kezdőpozíciója a típus méretével osztható

print()
# alternatív kezelési mód
myStruct = struct.Struct('l 2s f')
packed = myStruct.pack(*values)
print(packed)
unpacked = myStruct.unpack(packed)
print(unpacked)
print(myStruct.size)

# Fájlkezelés
# fájlok binárisan is megnyithatók "b" megadásával
# f.seek(offset, whence)-el állítható a fájlbejáró pointer pl. f.seek(0) fájl elejére ugrik
# bináris fájlok olvasásához a f.read(int bytes) metódust használjuk

with open("database.bin", "rb") as file:
    file.seek(2)  # fájl második bájtjára ugrik (bináris fájl esetén, szöveges fájl esetén a második karakterre)
    unpacker = struct.Struct('i3si')  # a fájl egyébként nem ebben a formátumban van szóval csoda hogy működik a program
    line = file.read(unpacker.size)
    data = unpacker.unpack(line)
    print(data)

print()
# Socket programozás
# socket: kommunikációs végpont
# egy program a saját socketjén keresztül ír és olvas egy másik program socketjére
# kommunkációs szükséges egymás azonosítójának ismerete (IP cím és port szám)
# IP cím helyett egyedi azonosító is használható pl. www.elte.hu (ezt a leképezést egy domain name server végzi)
# speciális 127.0.0.1 localhost

import socket

hostname1 = socket.gethostname()  # python interpretert futtató gép hostneve
print(hostname1)
hostip = socket.gethostbyname('www.example.org')  # hostname -> IP-cím
host1 = socket.gethostbyname_ex('www.example.org')  # részletes információ lekerzés a hostról cím alapján
# (hostname, aliaslist, ipaddrlist) rendezett hármast ad vissza
print(*host1)
host2 = socket.gethostbyaddr('157.181.161.79')  # részletes információ lekerzés a hostról cím alapján IP alapján
# rendezett hármast ad vissza
print(*host2)

print(socket.getservbyport(22))  # adott port alatt futó szolgálatás lekérése

# fontos kompatibilitási probléma, hogy a host és a hálózat esetén a bájtsorrend eltérhet
socket.htons(25)  # host to network short
socket.htonl(32)  # host to network long
socket.ntohs(25)  # network to host short
socket.ntohl(32)  # network to host long
