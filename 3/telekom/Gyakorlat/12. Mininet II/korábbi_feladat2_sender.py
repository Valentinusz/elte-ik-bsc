import socket

msg = b'Hello world'
sock = socket.socket(type=socket.SOCK_DGRAM)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
sock.sendto(msg, ('10.0.1.255', 5005))
sock.close()