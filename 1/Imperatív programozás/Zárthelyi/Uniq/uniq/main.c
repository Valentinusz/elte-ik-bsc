#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include "uniq.h"

int main(int argc, char* argv[]) {
    FILE *fp;

    int size = 4;
    int rowCount = 0;

    uniq* data = malloc(size * sizeof(uniq));

    if (!data) {raiseAllocationError();}


    int mode;

    if (argc == 1) {
        mode = 0; //stdin mode
    } else if (argc) {
        mode = 1; //file mode
    }

    for (int i = 0; i < argc; i++) {
        switch (mode) {
            case 0:
                fp = stdin;
                break;
            case 1:
                if (i == 0) {
                    continue;
                } else {
                    fp = fopen(argv[i], "r");
                    break;
                }
            default:
                break;
        }
        if (!fp) {
            fprintf(stderr, "File opening unsuccessful!\n");
            continue;
        } else {
            rowCount = 0;
            size = 4;

            char buffer[1024];
            while (fgets(buffer, 1023, fp) != NULL) {
                if (rowCount == size) {
                    size *= 2;
                    data = realloc(data, size * sizeof(uniq));
                    if (!data) {raiseAllocationError();}
                }

                buffer[1023] = '\0';
                buffer[strcspn(buffer, "\r\n")] = 0;

                int notIn = 1;

                for (int j=0; j<rowCount; j++) {
                    if (!(strcasecmp(buffer,data[j].string))) {
                        data[j].occurrence++;
                        notIn = 0;
                    }
                }

                if(notIn) {
                    strcpy(data[rowCount].string,buffer);
                    data[rowCount].occurrence = 0;
                    data[rowCount].occurrence++;
                    rowCount++;
                }
            }
            //printUniq(data,rowCount);
            printSorted(data,rowCount);
            reset(data,rowCount);
            fclose(fp);
        }
    }
    free(data);
    return 0;
}