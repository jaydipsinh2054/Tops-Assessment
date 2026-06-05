#include <iostream>
using namespace std;

int main()
{
    float basicSalary, overtimePay, bonus, tax, finalSalary;
    int overtimeHours;

    cout << "Enter Basic Salary: ";
    cin >> basicSalary;

    cout << "Enter Overtime Hours: ";
    cin >> overtimeHours;

    overtimePay = overtimeHours * 100;

    if (basicSalary > 50000)
        bonus = 5000;
    else
        bonus = 2000;

    tax = basicSalary * 0.10;

    finalSalary = basicSalary + overtimePay + bonus - tax;

    cout << "\nBonus: " << bonus;
    cout << "\nTax: " << tax;
    cout << "\nOvertime Pay: " << overtimePay;
    cout << "\nFinal Salary: " << finalSalary;

    return 0;
}