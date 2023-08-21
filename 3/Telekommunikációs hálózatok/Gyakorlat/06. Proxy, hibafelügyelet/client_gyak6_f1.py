import socket

connection = socket.socket()

server_address = ('localhost', 10001)
connection.connect(server_address)

connection.sendall(b'Hello szerver')

data  = connection.recv(4096)
print("Message: " + data.decode("utf-8"))

connection.close()