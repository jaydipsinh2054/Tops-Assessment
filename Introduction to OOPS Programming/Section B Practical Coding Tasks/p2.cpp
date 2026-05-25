#include <iostream>
using namespace std;

int main() {
    float screenTime[7];
    float total = 0, average;

    cout << "Enter screen time for 7 days:\n";

    for(int i = 0; i < 7; i++) {
        cout << "Day " << i + 1 << ": ";
        cin >> screenTime[i];

        total += screenTime[i];
    }

    average = total / 7;

    cout << "\nTotal Screen Time: " << total << " hours";
    cout << "\nAverage Screen Time: " << average << " hours";

    if(average > 6) {
        cout << "\nWarning: Screen time exceeds healthy limit!";
    }

    return 0;
}