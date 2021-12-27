#include <stdlib.h>

int main(){
    char* nm[2];
    nm[0] = "bash";
    nm[1] = NULL; //bcs this need execvp

    setuid(0);
    execvp("/bin/bash",nm);
    // you can aslo use execv, but you have to put path in array
}