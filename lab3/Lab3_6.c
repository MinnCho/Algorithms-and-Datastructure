/*
Author: Minn Cho
Date Generated: 26/09/20
Last Updated: 30/09/20
Solves assignment 6 in LAB3

Implements a function to check the index of a word at every instance of it in a text.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void find(char str[], int size){
    char c;
    int index = 0;
    while((c = getchar()) != EOF){
        index++;
        if(c == ' ' || index == 1){//starts checking at a space(the start of a word)
            char comp [size];
            int i;
            if(index == 1){
                ungetc(c, stdin);
            }
            for(i = 0; i < size; i++){//starts storing the word in an array of the same size of the word that is looked for
                comp[i] = (c = getchar());
                index++;
            }
            if((((c = getchar()) == ' ' )|| (c == '\n')) && (strcmp(str, comp) == 0)){//if the words are identical and it is followed by a space of new line, return its index
                int wordIndex = index;
                wordIndex -= size;
                printf("%d \n", wordIndex); 
                ungetc(c, stdin);
            }
            else{//otherwise, continue looking.
                ungetc(c, stdin);
            }
        }
    }
}

int main(){

    /*int size;

    printf("The length of the word you are looking for: ");
    scanf("%d" , &size);

    char str[size];

    printf("Enter the word you are looking for: ");
    printf("%d", size);
    for(int i = 0; i <= size; i++){
        scanf("%c", &str[i]);
        printf("%c", str[i]);
        printf("%d\n", i);
    }

    for(int i = 0; i <= size; i++){
        printf("%c", str[i]);
    }*/
    
    int size = 3;
    char str[] = "the";

    find(str, size);
}