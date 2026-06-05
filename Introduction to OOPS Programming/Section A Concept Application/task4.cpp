#include <iostream>
using namespace std;

int main()
{
    float mark1, mark2, mark3, average;

    cout << "Enter 3 Marks: ";
    cin >> mark1 >> mark2 >> mark3;

    average = (mark1 + mark2 + mark3) / 3;

    cout << "Average Marks = " << average;

    return 0;
}