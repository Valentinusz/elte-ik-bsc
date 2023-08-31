import socket

with socket.socket(type = socket.SOCK_DGRAM) as sock:
  server_address = ('localhost', 10000)
  sock.bind(server_address)
  
  data, address = sock.recvfrom(4096)
  
  print("Incoming message from " + str(address))
  print("Message: " + data.decode("utf-8"))
  
  sock.sendto(b'Hello kliens', address)
