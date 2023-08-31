#ifndef VECTOR_H
#define VECTOR_H

#include <stdlib.h>
#define ELEM_T int


typedef struct Vector {
    unsigned int capacity;
    unsigned int current_size;
    ELEM_T* elements;
} vector;

int initialize(vector* v, int capacity) {
    if (capacity > 0) {
        v->elements = malloc(capacity* sizeof(int));
        if (v->elements) {
            v->capacity = capacity;
            v->current_size = 0;
            return 1;
        }
    }
    return 0;
}

void dispose(vector* v) {
    free(v->elements);
    v->capacity = 0;
    v->current_size = 0;
}

int append(vector* v, ELEM_T e) {
    if (v->capacity > v->current_size) {
        v->elements[v->current_size] = e;
        v->current_size++;
        return 1;
    }
    return 0;
}

ELEM_T retrieve(vector* v,unsigned int index) {
    return v->elements[index];
}

int insert(vector* v, int i, ELEM_T e) {
    if (v->capacity > v->current_size && i <= v->current_size && i >= 0) {
        for (unsigned int j=v->current_size; j>i; j--) {
            v->elements[j] = v->elements[j-1];
        }
        v->elements[i] = e;
        v->current_size++;
        return 1;
    }
    return 0;
}

int erase(vector* v, int i) {
    if (i >= 0 && i < v->current_size) {
        for (int j=i; j<v->current_size; j++) {
            v->elements[j] = v->elements[j+1];
        }
        v->current_size--;
        return 1;
    }
    return 0;
}

static void copyElements(vector* v, ELEM_T* new) {
    for (int i=0; i < v->current_size; i++) {
        new[i] = v->elements[i];
    }
}

int set_capacity(vector* v, unsigned int c) {
    if (c >= v->capacity) {
        ELEM_T* new = malloc(c* sizeof(int));
        copyElements(v,new);
        v->capacity = c;
        v->elements = new;
        return 1;
    }
    return 0;
}

int safe_append(vector* v, ELEM_T e) {
    if(v->current_size == v->capacity) {
        if (set_capacity(v,(v->capacity)*2)) {
            if(append(v,e)){
                return 1;
            }
        }
    }
    else if (v->current_size < v->capacity) {
        if(append(v,e)) {
            return 1;
        }
    }
    return 0;
}

int safe_insert(vector* v, int i, ELEM_T e) {
    if(v->current_size == v->capacity) {
        if (set_capacity(v,(v->capacity)*2)) {
            if(insert(v,i,e)){
                return 1;
            }
        }
    }
    else if (v->current_size < v->capacity) {
        if(insert(v,i,e)) {
            return 1;
        }
    }
    return 0;
}



#endif //VECTOR_H