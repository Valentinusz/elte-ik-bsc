import socket

for i in range(1, 100):
    try:
        print(f'{i}: {socket.getservbyport(i)}')
    except OSError:
        print(f'{i}: no service')
