import socket

mainServerSock = socket.socket()
mainServer_address = ('localhost', 10002)
mainServerSock.connect(mainServer_address)

server = socket.socket()
server_address = ('localhost', 10001)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind(server_address)
server.listen(5)

connection, client_address = server.accept()

msgFromClient = connection.recv(4096)
print("Message: " + msgFromClient.decode("utf-8"))

mainServerSock.sendall(msgFromClient)

msgFromServer = mainServerSock.recv(4096)
print("Message: " + msgFromServer.decode("utf-8"))

connection.sendall(msgFromServer)

mainServerSock.close()
connection.close()
server.close()