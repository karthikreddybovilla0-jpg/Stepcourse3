#include <iostream>
#include <string>
using namespace std;

class IdCard {
public:
    string name;
    int booksIssued;

    IdCard(string n, int b) {
        name = n;
        booksIssued = b;
    }
};

int main() {
    IdCard ravi("Ravi", 0);

    IdCard* duplicate = &ravi;

    duplicate->booksIssued = 3;

    IdCard separate("Ravi", 3);

    cout << "Ravi's booksIssued (via first variable): " << ravi.booksIssued << endl;
    cout << "duplicate == ravi: " << (duplicate == &ravi ? "true" : "false") << endl;
    cout << "separate == ravi: " << (&separate == &ravi ? "true" : "false") << endl;

    return 0;
}