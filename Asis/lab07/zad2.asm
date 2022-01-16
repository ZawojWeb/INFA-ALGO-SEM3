section    .text
    global _start
_start:

    mov rax, 1234 ;tu podajemy liczbe do przerobienia 
    mov rsp, 4
    mov rcx, 10;
loop:
    mov rdx, 0;
    div rcx
    add rdx, '0'
    mov [output+rsp], dl

    dec rsp
    cmp rax, 0
    ja loop

    mov rcx, output
    mov rbx, 1
    mov rax, 4
    mov rdx, 6
    int 0x80
    mov rbx, 0
    mov rax, 1
    int 0x80

section    .data
output db '00000', 0xa