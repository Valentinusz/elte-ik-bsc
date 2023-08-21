import socket
import sys


class Client:
    def __init__(self, server_address='localhost', server_port=10001):
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.connect((server_address, server_port))

    def handle_remote_server_message(self):
        data = self.client.recv(4096).decode('utf-8')
        if not data:
            print('\nDisconnected from server')
            sys.exit()
        else:
            print(str(data))

    def handle_connection(self):
        while True:
            msg = input('Message: ')
            if msg != '':
                msg = msg.strip()
                self.client.sendall(msg.encode())
            self.handle_remote_server_message()


client = Client()
client.handle_connection()
