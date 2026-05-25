#include <iostream>
#include <fstream>
using namespace std;

int main() {

    int choice, day;
    float hours, total = 0, average;
    float studyHours[7];

    do {
        cout << "\n===== Student Productivity Tracker =====";
        cout << "\n1. Log Daily Study Hours";
        cout << "\n2. Generate Weekly Report";
        cout << "\n3. Exit";
        cout << "\nEnter your choice: ";
        cin >> choice;

        switch(choice) {

            case 1: {
                ofstream outFile("studyhours.txt");

                for(day = 0; day < 7; day++) {
                    cout << "Enter study hours for Day "
                         << day + 1 << ": ";
                    cin >> studyHours[day];

                    outFile << studyHours[day] << endl;
                }

                outFile.close();

                cout << "\nStudy hours saved successfully!\n";
                break;
            }

            case 2: {
                ifstream inFile("studyhours.txt");

                if(!inFile) {
                    cout << "\nNo data found!";
                    break;
                }

                total = 0;

                cout << "\nWeekly Study Report:\n";

                for(day = 0; day < 7; day++) {
                    inFile >> studyHours[day];

                    cout << "Day " << day + 1
                         << ": " << studyHours[day]
                         << " hours\n";

                    total += studyHours[day];
                }

                average = total / 7;

                cout << "\nTotal Study Hours: "
                     << total;

                cout << "\nAverage Study Hours: "
                     << average;

                if(average >= 5) {
                    cout << "\nExcellent Productivity!";
                }
                else {
                    cout << "\nNeed Improvement!";
                }

                inFile.close();
                break;
            }

            case 3:
                cout << "\nExiting Program...";
                break;

            default:
                cout << "\nInvalid Choice!";
        }

    } while(choice != 3);

    return 0;
}