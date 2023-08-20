#ifndef UNIQ_H
#define UNIQ_H
#include <stdio.h>
#include <stdlib.h>

typedef struct uniq {
    int occurrence;
    char string[1024];
} uniq;

void printUniq(uniq* arr, int rowCount) {
    for (int j=0; j<rowCount; j++) {
        printf("%d %s\n",arr[j].occurrence,arr[j].string);
    }
}

void reset(uniq* arr, int rowCount) {
    for (int j=0; j<rowCount; j++) {
        arr[j].occurrence = 0;
        strcpy(arr[j].string,"");
    }
}

void raiseAllocationError() {
    fprintf(stderr,"Memory allocation failed!\n");
    exit(1);
}

void printSorted(uniq* data,int rowCount) {
    int* array = malloc(4* sizeof(int));
    int* index = malloc(4* sizeof(int));

    for (int i=0; i<rowCount; i++) {
        array[i] = data[i].occurrence;
        index[i] = i;
    }

    int temp, temp2;
    for (int i = 0; i < rowCount; i++) {
        for (int j = i + 1; j < rowCount; j++) {
            if (array[i] < array[j]) {
                temp = array[i];
                temp2 = index[i];
                array[i]= array[j];
                index[i] = index[j];
                array[j] = temp;
                index[j] = temp2;
            }
        }
    }

    for (int i=0; i<rowCount; i++) {
        printf("%d %s\n",data[index[i]].occurrence,data[index[i]].string);
    }

    free(array);
    free(index);
}

#endif //UNIQ_H
