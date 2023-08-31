import socket
import select
import sys

class ProxyServer:
  def __init__(self, web_address, addr='localhost', port=10001, timeout=1):
    self.web_address = web_address
    self.port = port
    self.proxy_server = self.setupServer(addr)
    self.proxy_client = self.setupClient()
    # Sockets from which we expect to read
    self.inputs = [ self.proxy_server ]
    # Wait for at least one of the sockets to be ready for processing
    self.timeout=timeout
    self.cache = {}

  def setupServer(self, addr):
    # Create a TCP/IP socket
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setblocking(0)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    # Bind the socket to the port
    server_address = (addr, self.port)
    server.bind(server_address)
    
    # Listen for incoming connections
    server.listen(5)
    return server

  def setupClient(self):
    proxy_client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    proxy_client.connect(self.web_address)
    return proxy_client

  def handleNewConnection(self, sock):
    # A "readable" server socket is ready to accept a connection
    connection, client_address = sock.accept()
    print(client_address)
    connection.setblocking(0)	# or connection.settimeout(1.0)    
    self.inputs.append(connection)

  def forwardRequest(self, connection, data):
    data = data.decode('utf-8')
#    print(data)
    data = data.replace('localhost:'+str(self.port),self.web_address[0]).encode()
#    print(data.decode('utf-8'))
    self.proxy_client.sendall(data)
    dataFromServer = self.proxy_client.recv(65000)
    connection.sendall(dataFromServer)

  def handleDataFromClient(self, sock):
    data = sock.recv(65000)
    if data:            
        self.forwardRequest(sock, data)
    # Stop listening for input on the connection
    self.inputs.remove(sock)
    sock.close()

  def handleInputs(self, readable):
    for sock in readable:
        if sock is self.proxy_server:
            self.handleNewConnection(sock)
        else:
            self.handleDataFromClient(sock)

  def handleConnections(self):
    while self.inputs:
      try:
        readable, writable, exceptional = select.select(self.inputs, [], self.inputs, self.timeout)
    
        if not (readable or writable or exceptional):
            continue
    
        self.handleInputs(readable)
      except KeyboardInterrupt:
        print("Close the system")
        for c in self.inputs:
            c.close()
        self.inputs = []
        self.proxy_client.close()

web_address = (sys.argv[1],80)
proxyServer = ProxyServer(web_address, port=int(sys.argv[2]))
proxyServer.handleConnections()