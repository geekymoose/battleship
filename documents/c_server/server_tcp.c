#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#include <sys/types.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
#define ERROR -1

int start_server();
int display_local_socket_data(int);
int display_peer_socket_data(int);
void start_connection(int);

/**
 * Start program
 */
int main(int argc, char** argv){
	start_server();
	return EXIT_SUCCESS;
}

/**
 * Lauch TCP server
 */
int start_server(){
	struct sockaddr_in 	address;
	socklen_t 			length;
	int 				listener_socket;
	int 				new_socket;
	int 				fd_max;
	fd_set 				master_fds;
	fd_set 				read_fds;
	char 				buf[256];
	int 				nb_bytes;

	length = sizeof(address);
	FD_ZERO(&master_fds);
	FD_ZERO(&read_fds);
	memset(&address, '0', length);
	address.sin_family			= AF_INET;
	address.sin_port 			= htons(5000);
	address.sin_addr.s_addr 	= htonl(INADDR_ANY);

	if( (listener_socket = socket(AF_INET, SOCK_STREAM, 0)) == -1){
		perror("socket");
		return ERROR;
	}

	if(bind(listener_socket, (struct sockaddr*)&address, length) == -1){
		perror("bind");
		return ERROR;
	}

	if(listen(listener_socket, 10) == -1){
		perror("listen");
		return ERROR;
	}

	FD_SET(listener_socket, &master_fds);
	fd_max = listener_socket;

	fprintf(stdout, "Server address >> ");
	display_local_socket_data(listener_socket);
	fprintf(stdout, "\n");


	//Main loop
	while(1){
		read_fds = master_fds;
		if(select(fd_max+1, &read_fds, NULL, NULL, NULL) == -1){
			perror("select");
			return ERROR;
		}

		int i;
		for(i=0; i<=fd_max; i++){
			if(FD_ISSET(i, &read_fds)){

				//Socket is the listener socket : add the new client socket
				if(i == listener_socket){
					if( (new_socket = accept(listener_socket, NULL, &length)) == -1){
						perror("accept");
						return ERROR;
					}
					if(new_socket>fd_max){
						fd_max = new_socket;
					}
					FD_SET(new_socket, &master_fds);
					fprintf(stdout, "New connection : ");
					display_peer_socket_data(new_socket);
				} 

				//Existing client socket, handle connection
				else{
					//Send data to client
					if(nb_bytes = recv(i, buf, sizeof(buf), 0) <= 0){
						if(nb_bytes == 0){
							printf("Client logoff >> ");
							display_peer_socket_data(i);
						} 
						else {
							perror("client error");
							display_peer_socket_data(i);
						}
						close(i);
						FD_CLR(i, &master_fds);
					} 
					//Got some data from client
					else{
						int j;
						for(j = 0; j <= fd_max; j++) {
                            // send to everyone!
                            if (FD_ISSET(j, &master_fds)) {
                                // except the listener and ourselves
                                if (j != listener_socket && j != i) {
                                    if (send(j, buf, nb_bytes, 0) == -1) {
                                        perror("send");
                                    }
                                }
                            }
                        }
					}
				}
			}//End if FD_ISSET
		}//End for loop
	}//End main loop
}

 /**
 * Display features of a local socket
 * Format : 'IP = x.x.x.x, PORT = X' (then, return line)
 *
 * @param socked to display
 * @return EXIT_SUCCESS if execute successfully, otherwise, return ERROR
 */
int display_local_socket_data(int socked){
	socklen_t 				lenght;
	struct sockaddr_in		address; 

	lenght = sizeof(address);
	if(getsockname(socked, (struct sockaddr *)&address, &lenght) == -1){
		perror("getsockname");
		return ERROR;
	}
	fprintf(stdout, "IP : %s - PORT : %u\n", inet_ntoa(address.sin_addr), ntohs(address.sin_port));
	return EXIT_SUCCESS;
}

 /**
 * Display features of a peer socket
 * Format : 'IP = x.x.x.x, PORT = X' (then, return line)
 *
 * @param socked to display
 * @return EXIT_SUCCESS if execute successfully, otherwise, return ERROR
 */
int display_peer_socket_data(int socket){
	socklen_t 			length;
	struct sockaddr_in 	address;

	length = sizeof(address);
	if(getpeername(socket, (struct sockaddr *)&address, &length) == -1){
		perror("getpeername");
		return ERROR;
	}
	fprintf(stdout, "IP : %s - PORT : %u\n", inet_ntoa(address.sin_addr), ntohs(address.sin_port));
	return EXIT_SUCCESS;
}

/**
 * 
 */
void start_connection(int accept_sock){
	struct sockaddr_in	client_address;
	socklen_t 			length;
	char				buffer[256];
    time_t              log_time;

    log_time   = time(NULL);
	//sprintf(buffer, "IP = %s. PORT = %u\n", inet_ntoa(client_address.sin_addr),
	//										ntohs(client_address.sin_port));

	fprintf(stdout, "\nConnection at %s\tserver >>", ctime(&log_time));
	display_local_socket_data(accept_sock);
	fprintf(stdout, "\tclient >>");
	display_peer_socket_data(accept_sock);


	fprintf(stdout, "Sent");


	if(send(accept_sock, "Welcome", 14, 0) == -1){
		perror("sent");
		close(accept_sock);
		exit(ERROR);
	}

	fprintf(stdout, "Wait for Answer");
	char str[1024] = "";
	if(recv(accept_sock, str, strlen(str), 0) == -1){
		perror("recv");
		close(accept_sock);
		exit(ERROR);
	}

	fprintf(stdout, "Answer from clien : %s", str);
}