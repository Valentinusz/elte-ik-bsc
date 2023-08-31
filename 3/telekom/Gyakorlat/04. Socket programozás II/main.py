import socket

soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Egy Socket időtúllépése (időtúllépés: automatikus leallás egy bizonyos idő után ha nem történik meg egy adott esemény)
soc.settimeout(1.0)     # időtúllépés mértéke 1 másodperc
soc.settimeout(None)    # időtúllépés kikapcsolása
soc.setblocking(True)   # == socket.settimeout(None)
soc.setblocking(False)  # == socket.settimeout(0.0)


# A Socketek további finomhangolására használhatjuk a
# socket.setsockopt(level, optname, value) metódust
# level:
#     socket.IPPROTO_IP: IP-szintű beállítás
#     socket.SOL_SOCKET: socket API szintű beállítás
# optname:
#     socket.REUSEADDR: kapcsolat bontása után azonnal hasznosítsa újra a portot
# value: None, int, vagy bytes
soc.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

import select
# select - várakozás I/O befejezésére
# Windowson csak a socketek esetén működik, Linuxon pl. használható a pipeline-al is

# Több socket kezelése egyidőben
#   probléma: acccept és recv függvények blokkolnak
#   lehetséges megoldás: szálkezelés (drága)
#   tényleges megoldás:
#     select.select(rlist, wlist, xlist[, timeout])
#       rlist - readlist: olvasásra várakozó socketek
#       wlist - writelist: írásra várakozó socketek
#       xlist - kivételre várakozó socketek
#       timeout - várakozási idő, ha nincs megedva blokkol
#       visszatérés: 3 lista:
#          rlist azon elemeit tartalmozó lista, melyek készek az olvasásra
#          wlist azon elemeit tartalmazó lista, melyek készek az írásra
#          xlist azon elemeit tartalmozó lista, melyeken kivétel jön
#       az olvasható socketek három lehetséges esetet reprezentálnak:

import queue
# szálbiztos FIFO tároló
q = queue.Queue(5)
q.put(5)
q.get()  # blokkol
q.get_nowait()  # nem blokkol

import msvcrt
# Windows platformon ahsználható
msvcrt.kbhit()  # True, ha billentyűleütés beolvasására vár
msvcrt.getche()  # beolvas egy billyentyűleütést (blokkol)

# FELADATOK
# 1. TCP alkalmazás, több kliens üzenetet küld a szervernek, szerver "OK"-kal válaszol
# 2. Módosítsuk az előző órai számológép szervert, hogy több klienssel is tudjon kommunikálni,
#    a kliens tudjon több üzenetet is küldeni, melyek közt 2 másodpercet várakozik
# 3. TCP chat alkalmazás, szerveren keresztül elküldésre kerül az üzenet minden kliens számára
