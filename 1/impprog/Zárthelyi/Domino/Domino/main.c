#include <stdio.h>
#include <stdlib.h>

typedef struct Domino {
    int left;
    int right;
} Domino_t;

void InitArray(Domino_t** arr, int count) {
    // allocates memory for the array
    for (int i=0; i<count; i++) {
        arr[i] = malloc(sizeof(Domino_t));
        if (!arr[i]) {
            fprintf(stderr,"Memory allocation failed.");
            exit(1);
        }
    }
}

int place(Domino_t* dp, int* sLeft, int* sRight) {
    // attempts to place a domino
    // returns 0 if unsuccessful
    // returns 1 if successful and changes sLeft or sRight
    if (dp->left == *sRight) {
        *sRight = dp->right;
        printf("Added to the right end: %d|%d\n",dp->left,dp->right);
    } else if (dp->right == *sRight) {
        *sRight = dp->left;
        printf("Added to the right end: %d|%d\n",dp->left,dp->right);
    } else if (dp->right == *sLeft) {
        *sLeft = dp->left;
        printf("Added to the left end: %d|%d\n",dp->left,dp->right);
    } else if (dp->left == *sLeft) {
        *sLeft = dp->right;
        printf("Added to the left end: %d|%d\n",dp->left,dp->right);
    } else {
        return 0;
    }
    return 1;
}

void FreeArray(Domino_t** arr, int count) {
    for (int i=0; i<count; i++) {
        free(arr[i]);
    }
    free(arr);
}

int main() {
    FILE* fp = fopen("teszt2.txt","r");
    if (fp) {
        int setLeft;
        fscanf(fp,"%d",&setLeft);

        int setRight;
        fscanf(fp,"%d",&setRight);

        int count;
        fscanf(fp,"%d",&count);

        //printf("%d, %d, %d",setLeft,setRight,count);
        Domino_t** arr = malloc(count*sizeof(Domino_t*));
        if (arr) {
            InitArray(arr,count);
            for (int i=0; i<count; i++) {
                int l;
                int r;

                fscanf(fp,"%d",&l);
                fscanf(fp,"%d",&r);

                arr[i]->left = l;
                arr[i]->right = r;

            }
            fclose(fp);

            int placed = 0;

            printf("Initial domino: %d|%d\n",setLeft,setRight);

            for (int i=0; i<count; i++) {
                for (int j=0; j<count; j++) {
                    if (arr[j] && place(arr[j],&setLeft,&setRight)) {
                        placed++;
                        free(arr[j]);
                        arr[j] = NULL;
                        break;
                    }
                }
            }

            printf("Summary: %d dominos were placed",placed);

            FreeArray(arr,count);

            return 0;
        } else {
            fprintf(stderr,"Memory allocation failed.");
        }
    }
    else {
        fprintf(stderr,"File opening unsuccessful.");
    }
    return 1;
}