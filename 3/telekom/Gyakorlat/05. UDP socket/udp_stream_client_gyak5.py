import socket
import cv2
import numpy as np
import sys

if(len(sys.argv) != 3):
    print("Usage : {} hostname port".format(sys.argv[0]))
    print("e.g.   {} 192.168.0.39 1080".format(sys.argv[0]))
    sys.exit(-1)

# Az alábbi függvény készít egy ablakot, aminek az azonosítója "Image" lesz (ez egy helykitöltő lesz a hálózaton kapott képek számára):
cv2.namedWindow("Image")

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.settimeout(1.0)
host = sys.argv[1]
port = int(sys.argv[2])
server_address = (host, port)

while(True):
    try:
        sent = sock.sendto("get".encode('utf-8'), server_address)

        data, server = sock.recvfrom(65507)
        print("Fragment size : {}".format(len(data)))
        if len(data) == 4:
            # This is a message error sent back by the server
            if(data == b'FAIL'):
                continue
        # A data bytes típussal jön a hálózatról, az alábbi frombuffer függvény átkonvertálja olyan numpy tömbbe, aminek az 'uint8' típusú elemei a data bájtjai lesznek: 
        array = np.frombuffer(data, dtype=np.dtype('uint8'))
        # az array bájtok tömbjében lévő színes képet beolvassa az img változóba az alábbi függvény: 
        img = cv2.imdecode(array, 1)
        # Az "Image" azonosítjú ablakba megjeleníti az img képet az alábbi függvény:
        cv2.imshow("Image", img)
    except socket.timeout:
        pass
    # Az alábbi 1 milliszekundumig vár; azért kell, hogy a képek rendesen megjelenjenek meg:
    k = cv2.waitKey(1)
