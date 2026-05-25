#include <iostream>
using namespace std;

void swapNumbers(int *a, int *b) {
    int temp;

    temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    int num1, num2;

    cout << "Enter first number: ";
    cin >> num1;

    cout << "Enter second number: ";
    cin >> num2;

    cout << "\nBefore Swapping:";
    cout << "\nNum1 = " << num1;
    cout << "\nNum2 = " << num2;

    swapNumbers(&num1, &num2);

    cout << "\n\nAfter Swapping:";
    cout << "\nNum1 = " << num1;
    cout << "\nNum2 = " << num2;

    return 0;
}