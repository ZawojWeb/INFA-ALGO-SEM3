#include <stdio.h>
    int main(){
        for(int i = 0; i < 255; i++){
            printf("\x1b[38;5;%im Hello World",i)
        }
    }

// Na Ubunut wyświetliło mi dużo kolorów, zakładam zatem ze da sie wyswietlic 256 kolrów
// Na Arch nie da się wyświetlić 256 kolrów, jest tylko 16