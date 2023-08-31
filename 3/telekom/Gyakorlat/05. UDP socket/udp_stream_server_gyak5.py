import socket
import sys
from videograbber import VideoGrabber

if(len(sys.argv) != 2):
		print("Usage : {} port".format(sys.argv[0]))
		print("e.g. {} 1080".format(sys.argv[0]))
		sys.exit(-1)

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.settimeout(1.0)
port = int(sys.argv[1])
server_address = ("localhost", port)
print('starting up on %s port %s\n' % server_address)
sock.bind(server_address)

jpeg_quality = 25
grabber = VideoGrabber(jpeg_quality)
grabber.start()

running = True
while(running):
	try:
		try:
			data, address = sock.recvfrom(4)
			data = data.decode('utf-8')
			if(data == "get"):
					buffer = grabber.get_buffer()
					if buffer is None:
							continue
					if len(buffer) > 65507:
							print("The message is too large to be sent within a single UDP datagram. We do not handle splitting the message in multiple datagrams")
							sock.sendto("FAIL".encode('utf-8'),address)
							continue
					# We sent back the buffer to the client
					sock.sendto(buffer.tobytes(), address)
		except socket.timeout:
			pass
	except KeyboardInterrupt:
		grabber.stop()
		running = False

print("Quitting..")
grabber.join()
sock.close()
