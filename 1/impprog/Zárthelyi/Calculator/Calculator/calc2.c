#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "calc2.h"


int main(int argc, char* argv[]) {
    if (argc > 1) {
        for (int i=1; i<argc ; i++) {
            calculator calculator1;
            printf("%d ",reset(&calculator1));
            int j = 0;
            char c = argv[i][j];
            while(c != '\0') {
                if (isdigit(c)) {
                    printf("%d ", digit(&calculator1,atoi(&c)));
                } else {
                    switch(c) {
                        case '*':
                            printf("%d ",times(&calculator1));
                            break;
                        case '+':
                            printf("%d ",plus(&calculator1));
                            break;
                        default:
                            break;
                    }
                }
                j++;
                c = argv[i][j];
            }
            printf("\n");
        }
    }
    return 0;
}