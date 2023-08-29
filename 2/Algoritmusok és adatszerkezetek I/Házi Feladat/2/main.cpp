#include <iostream>
#include "Stack.h"

using namespace std;

bool isMirrored(string &str) {
    Stack stack;
    for (int i = 0; i < str.size(); ++i) {
        if (str[i] == '#') {
            for (int j = i+1; j <= i+stack.getSize(); ++j) {
                if (stack.pop() != str[j]) {
                    return false;
                } else {
                    i++;
                }
            }
        } else {
            stack.push(str[i]);
        }
    }

    /*
    cout << "(";

    for (int i = 0; i < stack.getSize(); ++i) {
        cout << stack.elements[i];
    }

    cout << ")" << endl;
     */

    return stack.isEmpty();
}

int main() {


    string tests[] = {"#","####","abc#cba","##a#aabc#cba","abc","abc#cb","abc#cbaa#aa","ab#bac##c"};

    for (auto & test : tests) {
        cout << isMirrored(test) << endl;
    }

    return 0;
}
