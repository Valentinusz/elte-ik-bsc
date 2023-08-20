#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "printReversed.h"
#include "readData.h"
#include "freeString.h"

int main(int argc, char* argv[]) {
    FILE *fp;

    int countOfStrings = 8;
    int rowCount = 0;

    char **data_ptr = malloc(countOfStrings * sizeof(char *));
    if (data_ptr == NULL) {
        fprintf(stderr,"Memory allocation failed!");
        exit(1);
    }

    if (argc == 1) {
        fp = stdin;
        initString(rowCount,countOfStrings,&data_ptr);
        readData(&rowCount,&countOfStrings,&data_ptr,fp);
        fclose(fp);
        printReversed(rowCount,data_ptr,0);
        freeString(countOfStrings,data_ptr);
    }
    else {
        int isLast;
        for (int i = 1; i < argc; i++) {
            if (i+1 == argc) {
                isLast = 1;
            }
            fp = fopen(argv[i], "r");
            if (fp != NULL) {
                initString(rowCount,countOfStrings,&data_ptr);
                readData(&rowCount,&countOfStrings,&data_ptr,fp);
                fclose(fp);
                printReversed(rowCount,data_ptr,isLast);
                freeString(countOfStrings,data_ptr);
                rowCount = 0;
                countOfStrings = 8;
            }
            else {
                fprintf(stderr,"File opening unsuccessful!");
            }
        }
    }
    free(data_ptr);
    return 0;
}