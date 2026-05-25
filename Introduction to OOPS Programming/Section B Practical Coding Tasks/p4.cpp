#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
    string goal;

    // Writing to file
    ofstream outFile("goals.txt", ios::app);

    cout << "Enter your daily goal: ";
    getline(cin, goal);

    outFile << goal << endl;
    outFile.close();

    // Reading from file
    ifstream inFile("goals.txt");

    cout << "\nSaved Goals:\n";

    while(getline(inFile, goal)) {
        cout << "- " << goal << endl;
    }

    inFile.close();

    return 0;
}