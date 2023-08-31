#ifndef CALC_H
#define CALC_H
typedef enum STATE {LEFT, LEFT_PLUS, LEFT_TIMES, PLUS_RIGHT, TIMES_RIGHT} State;

static int state = LEFT;
static unsigned int memory = 0;
static unsigned int input = 0;

unsigned int digit(int n) {
    input = 10*input + n;
    if (state == LEFT_PLUS) {
        state = PLUS_RIGHT;
    } else {
        if (state == LEFT_TIMES) {
            state = TIMES_RIGHT;
        }
    }
    return input;
}

static void PlusTimes() {
    switch (state) {
        case LEFT:
            memory = input;
            break;
        case PLUS_RIGHT:
            memory += input;
            break;
        case TIMES_RIGHT:
            memory *= input;
            break;
        default:
            break;
    }
    input = 0;
}

unsigned int plus() {
    PlusTimes();
    state = LEFT_PLUS;
    return memory;
}

unsigned int times() {
    PlusTimes();
    state = LEFT_TIMES;
    return memory;
}

unsigned int reset() {
    state = LEFT;
    memory = 0;
    input = 0;
    return input;
}
#endif //CALC_H
