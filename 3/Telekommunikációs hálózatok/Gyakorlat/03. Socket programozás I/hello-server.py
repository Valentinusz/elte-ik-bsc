import socket

with socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM) as server:
    server.bind(('localhost', 10000))
    server.listen(1)
    connection, client_address = server.accept()
    with connection:
        print(f'Connected by {client_address}')
        data = connection.recv(1024)
        print(data.decode())
        connection.sendall(b'Hello Client!')
