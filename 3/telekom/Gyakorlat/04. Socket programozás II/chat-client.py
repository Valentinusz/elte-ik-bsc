import socket
import sys
import time
import select
import msvcrt


class ChatClient:
    def __init__(self, username, serverAddr='localhost', serverPort=10001, timeout=1):
        self.username = username
        self.setupClient(serverAddr, serverPort)
        self.prompt(False)
        self.socket_list = [self.connection]

    def setupClient(self, serverAddr, serverPort):
        server_address = (serverAddr, serverPort)

        # Create a TCP/IP socket
        self.connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Connect the socket to the port where the server is listening
        self.connection.connect(server_address)
        self.connection.sendall(self.username.encode())
        self.connection.settimeout(1.0)

    def prompt(self, nl):
        if nl:
            print('')
        sys.stdout.write('<' + self.username + '> ')
        sys.stdout.flush()

    def readInput(self, timeout=1):
        start_time = time.time()
        input = ''
        while True:
            if msvcrt.kbhit():
                chr = msvcrt.getche()
                if chr == b'\r':  # enter_key
                    break
                elif ord(chr) >= 32:  # space_char
                    input += chr.decode('utf-8')
            if len(input) == 0 and (time.time() - start_time) > timeout:
                break
        return input

    def exit_gracefully(self):
        print("\nClose the client")
        self.connection.close()
        sys.exit()

    def handleSocketError(self, err):
        errorcode = err.args[0]
        if errorcode == 10053:  # WSAECONNABORTED (ez akkor jöhet, amikor a kliens éppen gépel és a szerver leáll; amikor küldené az üzenetet, akkor jön a hiba)
            print('\r\nThe established connection was aborted by the remote server')
            sys.exit()
        else:
            raise  # Re-throwing exception

    def handleIncomingMessageFromRemoteServer(self, sock):
        if sock == self.connection:
            try:
                data = sock.recv(4096)
            except socket.error as err:
                self.handleSocketError(err)
            else:
                if not data:
                    print('\r\nDisconnected from server')
                    sys.exit()
                else:
                    data = data.decode('utf-8')
                    if not data.startswith("[" + self.username):
                        print('\r\n' + data)
                        sys.stdout.flush()
                        self.prompt(False)

    def handleConnection(self):
        while True:
            try:
                # Get the list sockets which are readable
                read_sockets, write_sockets, error_sockets = select.select(self.socket_list, [], [], 1)
                for sock in read_sockets:
                    self.handleIncomingMessageFromRemoteServer(sock)
                msg = self.readInput()
                if msg != '':
                    msg = msg.strip()
                    self.connection.sendall(msg.encode())
                    self.prompt(True)
            except KeyboardInterrupt:
                self.exit_gracefully()


username = sys.argv[1]
chatClient = ChatClient(username)
chatClient.handleConnection()