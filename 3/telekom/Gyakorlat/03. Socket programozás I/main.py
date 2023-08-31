import socket


# kommunikációs csatornának két elterjedt fajtája van:
#   kapcsolat-orientált (pl. telefonbeszélgetés) csomagok megfelelő sorrendben érkeznek meg
#     TCP (Transmission Control Protocol), stream típusú socket
#   kapcsolat nélküli modell (pl. postai levelezés) csomagagsorrend nem biztos, el is veszhet, teljesítményben jobb
#     UDP (User Datagram Protocol), datagram típusú socket


s = socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM, proto=0)
# socket.AF_INET     - IPv4
# socket.AF_INET6    - IPv6
# socket.SOCK_STREAM - kapcsolat-orientált (stream socket)
# socket.DGRAM       - kapcsolat nélküli (datagram socket)
# proto              - protokoll (opcionális, default = 0)


# Bind-olás
s.bind(('localhost', 10000))  # hosztnév vagy IP és port
# 127.0.0.1 = localhost


# Socket utasítása a kapcsolódási kérelmek figyelésére
s.listen(2)
# backlog: csatlakozásra varó kliensek maximális száma


other_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
other_socket.bind(('localhost', 5000))


# Csatlakozási kérelem küldése egy távoli sockethez
other_socket.connect(('localhost', 10000))


# Csatlakozási kérelem elfogadása
connection, client_address = s.accept()
# Visszatérés: (conn, address) tuple
#  conn: új socket objektum melyen keresztül kommunikációt végezhetünk
#  address: a kapcsolat másik oldalán lévő socket-hez bindolt cím


# Adatküldés
# other_socket.send("Hello World!".encode(), 0)  # .encode(): str -> bytes
# data: bytes
# flags: [] default: no flag = 0
# Visszatérés: átküldött bájtok száma, alkalmazásnak ellenőrzni kell megkapott-e mindent


# Adatküldés amíg van adat
other_socket.sendall("Hello World2!".encode())
# Visszatérés: None, ha sikeres
# ha hibával áll le, nincs mód az átküldött bájtok számának meghatározására


# Adatfogadás
data = connection.recv(1024, 0)
# bufsize - az egy időben fogadható adat nagysága
# flags
# Visszatérés: bytes


print(data.decode())


connection.close()
s.close()
other_socket.close()
