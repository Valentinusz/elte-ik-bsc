import socket
import select


class Server:
    def __init__(self, server_address='localhost', port=10001, timeout=1):
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.setblocking(False)
        self.server.settimeout(timeout)
        self.server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.server.bind((server_address, port))
        self.server.listen(5)
        self.timeout = timeout
        self.inputs = [self.server]

    def handle_new_connection(self, client_socket):
        connection, _ = client_socket.accept()
        connection.settimeout(1.0)
        self.inputs.append(connection)

    def handle_client_data(self, client_connection):
        data = client_connection.recv(1024)
        data = data.strip()
        if data:
            print(str(data))
            client_connection.sendall(b'OK')
        else:
            print(f'closing {client_connection.getpeername()} after reading no data')
            self.inputs.remove(client_connection)
            client_connection.close()

    def handle_inputs(self, readable):
        for soc in readable:
            if soc is self.server:
                self.handle_new_connection(soc)
            else:
                self.handle_client_data(soc)

    def handle_exceptional_condition(self, exceptional):
        for sock in exceptional:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            # Stop listening for input on the connection
            self.inputs.remove(sock)
            sock.close()

    def handle_connections(self):
        while self.inputs:
            try:
                readable, writable, exceptional = select.select(self.inputs, [], self.inputs, self.timeout)

                if not (readable or writable or exceptional):
                    continue

                self.handle_inputs(readable)
                self.handle_exceptional_condition(exceptional)
            except KeyboardInterrupt:
                print("Interrupted.")
                for c in self.inputs:
                    c.close()
                self.inputs = []


server = Server()
server.handle_connections()
