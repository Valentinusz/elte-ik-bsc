import socket
import sys
import time
import select

class ChatClientBroadcastUDP:
  def __init__(self, username, broadcastAddr='10.0.1.255', broadcastPort=5005, timeout=1):
    self.username = username
    self.setupClient(broadcastAddr, broadcastPort)
    self.prompt()
    self.socket_list = [sys.stdin, self.client]
    self.logout_msg = "["+self.username+"] is LOGOUT"

  def setupClient(self, broadcastAddr, broadcastPort):
    self.broadcast_address = (broadcastAddr, broadcastPort)
    self.client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    self.client.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    self.client.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
    self.client.bind(self.broadcast_address)
    self.client.settimeout(1.0)

  def prompt(self):
    sys.stdout.write('<'+self.username+'> ')
    sys.stdout.flush()

  def exit_gracefully(self):
    print("\nClose the client")
    self.client.close()
    sys.exit()
  
  def handleIncomingMessage(self, sock):
    if sock == self.client:
      data, serveraddr = sock.recvfrom(4096)
      data = data.decode('utf-8')
      if not data.startswith("["+self.username):
        print('\n'+data)
        sys.stdout.flush()
        self.prompt()
    elif sock == sys.stdin:
      msg = sys.stdin.readline()
      msg = msg.strip()
      msg = "["+self.username+"] " + msg
      self.client.sendto(msg.encode(), self.broadcast_address)
      self.prompt()
  
  def handleMessages(self):
    while True:
      try:
        # Get the list sockets which are readable
        read_sockets, write_sockets, error_sockets = select.select(self.socket_list , [], [], 1)
        for sock in read_sockets:
          self.handleIncomingMessage(sock)  
      except KeyboardInterrupt:
          self.client.sendto(self.logout_msg.encode(), self.broadcast_address)
          self.exit_gracefully()

username = sys.argv[1]
chatClient = ChatClientBroadcastUDP(username)
chatClient.handleMessages()