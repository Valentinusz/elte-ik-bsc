import socket

with socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM) as connection:
    connection.connect(('localhost', 10000))
    connection.sendall(b'Hello Server!')
    data = connection.recv(1024)
    print(data.decode())
