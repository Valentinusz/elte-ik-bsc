#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "calc.h"


int main(int argc, char* argv[]) {
    if (argc > 1) {
        for (int i=1; i<argc ; i++) {
            printf("%d ",reset());
            int j = 0;
            char c = argv[i][j];
            while(c != '\0') {
                if (isdigit(c)) {
                    printf("%d ", digit(atoi(&c)));
                } else {
                    switch(c) {
                        case '*':
                            printf("%d ",times());
                            break;
                        case '+':
                            printf("%d ",plus());
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
