import struct
import select
import socket

unpacker = struct.Struct('d c d')


def unpack_expression(data):
    operand1, operator, operand2 = unpacker.unpack(data)

    output = f'{operand1} {operator.decode()} {operand2} = '

    match operator.decode():
        case '+':
            output += str(operand1 + operand2)
        case '-':
            output += str(operand1 - operand2)
        case '*':
            output += str(operand1 * operand2)
        case '/':
            output += str(operand1 / operand2)
    return output


class CalcTCPSelectServer:
    def __init__(self, address='localhost', port=10001, timeout=1):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setblocking(False)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        server_socket.bind((address, port))

        server_socket.listen(5)

        self.server = server_socket

        # Sockets from which we expect to read
        self.inputs = [self.server]
        # Wait for at least one of the sockets to be ready for processing
        self.timeout = timeout

    def handle_new_connection(self, sock):
        # A "readable" server socket is ready to accept a connection
        connection, client_address = sock.accept()
        connection.setblocking(0)  # or connection.settimeout(1.0)
        self.inputs.append(connection)

    def handle_client_data(self, sock):
        data = sock.recv(1024)
        data = data.strip()
        if data:
            unpacked = unpack_expression(data)
            print(unpacked)
            sock.sendall(unpacked.encode())
        else:
            # Interpret empty result as closed connection
            print('closing ' + str(sock.getpeername()) + ' after reading no data')
            # Stop listening for input on the connection
            self.inputs.remove(sock)
            sock.close()

    def handle_inputs(self, readable):
        for sock in readable:
            if sock is self.server:
                self.handle_new_connection(sock)
            else:
                self.handle_client_data(sock)

    def handle_exception(self, exceptions):
        for sock in exceptions:
            print('handling exceptional condition for ' + str(sock.getpeername()))
            # Stop listening for input on the connection
            self.inputs.remove(sock)
            sock.close()

    def handle_connections(self):
        while self.inputs:
            try:
                readable, writable, exceptions = select.select(self.inputs, [], self.inputs, self.timeout)

                if not (readable or writable or exceptions):
                    # timed out, do some other work here
                    continue

                self.handle_inputs(readable)
                self.handle_exception(exceptions)
            except KeyboardInterrupt:
                print("Close the system")
                for c in self.inputs:
                    c.close()
                self.inputs = []


simpleTCPSelectServer = CalcTCPSelectServer()
simpleTCPSelectServer.handle_connections()
