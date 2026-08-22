#include <iostream>
#include <string>
using namespace std;

class PlacementRecord {
public:
    string studentName;
    string company;
    double packageLpa;
    PlacementRecord(string name, string comp, double pkg) {
        studentName = name;
        company = comp;
        packageLpa = pkg;
    }
    void printRecord() {
        cout << studentName << " -> "
             << company << " @ "
             << packageLpa << " LPA" << endl;
    }
};

int main() {
    PlacementRecord records[] = {
        PlacementRecord("Ravi", "TCS", 4.5),
        PlacementRecord("Anitha", "Zoho", 6.2),
        PlacementRecord("Karthik", "Infosys", 4.0)
    };
    for (int i = 0; i < 3; i++) {
        records[i].printRecord();
    }

    return 0;
}