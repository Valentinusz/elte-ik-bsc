import socket
import struct
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

multicast_group = '224.3.29.71'
address = ('',10000)
sock.bind(address)

group = socket.inet_aton(multicast_group)
mreq = struct.pack('4sL', group, socket.INADDR_ANY)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq)

print('Waiting to receive message')
data, sender_address = sock.recvfrom(4096)
print('Incoming message from ' + str(sender_address))
print('Message: ' + data.decode('utf-8'))

sock.sendto('ACK'.encode(), sender_address)

sock.close()
