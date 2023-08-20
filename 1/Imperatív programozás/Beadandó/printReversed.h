#include <stdio.h>
#include <string.h>
#ifndef PRINTREVERSED_H
#define PRINTREVERSED_H
void printReversed(int rowCount, char** data_ptr, int isLast) {
    for (int i=(rowCount-1); i>=0; i--) {
        int l = strlen(data_ptr[i])-1;
        printf("%d ",i+1);
        for (int j=l; j>=0; j--) {
            printf("%c",data_ptr[i][j]);
        }
        if (isLast==0 || (i+1 != 1)) {
            printf("\n");
        }
    }
}
#endif //PRINTREVERSED_H