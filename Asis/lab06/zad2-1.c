#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

void sighandler(int);

int main(){
    for (int i = 1; i <= 64; i++)
    {
        signal(i, sighandler); // here we run all signgs
    }
    

    while (1)
    {
        printf("Gooing sleep for sec\n");
        sleep(1);    
    }
    
    return 0;
}
void sighandler(int signum){
    printf("Cought signal %d, comming out.. \n", signum);
    if(signum == 2){
        exit(1);  //Ctrl+c
    }
}