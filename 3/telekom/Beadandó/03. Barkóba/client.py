import struct
import socket
import sys

message_format = struct.Struct('c i')


class Client:
    def __init__(self, hostname='localhost', port=10000):
        server_address = (hostname, port)
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.client.connect(server_address)
        self.lower_bound = 0
        self.upper_bound = 100

    def handle_connection(self):

        last = b'<'
        client_message = b'<'

        guess = (self.lower_bound + self.upper_bound) // 2
        self.client.sendall(message_format.pack(client_message, guess))

        while True:
            data = self.client.recv(message_format.size)
            server_response = message_format.unpack(data)[0].decode()
            last_guess = guess

            if data:
                if server_response == 'V' or server_response == 'Y' or server_response == 'K':
                    self.client.close()
                    break
                else:
                    if self.lower_bound >= self.upper_bound:
                        client_message = b'='

                    if self.lower_bound + 1 == self.upper_bound:
                        client_message = b'>'

                    if server_response == 'I':
                        if last == b'<':
                            self.upper_bound = last_guess-1
                        if last == b'>':
                            self.lower_bound = last_guess+1
                    elif server_response == 'N':
                        if last == b'<':
                            self.lower_bound = last_guess
                        if last == b'>':
                            self.upper_bound = last_guess
                guess = (self.lower_bound + self.upper_bound) // 2
                self.client.sendall(message_format.pack(client_message, guess))


try:
    client = Client(sys.argv[1], int(sys.argv[2]))
except IndexError:
    client = Client()
client.handle_connection()
