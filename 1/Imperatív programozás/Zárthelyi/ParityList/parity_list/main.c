#include <stdio.h>
#include "parity.h"

int main() {

    ParityArray arr;
    InitParityArray(&arr,5);

    InsertToParityArray(&arr,2);
    InsertToParityArray(&arr,7);
    InsertToParityArray(&arr,1);
    // itt ellenőrizni kéne hogy sikeres-e a foglalás, de mivel a feladat
    // nem kérte hogy én írjam meg a bemeneti részt feltételezem, hogy
    // a bemenetet megíró személy írja meg

    PrintParityArray(&arr);

    InsertToParityArray(&arr,9);

    printf("\n");

    InsertToParityArray(&arr,4);

    PrintParityArray(&arr);

    DisposeParityArray(&arr);

    return 0;
}
