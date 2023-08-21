import socket
import sys
import hashlib


class Server:
    def __init__(self, server_address='localhost', port=10000):
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind((server_address, port))
        self.server.listen()

    def handle_connection(self, checksum_server_address='localhost', checksum_port=10001, file_id=0, filename='copy.txt'):
        connection, client_address = self.server.accept()
        hash_md5 = hashlib.md5()
        with open(filename, 'bw') as file:
            data = connection.recv(512)
            while data != b'':
                file.write(data)
                hash_md5.update(data)
                data = connection.recv(512)
        connection.close()
        checksum_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        checksum_client.connect((checksum_server_address, checksum_port))
        checksum_client.send(bytes('|'.join(['KI', str(file_id)]), encoding='utf-8'))
        checksum_message = checksum_client.recv(512)
        checksum_message = checksum_message.decode('utf-8')
        checksum_message = checksum_message.split('|')
        if checksum_message[0] == 0 or checksum_message[1] != hash_md5.hexdigest():
            print('CSUM CORRUPTED')
        else:
            print('CSUM OK')


try:
    server = Server(sys.argv[1], int(sys.argv[2]))
    server.handle_connection(sys.argv[3], int(sys.argv[4]), int(sys.argv[5]), sys.argv[6])
except IndexError:
    print('Not enough arguments!', file=sys.stderr)
