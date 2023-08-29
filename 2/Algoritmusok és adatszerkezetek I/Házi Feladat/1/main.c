#include <stdio.h>

void max_subarray_3(int array[], int size, int* start, int* end) {
    if (size == 1) {
        *start = 0;
        *end = 0;
    } else {
        int max = array[0];
        for (int i=0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                int sum = 0;
                for (int k = i; k <= j; ++k) {
                    sum += array[k];
                }
                if (sum > max) {
                    max = sum;
                    *start = i;
                    *end = j;
                }
            }
        }
    }
}

void max_subarray_2(int array[], int size, int* start, int* end) {
    if (size == 0) {
        *start = -1;
        *end = -1;
    } else {
        *start = 0;
        *end = 0;
        int max = array[0];
        for (int i=0; i < size; ++i) {
            int sum = array[i];
            for (int j = i+1; j < size; ++j) {
                sum += array[j];
                if (sum > max) {
                    max = sum;
                    *start = i;
                    *end = j;
                }
            }
        }
    }
}

void max_subarray_1(int array[], int size, int* start, int* end) {
    int best = 0;
    int length = 0;
    int current = 0;
    for (int i = 0; i < size; ++i) {
        if (current + array[i] > 0) {
            current = current + array[i];
            length++;
        } else {
            current = 0;
            length = 0;
        }

        if (current > best) {
            best = current;
            *start = i - length + 1;
            *end = i;
        }
    }
}


int main() {

    //int array[] = {-2,1,-3,4,-1,2,1,-5,4};
    int array[] = {-2, -3, 4, -1, -2, 1, 5, -3};
    //int size = 9;
    int size = 8;

    int start = 0;
    int end = 0;

    max_subarray_3(array,size,&start,&end);

    printf("%d %d\n",start,end);

    start = 0;
    end = 0;

    max_subarray_2(array,size,&start,&end);

    printf("%d %d\n",start,end);


    start = 0;
    end = 0;

    max_subarray_1(array,size,&start,&end);

    printf("%d %d",start,end);
}
