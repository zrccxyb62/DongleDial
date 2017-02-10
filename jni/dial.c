#include <stdio.h> // standard input / output functions
#include <string.h> // string function definitions
#include <unistd.h> // UNIX standard function definitions
#include <fcntl.h> // File control definitions
#include <errno.h> // Error number definitions
#include <termios.h> // POSIX terminal control definitionss
#include <time.h>   // time calls
#include "dial.h"


int open_port(void)
{
	int fd; // file description for the serial port

	fd = open("/dev/ttyUSB0", O_RDWR | O_NOCTTY | O_NDELAY);

	if(fd == -1) // if open is unsucessful
	{
		//perror("open_port: Unable to open /dev/tty* - ");
		fd = open("/dev/ttyUSB2", O_RDWR | O_NOCTTY | O_NDELAY);
		if(fd == -1)
			fd = open("/dev/ttyUSB3", O_RDWR | O_NOCTTY | O_NDELAY);
		printf("open_port: Unable to open /dev/ttyUSB0. Now open 2 \n");
	}
	else
	{
		fcntl(fd, F_SETFL, 0);
		printf("port is open.\n");
	}

	return(fd);
} //open_port

int configure_port(int fd)      // configure the port
{
	struct termios port_settings;      // structure to store the port settings in

	cfsetispeed(&port_settings, B115200);    // set baud rates
	cfsetospeed(&port_settings, B115200);

	port_settings.c_cflag &= ~PARENB;    // set no parity, stop bits, data bits
	port_settings.c_cflag &= ~CSTOPB;
	port_settings.c_cflag &= ~CSIZE;
	port_settings.c_cflag |= CS8;

	tcsetattr(fd, TCSANOW, &port_settings);    // apply the settings to the port
	return(fd);

} //configure_port

int query_modem(int fd, int number)   // query modem with an AT command
{
	char dialchar[15];
	sprintf(dialchar, "%d", number);

	char n;
	fd_set rdfs;
	struct timeval timeout;

	// initialise the timeout structure
	timeout.tv_sec = 10; // ten second timeout
	timeout.tv_usec = 0;

	//Create byte array
	unsigned char send_bytes1[] = {'A','T','+','C','D','V'};
	//unsigned char send_bytes2[] = dialchar;
	unsigned char send_bytes3[] = {'\r','\n'};
	int sizesendbytes= sizeof(send_bytes1)+sizeof(dialchar)+sizeof(send_bytes3);
    char send_bytes[sizesendbytes];
    sprintf(send_bytes, "%s%s%s", send_bytes1,dialchar,send_bytes3);

	write(fd, send_bytes, sizeof(send_bytes));  //Send data
	printf("Wrote the bytes. \n");

	// do the select
	n = select(fd + 1, &rdfs, NULL, NULL, &timeout);

	// check if an error has occured
	if(n < 0)
	{
	 perror("select failed\n");
	}
	else if (n == 0)
	{
	 puts("Timeout!");
	}
	else
	{
	 printf("\nBytes detected on the port!\n");
	}

	return 0;

} //query_modem

int query_modem1(int fd, int number)   // query modem with an AT command
{
	char dialchar[15];
	sprintf(dialchar, "%d", number);

	char n;
	fd_set rdfs;
	struct timeval timeout;

	// initialise the timeout structure
	timeout.tv_sec = 10; // ten second timeout
	timeout.tv_usec = 0;

	//Create byte array
	unsigned char send_bytes1[] = {'A','T','+','C','H','V','\r','\n'};



	write(fd, send_bytes1, sizeof(send_bytes1));  //Send data
	printf("Wrote the bytes. \n");

	// do the select
	n = select(fd + 1, &rdfs, NULL, NULL, &timeout);

	// check if an error has occured
	if(n < 0)
	{
	 perror("select failed\n");
	}
	else if (n == 0)
	{
	 puts("Timeout!");
	}
	else
	{
	 printf("\nBytes detected on the port!\n");
	}

	return 0;

}

/*int main(void)
{
	int fd = open_port();
	configure_port(fd);
	query_modem(fd);

	return(0);

} *///main

JNIEXPORT jint JNICALL Java_com_example_dongledial_Call_dial
  (JNIEnv * je, jclass jc, jint number){
	//main();
	int fd = open_port();
	configure_port(fd);
	if(number==0)
	{
		query_modem1(fd,number);
		return (fd);
	}
	else
	{
		query_modem(fd,number);
		return (fd);
	}
}
