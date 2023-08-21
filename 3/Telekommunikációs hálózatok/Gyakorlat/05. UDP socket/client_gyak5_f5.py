import socket
import sys
import time
import select
import msvcrt

class ChatClientUDP:
  def __init__(self, username, serverAddr='localhost', serverPort=10001, timeout=1):
    self.username = username
    self.setupClient(serverAddr, serverPort)
    self.prompt(False)
    self.socket_list = [self.client]
    self.logout_msg = "["+self.username+"] is LOGOUT"

  def setupClient(self, serverAddr, serverPort):
    self.server_address = (serverAddr, serverPort)

    # Create a UDP socket
    self.client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    
    self.client.sendto(self.username.encode(), self.server_address)
    self.client.settimeout(1.0)

  def prompt(self, nl) :
    if nl:
        print('')
    sys.stdout.write('<'+self.username+'> ')
    sys.stdout.flush()

  def readInput(self, timeout = 1):
    start_time = time.time()
    input = ''
    while True:
      if msvcrt.kbhit():
        chr = msvcrt.getche()
        if chr == b'\r': # enter_key
            break
        elif ord(chr) >= 32: # space_char
            input += chr.decode('utf-8')
      if len(input) == 0 and (time.time() - start_time) > timeout:
          break
    return input

  def exit_gracefully(self):
    print("\nClose the client")
    self.client.close()
    sys.exit()
  
  def handleIncomingMessageFromRemoteServer(self, sock):
    if sock == self.client:
      data, serveraddr = sock.recvfrom(4096)
      if data == b'SERVER STOP':
        print('\r\nThe server has stopped working.')
        sys.exit()
      else:
        data = data.decode('utf-8')
        if not data.startswith("["+self.username):
          print('\r\n'+data)
          sys.stdout.flush()
          self.prompt(False)
  
  def handleConnection(self):
    while True:
      try:
        # Get the list sockets which are readable
        read_sockets, write_sockets, error_sockets = select.select(self.socket_list , [], [], 1)
        for sock in read_sockets:
          self.handleIncomingMessageFromRemoteServer(sock)
        msg = self.readInput()
        if msg != '':
          msg = msg.strip()
          self.client.sendto(msg.encode(), self.server_address)
          self.prompt(True)    
      except KeyboardInterrupt:
          self.client.sendto(self.logout_msg.encode(), self.server_address)
          self.exit_gracefully()

username = sys.argv[1]
chatClient = ChatClientUDP(username)
chatClient.handleConnection()