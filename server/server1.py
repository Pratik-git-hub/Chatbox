import socket
import threading
host = '127.0.0.1'
port = 55555

server = socket.socket(socket.AF_INET , socket.SOCK_STREAM)

server.bind((host,port))
server.listen()
clients = []
users = []
#for sending messages to all
def broadcast(message,client):

    for client_a in clients:
        if client_a != client:
            client_a.send(message)

# for sending messages to specific client
def specific_client( index,message,from_client):
    print(message)
    message_copy = message
    message_handler = message_copy.split(" ")
    print(message_handler)
    sp = message_handler[3]
    print("sp : ", sp, len(sp), type(sp))

    if sp.find("all") == -1:
        to_client = clients[users.index(sp)]
        print("to client", to_client, "   ", users.index(sp))
        print(clients)
        print("ind :", index)
        name_of_from = users[index]
        print("name_of_from :", name_of_from)
        conv_message = name_of_from + message[11 + len(sp)::]
        print("conv_message : ", conv_message)
        to_client.send(("(privately)"+conv_message+"\n").encode("ascii"))
    else:
        name_of_from = users[index]
        print("name_of_from :", name_of_from)
        conv_message = name_of_from + message[11 + len(sp)::]
        print("conv_message : ", conv_message)
        broadcast((conv_message + "\n").encode('UTF-8'), from_client)
# for handling client
def handle(client):

    while True:
        index = clients.index(client)
        try:
            message = client.recv(1024).decode('UTF-8')
            specific_client(index,message,client)
        except:
            index = clients.index(client)
            clients.remove(client)
            client.close()
            username = users[index]
            broadcast(f'chat is been left by :{username}\n'.encode('ascii'),client)
            users.remove(username)
            break
# accept client socket
def recieve():
    while True:

    # accepting client
        try:
            client, address = server.accept()
            print(f'connected to address{address}')
        #getting username from client
            client.send("NICK\n".encode('ascii'))
            username = client.recv(1024).decode('UTF-8')
        # appending user & client to list
            users.append(username)
            clients.append(client)
            print(users)
            print(f'nickname of client {client} is {username}\n')
            broadcast(f'joined by :{username}\n'.encode('ascii'), client)
            client.send('connected to server\n'.encode('ascii'))
        # sending joined message to all groups
            for all_user in users:
                print(all_user)
                if all_user != username:
                    client.send(f'joined by :{all_user}\n'.encode('ascii'))
            print(f'joined by {username}\n'.encode('ascii'))
            print('connected to server\n'.encode('ascii'))
        except:
            print( BaseException)
            print("hiiiiiiiiiiiiiiiiiiii")
    #thread for handling client messages
        thread = threading.Thread(target=handle, args=(client,))
        thread.start()

print("waiting for connection .....")
recieve()