#include <stdlib.h>
#ifndef FREESTRING_H
#define FREESTRING_H
void freeString(int countOfStrings, char** data_ptr) {
    for (int i=0; i<countOfStrings; i++) {
        free(data_ptr[i]);
    }
}
#endif //FREESTRING_H