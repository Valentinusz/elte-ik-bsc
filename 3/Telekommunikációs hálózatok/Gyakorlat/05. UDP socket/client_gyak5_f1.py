import socket

with socket.socket(type = socket.SOCK_DGRAM) as connection:
  server_address = ('localhost', 10000)
  
  connection.sendto(b'Hello szerver', server_address)
  
  data, address = connection.recvfrom(4096)
  
  print("Incoming message from " + str(address))
  print("Message: " + data.decode("utf-8"))
