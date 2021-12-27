#include <signal.h>
#include <stdio.h>
#include <stdlib.h>


int main(){
    setuid(0);
    printf("%d\n", kill(1,SIGKILL));
    return;
}