#include <stdio.h>
#include <ctype.h>
#define SIZE 16

void printLine(unsigned char arr[], int count, long address) {
    printf("%08lx  ",address);
    for (int i=0; i < SIZE; i++) {
        if (i < count) {
            printf("%02x ",arr[i]);
            if (i == 7) {
                printf(" ");
            }
        } else {
			if (i == 7) {
				printf("    ");
			} else {
				printf("   ");
			}
        }
    }
    printf(" |");
    for (int i=0; i < count ; ++i) {
        if (isgraph(arr[i]) || arr[i] == ' ') {
            printf("%c",arr[i]);
        }
        else {
            printf(".");
        }
    }
    printf("|\n");
}

void hd(FILE* fp, int* c, long* address, unsigned char arr[], int* count) {
    while((*c = fgetc(fp)) != EOF) {
        if (*count == 16) {
            printLine(arr,*count,*address);
            *address += *count;
            *count = 0;
        }
        arr[*count] = *c;
        (*count)++;
        //printf("%02x\n",(unsigned char)c);
    }
    /*
    if (count != 0) {
        printLine(arr,*count,*address);
        *address += *count;
    }
     */
}

int main(int argc, char* argv[]) {
    long address = 0;
    int c;
    unsigned char arr[SIZE];
    int count = 0;
    if (argc > 1) {
        for (int i=1; i < argc; i++) {
            FILE* fp = fopen(argv[1],"r");

            if(!fp) {
                return 1;
            }

            hd(fp,&c,&address,arr,&count);

            fclose(fp);
        }
    } else {
        hd(stdin,&c,&address,arr,&count);
    }
    if (count != 0) {
        printLine(arr,count,address);
        address += count;
    }
    printf("%08lx\n",address);
    return 0;
}
