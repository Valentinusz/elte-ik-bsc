#include <stdio.h>
#include "vector.h"



int main() {

    vector v;

    initialize(&v,5);
    append(&v,3);
    append(&v,9);
    append(&v,8);
    append(&v,1);

    printf("%d\n",retrieve(&v,0));
    printf("%d\n",retrieve(&v,1));
    printf("%d\n",retrieve(&v,2));
    printf("%d\n",retrieve(&v,3));
    printf("\n");

    insert(&v,1,5);

    printf("%d\n",retrieve(&v,0));
    printf("%d\n",retrieve(&v,1));
    printf("%d\n",retrieve(&v,2));
    printf("%d\n",retrieve(&v,3));
    printf("%d\n",retrieve(&v,4));
    printf("\n");

    set_capacity(&v,6);
    safe_append(&v,7);
    safe_append(&v,2);

    printf("%d\n",retrieve(&v,0));
    printf("%d\n",retrieve(&v,1));
    printf("%d\n",retrieve(&v,2));
    printf("%d\n",retrieve(&v,3));
    printf("%d\n",retrieve(&v,4));
    printf("%d\n",retrieve(&v,5));
    printf("%d\n",retrieve(&v,6));
    printf("\n");


    safe_insert(&v,0,0);

    printf("%d\n",retrieve(&v,0));
    printf("%d\n",retrieve(&v,1));
    printf("%d\n",retrieve(&v,2));
    printf("%d\n",retrieve(&v,3));
    printf("%d\n",retrieve(&v,4));
    printf("%d\n",retrieve(&v,5));
    printf("%d\n",retrieve(&v,6));
    printf("%d\n",retrieve(&v,7));
    printf("\n");


    erase(&v,1);

    printf("%d\n",retrieve(&v,0));
    printf("%d\n",retrieve(&v,1));
    printf("%d\n",retrieve(&v,2));
    printf("%d\n",retrieve(&v,3));
    printf("%d\n",retrieve(&v,4));
    printf("%d\n",retrieve(&v,5));
    printf("%d\n",retrieve(&v,6));

    dispose(&v);

    return 0;
}
