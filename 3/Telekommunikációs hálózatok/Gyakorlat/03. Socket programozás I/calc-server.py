import socket
import struct

unpacker = struct.Struct('d c d')
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind(('localhost', 10000))
    server.listen(1)
    connection, client_address = server.accept()
    with connection:
        data = connection.recv(1024)
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
        print(output)
