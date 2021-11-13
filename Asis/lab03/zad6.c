#include <stdio.h>
    int main(){
        for(int i = 0; i < 255; i++){
            printf("\x1b[38;5;%im Hello World",i)
        }
    }