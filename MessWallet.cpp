#include <iostream>
using namespace std;

class MessWallet {
private:
    double balance;

public:
    MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            cout << "Warning: Negative opening balance. Starting with 0." << endl;
            balance = 0;
        } else {
            balance = openingBalance;
        }
    }

    void topUp(double amount) {
        if (amount <= 0) {
            cout << "Top-up rejected: amount must be positive" << endl;
        } else {
            balance += amount;
            cout << "Balance after top-up: " << balance << endl;
        }
    }

    void deduct(double amount) {
        if (amount > balance) {
            cout << "Deduct rejected: insufficient balance" << endl;
        } else if (amount <= 0) {
            cout << "Deduction rejected: amount must be positive" << endl;
        } else {
            balance -= amount;
        }
    }

    double getBalance() {
        return balance;
    }
};

int main() {
    MessWallet wallet(500);

    wallet.topUp(200);
    wallet.deduct(1000);

    cout << "Final balance: " << wallet.getBalance() << endl;

    return 0;
}