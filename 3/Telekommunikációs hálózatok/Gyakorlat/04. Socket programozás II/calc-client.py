import struct
import socket
import sys
import time


class SimpleTCPSelectClient:
    def __init__(self, server_address='localhost', server_port=10001):
        # Create a TCP/IP socket
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Connect the socket to the port where the server is listening
        self.client.connect((server_address, server_port))

    def handle_remote_server_message(self):
        data = self.client.recv(4096).decode('utf-8')
        if not data:
            print('\nDisconnected from server')
            sys.exit()
        else:
            print(str(data))

    def handle_connection(self, msg):
        while True:
            if msg != '':
                print(msg)
                msg = msg.strip()
                msg = msg.split()
                self.client.sendall(packer.pack(float(msg[0]), msg[1].encode(), float(msg[2])))
            self.handle_remote_server_message()
            break


packer = struct.Struct("d c d")
client = SimpleTCPSelectClient()

for i in range(1, len(sys.argv)):
    print(sys.argv[i])
    client.handle_connection(sys.argv[i])
    time.sleep(2)
