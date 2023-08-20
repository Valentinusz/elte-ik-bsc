#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "initString.h"
#ifndef READDATA_H
#define READDATA_H
void readData(int* rowCount, int* countOfStrings, char*** data_ptr, FILE *fp) {
    char buffer[1025];
    while (fgets(buffer, 1024, fp) != NULL) {
        if (*rowCount == *countOfStrings) {
            (*countOfStrings) *= 2;
            *data_ptr = realloc((*data_ptr), (*countOfStrings) * sizeof(char *));
            if (*data_ptr == NULL) {
                fprintf(stderr,"Memory allocation failed!");
                exit(1);
            }
            initString(*rowCount,*countOfStrings,data_ptr);
        }
        buffer[1024] = '\n';
        buffer[strcspn(buffer, "\r\n")] = 0;
        strcpy((*data_ptr)[*rowCount], buffer);
        (*rowCount)++;
    }
}
#endif //READDATA_H