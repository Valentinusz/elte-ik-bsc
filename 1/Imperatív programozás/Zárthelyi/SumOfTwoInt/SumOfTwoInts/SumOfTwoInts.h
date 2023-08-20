#ifndef SUMOFTWOINTS_H
#define SUMOFTWOINTS_H

static int indexOf(int array[], int item, int lastIndex) {
    int index = lastIndex + 1;

    for (int i=0; i<=lastIndex; i++) {
        if(array[i] == item) {
            index = i;
        }
    }
    return index;
}

int SumOfTwoInts(int array[], int item, int lastIndex, int* first, int* second) {
    int result = 0;
    int i = 0;
    while (!result && i <= lastIndex) {
        int difference = item - array[i];
        int idx = indexOf(array,difference,lastIndex);
        if (idx <= lastIndex && i != idx) {
            *first = i;
            *second = idx;
            result = 1;
        }
        else {
            i++;
        }
    }
    return result;
}
#endif //SUMOFTWOINTS_H
