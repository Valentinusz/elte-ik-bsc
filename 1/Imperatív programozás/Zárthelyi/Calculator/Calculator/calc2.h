#ifndef CALC_H
#define CALC_H
typedef enum STATE {LEFT, LEFT_PLUS, LEFT_TIMES, PLUS_RIGHT, TIMES_RIGHT} State;

typedef struct calculator {
    int state;
    unsigned int memory;
    unsigned int input;
} calculator;

unsigned int digit(calculator* c,int n) {
    c->input = 10*c->input + n;
    if (c->state == LEFT_PLUS) {
        c->state = PLUS_RIGHT;
    } else {
        if (c->state == LEFT_TIMES) {
            c->state = TIMES_RIGHT;
        }
    }
    return c->input;
}

static void PlusTimes(calculator* c) {
    switch (c->state) {
        case LEFT:
            c->memory = c->input;
            break;
        case PLUS_RIGHT:
            c->memory += c->input;
            break;
        case TIMES_RIGHT:
            c->memory *= c->input;
            break;
        default:
            break;
    }
    c-> input = 0;
}

unsigned int plus(calculator* c) {
    PlusTimes(c);
    c->state = LEFT_PLUS;
    return c->memory;
}

unsigned int times(calculator* c) {
    PlusTimes(c);
    c->state = LEFT_TIMES;
    return c->memory;
}

unsigned int reset(calculator* c) {
    c->state = LEFT;
    c->memory = 0;
    c->input = 0;
    return c->input;
}
#endif //CALC_H
