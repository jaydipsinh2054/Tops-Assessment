#include <iostream>
using namespace std;

int main()
{
    int choice;

    do
    {
        cout << "\n===== MENU =====";
        cout << "\n1. Start";
        cout << "\n2. About";
        cout << "\n3. Exit";
        cout << "\nEnter Choice: ";
        cin >> choice;

        switch(choice)
        {
            case 1:
                cout << "Program Started";
                break;

            case 2:
                cout << "Menu Driven Application";
                break;

            case 3:
                cout << "Exiting...";
                break;

            default:
                cout << "Invalid Choice";
        }

    } while(choice != 3);

    return 0;
}