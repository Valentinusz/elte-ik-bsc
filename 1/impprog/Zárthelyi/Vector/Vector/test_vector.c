#include <stdio.h>
#include <stdlib.h>
#include "vector.h"

int main(int argc, char* argv[]) {
    if (argc > 1) {
        int capacity = atoi(argv[1]);
        vector vector1;
        initialize(&vector1,capacity);


        for (int i=2; i < argc; i+=2) {
            switch(argv[i][0]) {
                case 'a':
                    printf("a: %d\n",append(&vector1,atoi(argv[i+1])));
                    break;
                case 'r':
                    printf("r: %d\n",retrieve(&vector1,atoi(argv[i+1])));
                    break;
            }
        }

        dispose(&vector1);
    }
    return 0;
}