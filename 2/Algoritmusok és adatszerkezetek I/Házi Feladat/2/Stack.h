#ifndef STACK_H
#define STACK_H
#include <vector>

class Stack {
private:
    std::vector<char> elements;

public:
    bool isEmpty() const {
        return elements.empty();
    }

    unsigned int getSize() const {
        return elements.size();
    }

    void push(char e) {
        elements.push_back(e);
    }

    char pop() {
        char x = elements.back();
        elements.pop_back();
        return x;
    }
};


#endif //STACK_H
