/*
Author: Minn Cho
Date Generated: 20/09/20
Last Updated: 21/09/20
Solves assignment 1 in LAB2

Implements a function to sort all negative numbers to the left, positive numbers to the right and 0s in between.

Loop invariant: minNeg, the counter counting the number of negative numbers. Anything left of the index of the array[minNeg] will be negative.
*/

#include <stdio.h>
#include <stdlib.h>

void print(int a[], int size){
    int i;
    for(i = 0; i < size; i++){
        printf("%d ", a[i]);
    }
}


void sort(int a[] , int size){
    int temp = 0;
    int minNeg = 0; //counter to count the numbers of negative numbers

    int i;
    for(i = 0; i < size; i++){//traverse the whole array
        if(a[i] < 0){ //if the element is negative 
            if(minNeg != i){//and if it isn't already sorted, swap it with the current position's element
                temp = a[i];
                a[i] = a[minNeg];
                a[minNeg] = temp;
            }
            minNeg++;
        }  
    }

    int j;
    for(j = minNeg; j < size; j++){//traverse array again to find the zeros
        if(a[j] == 0){
            if(minNeg != j){//swap it with j if it isn't in the correct position already.
                temp = a[j];
                a[j] = a[minNeg];
                a[minNeg] = temp;
            }
            minNeg++;
        }
    }
    print(a, size);
}

int main(){
    int size;
    int *a;

    printf("Size of array: ");
    scanf("%d", &size);

    a = (int*)malloc(size * sizeof(int));

    int i;
    for(i = 0; i < size; i++){
        scanf("%d", &a[i]);
    }

    sort(a , size);
}
/*
Size of array: 6
8
0
-4
0
-5
-7
-4 -5 -7 0 0 8
*/
