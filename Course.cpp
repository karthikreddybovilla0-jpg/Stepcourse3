#include <iostream>
#include <string>
using namespace std;

class Course {
private:
    string code;
    string title;
    int credits;
    int labCredits;

public:
    Course(string c, string t, int cr, int lcr) {
        code = c;
        title = t;
        credits = cr;
        labCredits = lcr;
    }

    Course(string c, string t, int cr) {
        code = c;
        title = t;
        credits = cr;
        labCredits = 0;
    }

    int totalCredits() {
        return credits + labCredits;
    }
};

int main() {
    Course c1("21CSC201J", "Data Structures", 4);
    Course c2("21CSC205L", "DSA Lab", 3, 1);

    cout << "21CSC201J total credits: " << c1.totalCredits() << endl;
    cout << "21CSC205L total credits: " << c2.totalCredits() << endl;

    return 0;
}