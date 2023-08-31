import socket
import struct
import time
import select
import sys

msg_format = struct.Struct('i')


class ChecksumServer:
    def __init__(self, server_address='localhost', port=10001):
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.settimeout(1.0)
        self.server.bind((server_address, port))
        self.server.listen()
        self.inputs = [self.server]
        self.validation_dict = {}
        self.last_time = time.time()
        self.time = self.last_time

    def handle_new_connection(self):
        connection, client_address = self.server.accept()
        connection.setblocking(False)
        self.inputs.append(connection)

    def handle_input(self, readable):
        for sock in readable:
            if sock is self.server:
                self.handle_new_connection()
            else:
                self.handle_client_data(sock)

    def handle_client_data(self, sock):
        self.last_time = self.time
        self.time = time.time()

        for key in list(self.validation_dict.keys()):
            if self.time - self.last_time > self.validation_dict[key][3]:
                del self.validation_dict[key]

        data = sock.recv(512)
        if data:
            message = data.decode('utf-8').split('|')
            if message[0] == 'BE':
                message = [message[0], message[1], message[2], message[3], message[4]]
                self.validation_dict[message[1]] = (message[1], int(message[2]), message[4], int(message[3]))
                sock.sendall(b'OK')
            elif message[0] == 'KI':
                if message[1] in self.validation_dict.keys():
                    record = self.validation_dict[message[1]]
                    sock.sendall(bytes(str(record[1]) + '|' + str(record[2]), encoding='utf-8'))
                else:
                    sock.sendall(b'0|')
        else:
            self.inputs.remove(sock)
            sock.close()

    def handle_exceptional_condition(self, exceptional):
        for sock in exceptional:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            self.inputs.remove(sock)
            sock.close()

    def handle_connections(self):
        while self.inputs:
            try:
                readable, writable, exceptional = select.select(self.inputs, [], self.inputs, self.server.timeout)

                if not (readable or writable or exceptional):
                    continue

                self.handle_input(readable)
                self.handle_exceptional_condition(exceptional)
            except KeyboardInterrupt:
                print("Closing the system")
                for c in self.inputs:
                    c.close()
                self.inputs = []


try:
    server = ChecksumServer(sys.argv[1], int(sys.argv[2]))
    server.handle_connections()
except IndexError:
    print('Not enough arguments!', file=sys.stderr)
