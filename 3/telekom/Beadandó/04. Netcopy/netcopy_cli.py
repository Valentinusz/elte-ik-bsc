import sys
import socket
import hashlib

valid_for = 60


class Client:
    def __init__(self):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def send_file(self,
                  server_address='localhost', port=10000,
                  checksum_server_address='localhost', checksum_port=10001,
                  file_id=0, filename="file.txt",):

        self.client.connect((server_address, port))
        hash_md5 = hashlib.md5()
        with open(filename, 'br') as file:
            data = file.read(512)
            while data != b'':
                self.client.send(data)
                hash_md5.update(data)
                data = file.read(512)
            self.client.send(b'')

        self.client.close()
        checksum_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        checksum_client.connect((checksum_server_address, checksum_port))

        checksum_client.send(bytes('|'.join(['BE', str(file_id), str(valid_for), str(len(hash_md5.hexdigest())), hash_md5.hexdigest()]), encoding='utf-8'))

        response = checksum_client.recv(2)
        print(response.decode('utf-8'))


client = Client()
try:
    client.send_file(sys.argv[1], int(sys.argv[2]), sys.argv[3], int(sys.argv[4]), int(sys.argv[5]), sys.argv[6])
except IndexError:
    print('Not enough arguments!', file=sys.stderr)
