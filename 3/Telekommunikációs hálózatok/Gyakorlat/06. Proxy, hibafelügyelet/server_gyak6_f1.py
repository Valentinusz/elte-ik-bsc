import socket

mainServerSock = socket.socket()
mainServer_address = ('localhost', 10002)
mainServerSock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
mainServerSock.bind(mainServer_address)
mainServerSock.listen(5)

connection, client_address = mainServerSock.accept()

msgFromClient = connection.recv(4096)
print("Message: " + msgFromClient.decode("utf-8"))

connection.sendall(b'Hello kliens')

connection.close()
mainServerSock.close()