import struct
import select
import socket
import sys
import random

message_format = struct.Struct('c i')


class Server:
    def __init__(self, hostname='localhost', port=10000):
        self.number_to_guess = random.randrange(0, 100)
        server_address = (hostname, port)
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.settimeout(1.0)
        self.server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.server.bind(server_address)
        self.server.listen(5)
        self.end = False
        self.inputs = [self.server]

    def handle_new_connection(self):
        connection, client_address = self.server.accept()
        connection.setblocking(False)
        self.inputs.append(connection)

    def generate_response(self, message):
        if self.end:
            return b'V'

        relation = message[0].decode()
        if relation == '<':
            return b'I' if self.number_to_guess < message[1] else b'N'
        elif relation == '=':
            self.end = True
            return b'Y' if message[1] == self.number_to_guess else b'K'
        elif relation == '>':
            return b'I' if self.number_to_guess > message[1] else b'N'

    def handle_client_data(self, sock):
        data = sock.recv(message_format.size)
        if data:
            print(message_format.unpack(data))
            response = self.generate_response(message_format.unpack(data))
            server_message = (response, 0)
            sock.sendall(message_format.pack(*server_message))
        else:
            self.inputs.remove(sock)
            sock.close()

    def handle_input(self, readable):
        for sock in readable:
            if sock is self.server:
                self.handle_new_connection()
            else:
                self.handle_client_data(sock)

    def handle_exceptional_condition(self, exceptional):
        for sock in exceptional:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            # Stop listening for input on the connection
            self.inputs.remove(sock)
            sock.close()

    def handleConnections(self):
        while self.inputs:
            try:
                readable, writable, exceptional = select.select(self.inputs, [], self.inputs, self.server.timeout)

                if not (readable or writable or exceptional):
                    # timed out, do some other work here
                    continue

                self.handle_input(readable)
                self.handle_exceptional_condition(exceptional)
            except KeyboardInterrupt:
                print("Close the system")
                for c in self.inputs:
                    c.close()
                self.inputs = []


try:
    server = Server(sys.argv[1], int(sys.argv[2]))
except IndexError:
    server = Server()
server.handleConnections()
