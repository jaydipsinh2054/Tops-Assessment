#include <iostream>
using namespace std;

int main()
{
    int num, den;

    cout << "Enter Numerator: ";
    cin >> num;

    cout << "Enter Denominator: ";
    cin >> den;

    if (den == 0)
    {
        cout << "Error: Division by Zero Not Allowed!";
    }
    else
    {
        cout << "Result = " << (float)num / den;
    }

    return 0;
}