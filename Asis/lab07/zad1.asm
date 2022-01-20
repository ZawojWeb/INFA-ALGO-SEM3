SECTION .data
    number:         dd      123456, 0
    avilableChars:  db      "0123456789abcdef",0
    dummpData:      db      "0",0

SECTION .text
global  _start
 
_start:
    mov r8, 0
    mov rax, [number]
    cmp rax, 0
    je endProgram
    jmp while
    

;while(number != 0){

while:
    mov rsi, 16
    xor rdx, rdx
    div rsi
    push rdx

    inc r8

    cmp rax, 0
    je print
    jmp while
;}


print:
    xor rax, rax
    xor rdx, rdx
    
    pop rdx
    mov rax, rdx

    mov rax, [avilableChars + rax]
    mov [dummpData], rax
    mov rcx, dummpData
    mov rdx, 1
    mov rbx, 1
    mov rax, 4
    int 0x80


    dec r8
    cmp r8, 0
    je endProgram
    jmp print



endProgram:
    
    mov [dummpData], byte 10
    mov rcx, dummpData
    mov rdx, 1
    mov rbx, 1
    mov rax, 4
    int 0x80

    mov rbx, 0
    mov rax, 1
    int 0x80






