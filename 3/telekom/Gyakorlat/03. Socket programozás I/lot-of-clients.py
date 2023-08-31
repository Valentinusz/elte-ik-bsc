import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(('localhost', 10000))
    server.listen(1)
    s1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s3 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    s1.connect(('localhost', 10000))
    # mivel a backlog mérete 1 ezért csak egy kapcsolódási kérelmet tud fogadni a szerver
    s2.connect(('localhost', 10000))
    s3.connect(('localhost', 10000))
