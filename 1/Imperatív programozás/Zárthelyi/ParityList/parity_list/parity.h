#include <stdlib.h>
#ifndef PARITY_H
#define PARITY_H

typedef struct ParityArray {
    int* arr;
    int even_idx;
    int odd_idx;
    unsigned int size;
} ParityArray;

int InsertToParityArray(ParityArray* array,int item) {
    if (array->even_idx > array->odd_idx) {
        return 1;
    }
    if (item % 2 == 0) {
        array->arr[array->even_idx] = item;
        array->even_idx++;
    }
    else {
        array->arr[array->odd_idx] = item;
        array->odd_idx--;
    }
    return 0;
}

void PrintParityArray(ParityArray* array) {
    for (int i=0; i < array->even_idx; i++) {
        printf("%d ",array->arr[i]);
    }
    for (unsigned int i=array->odd_idx + 1; i < array->size; i++) {
        printf("%d ", array->arr[i]);
    }
}

int InitParityArray(ParityArray* array, int size) {
    array->even_idx = 0;
    array->odd_idx = size - 1;
    array->size = size;
    array->arr = calloc(size,sizeof(int));
    if (array->arr) {
        return 0;
    }
    else {
        return 1;
    }
}

void DisposeParityArray(ParityArray* array) {
    array->even_idx = 0;
    array->odd_idx = 0;
    array->size = 0;
    free(array->arr);
    array->arr = NULL;
}


#endif //PARITY_H
