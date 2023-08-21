import socket

sock = socket.socket(type=socket.SOCK_DGRAM)
sock.bind(('10.0.1.255', 5005))
data, addr = sock.recvfrom(1024)
print(data, addr)
sock.close()