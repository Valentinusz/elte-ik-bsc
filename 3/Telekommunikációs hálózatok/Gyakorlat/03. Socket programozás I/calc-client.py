import sys
import socket
import struct

packer = struct.Struct("d c d")
try:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as connection:
        connection.connect(('localhost', 10000))
        packed_data = packer.pack(float(sys.argv[1]), str(sys.argv[2]).encode(), float(sys.argv[3]))
        connection.sendall(packed_data)
except TypeError:
    print('Please supply a number for arguments: 1, 3.', file=sys.stderr)
except IndexError:
    print(f'Not enough command-line arguments. Need 3, found {len(sys.argv)-1}')
