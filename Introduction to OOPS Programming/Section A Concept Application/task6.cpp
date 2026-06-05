#include <iostream>
using namespace std;

struct Student
{
    string name;
    int marks;
    char grade;
};

int main()
{
    Student s;

    cout << "Enter Student Name: ";
    cin >> s.name;

    cout << "Enter Marks: ";
    cin >> s.marks;

    cout << "Enter Grade: ";
    cin >> s.grade;

    cout << "\nStudent Record";
    cout << "\nName : " << s.name;
    cout << "\nMarks : " << s.marks;
    cout << "\nGrade : " << s.grade;

    return 0;
}