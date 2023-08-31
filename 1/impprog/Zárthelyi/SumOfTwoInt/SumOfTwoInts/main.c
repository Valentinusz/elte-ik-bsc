#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "SumOfTwoInts.h"

int main(int argc, char* argv[]) {
    /*
    int len = 5;
    int arr[5] = {2, 5, -7, 6, 9};

    int a;
    int b;

    int test[3] = {-2, 7, 12};

    for (int i=0; i<3 ; i++) {
        int result = SumOfTwoInts(arr,test[i],len-1,&a,&b);
        if (result) {
            printf("%d: igen, %d = arr[%d] + arr[%d]\n",test[i],test[i],a,b);
        }
        else {
            printf("%d: nem\n",test[i]);
        }
    }
     */
    if (argc == 4) {
        int item = atoi(argv[1]);
        int N = atoi(argv[2]);

        int* array = malloc(N * (sizeof(int)));

        if (array) {
            char* token = strtok(argv[3],":");

            int i = 0;
            while(token != NULL) {
                array[i] = atoi(token);
                token = strtok(NULL,":");
                i++;
            }

            int a;
            int b;

            int result = SumOfTwoInts(array,item,N-1,&a,&b);
            if (result) {
                printf("%d: igen, %d = arr[%d] + arr[%d]\n",item,item,a,b);
            }
            else {
                printf("%d: nem\n", item);
            }

            free(array);
            return 0;
        }
    }
    return 1;
}
