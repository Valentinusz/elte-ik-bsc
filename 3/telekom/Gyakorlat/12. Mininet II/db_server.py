import sqlite3 as sl
import socket
import os
import sys

database_name = 'pets.db'

server_addr = sys.argv[1]
server_port = int(sys.argv[2])

# E.g. (on host h1): python3 db_server.py 10.0.1.1 10000

def init(db_exists):
  con = sl.connect(database_name)
  if not db_exists:
    with con:
      con.execute('CREATE TABLE DOGS(name VARCHAR(10), age INT);')
    # Prepared statement:
    sql = 'INSERT INTO DOGS (name, age) values(?, ?)'
    data = [('Ace', 1), ('Bailey', 5), ('Coco', 10)]
    with con:
        con.executemany(sql, data)
  return con

con = init(os.path.exists(database_name))

server = socket.socket()
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server.bind((server_addr,server_port))
server.listen(5)
connection, _ = server.accept()

while True:
  try:
    query = connection.recv(1024)
    if not query:
      print("Close the system")
      connection.close()
      server.close()
      sys.exit()
    query = query.strip()
    print('Incoming query:', query.decode())
    with con:
      data = con.execute(query.decode())
      result = ''
      for row in data:
        result += str(row) + '\n'
      connection.sendall(result.encode())
  except KeyboardInterrupt:
    print("Close the system")
    connection.close()
    server.close()
    sys.exit()
