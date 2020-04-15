/*
Author: Minn Cho
Date Generated: 04/09/20
Last Updated: 05/09/20
Solves assignment 1 in LAB1

Takes in character inputs of max length 10 and returns a reversed version of the input. Implemented through both recursion and iteration.

Recursive version is borrowed from one of the classes.
*/
#include <stdio.h>
#include <stdlib.h>

void reverse(){
  char t;
  if((t = getchar()) != '\n'){    //stores the character from stdin into variable t
    reverse();                    //reverse gets called recursively until the end of file is reached
    putchar(t);                   //reverse starts printing character from the last one, thus inverting the input
  }
}

void reverseIterator(){
  char* t;
  char input;

  t = (char*)malloc(10 * sizeof(char)); //memory allocation of size 10 for character array t

  int count = 0;
  while((input = getchar()) != '\n'){   //stores character from stdin into variable input
    *t = input;                         //character is stored in array; *t is a pointer to array, this time the first index
    *t++;                               //increment pointer to the next index of the array
    count++;                            //count is a variable to count how much space the characters took in the array
  }

  while(count > 0){
    *t--;                               //Because the last while loop ended by incrementing the pointer and the count, there is one extra incrementation
    count--;                            //this is why we start by decrementing these variables
    putchar(*t);                        //and this prints the stored characters in a reverse manner
  }

}

int main(){
  reverse();
  putchar('\n');

  reverseIterator();
}
