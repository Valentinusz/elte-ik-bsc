#include <stdio.h>
#include <stdlib.h>
#ifndef INITSTRING_H
#define INITSTRING_H
void initString(int rowCount, int countOfStrings, char*** data_ptr) {
    for (int i=rowCount; i< countOfStrings; i++) {
        (*data_ptr)[i] = (char *)calloc(1025,sizeof(char));
        if ((*data_ptr)[i] == NULL) {
            fprintf(stderr,"Memory allocation failed!");
            exit(1);
        }
    }
}
#endif //INITSTRING_H