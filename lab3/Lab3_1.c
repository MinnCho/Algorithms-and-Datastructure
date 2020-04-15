/*
Author: Minn Cho
Date Generated: 23/09/20
Last Updated: 23/09/20
Solves assignment 1 in LAB3

Implements a filter that replaces any non alphabet character with a blank space.
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void filter(){
    char a;
    while((a = getchar()) != EOF){
        if(isalpha(a) == 0){//if it isn't an alphabet, replace with blank space
            putchar(' ');
        }
        else{
            putchar(a);//otherwise, keep the character.
        }
    }
}

int main(){
    filter();
}