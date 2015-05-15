#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <string.h>
#define ERROR -1


int main(int argc, char **argv){
	int 				socket_server;
	struct sockaddr_in	address;
	char 				buf[1024];
	int 				nb_bytes;

	memset(&address, '0', sizeof(address));

	if( (socket_server = socket(AF_INET, SOCK_STREAM, 0)) == -1){
		perror("socket");
		return ERROR;
	}

	address.sin_family 		= AF_INET;
	address.sin_port 		= htons(5000);
	address.sin_addr.s_addr	= inet_addr("127.0.0.1");

	if(connect(socket_server, (struct sockaddr *)&address, sizeof(address)) == -1){
		perror("connect");
		return ERROR;
	}

	while(1){
		nb_bytes = recv(socket_server, buf, sizeof(buf), 0);
			if(nb_bytes == 0){
				fprintf(stdout, "Server deconnected\n");
				close(socket_server);
				return ERROR;
			} 
			else if (nb_bytes == -1){
				perror("recv");
				close(socket_server);
				return ERROR;
			} 
			else {
				printf("Read : %s\n", buf);
			}
	}
}