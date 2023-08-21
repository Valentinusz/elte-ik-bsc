import socket
import struct
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

multicast_group_address = ('224.3.29.71',10000)

sock.settimeout(3)

ttl = struct.pack('b',3)
sock.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, ttl)

try:
 while True:
  try:
   sock.sendto('Hello clients'.encode(), multicast_group_address)
   data, client_address = sock.recvfrom(4096)
  except socket.timeout:
   print('timed out')
  else:
   print('Incoming message from ' + str(client_address))
   print('Message: ' + data.decode('utf-8'))
finally:
 print('closing socket')
 sock.close()
 