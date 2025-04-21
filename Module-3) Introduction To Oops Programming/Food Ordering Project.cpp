#include <iostream>
#include <string>
using namespace std;

// FoodItem class (OOP Concept)
class FoodItem {
public:
    int id;
    string name;
    float price;

    FoodItem() {}

    FoodItem(int i, string n, float p) {
        id = i;
        name = n;
        price = p;
    }

    void displayItem() {
        cout << id << "\t"<<name << "\t\t"<<price << endl;
    }
};

// Function to display menu
void displayMenu(FoodItem menu[], int size) {
    cout << "\n--------- Menu ---------\n";
    cout << "ID" << "\tItem" << "\t\tPrice\n";
    cout << "------------------------\n";
    for (int i = 0; i < size; i++) {
        menu[i].displayItem();
    }
}

// Function to calculate bill
float calculateBill(FoodItem item, int quantity) {
    return item.price * quantity;
}

int main() {
    string customerName;
    cout << "Enter Customer Name: ";
    getline(cin, customerName);

    // Initialize menu items
    FoodItem menu[5] = {
        FoodItem(1, "Pizza", 199.0),
        FoodItem(2, "Burger", 99.0),
        FoodItem(3, "Pasta", 100.0),
        FoodItem(4, "VadaPav", 30.0),
        FoodItem(5, "Coke", 20.0)
    };

    int choice, quantity;
    char continueOrder;
    float totalBill = 0.0;

    do {
        displayMenu(menu, 5);

        cout << "\nEnter the ID of the item you want to order: ";
        cin >> choice;

        // Input validation
        if (choice < 1 || choice > 5) {
            cout << "Invalid choice. Please try again.\n";
            continue;
        }

        cout << "Enter quantity: ";
        cin >> quantity;

        if (quantity <= 0) {
            cout << "Invalid quantity. Please try again.\n";
            continue;
        }

        float bill = calculateBill(menu[choice - 1], quantity);
        totalBill += bill;

        cout << "\nYou ordered " << quantity << " x " << menu[choice - 1].name << " = ?" << bill << endl;

        cout << "Do you want to order more? (y/n): ";
        cin >> continueOrder;

    } while (continueOrder == 'y' || continueOrder == 'Y');

    cout << "\n========= Final Bill =========\n";
    cout << "Customer Name: " << customerName << endl;
    cout << "Your Order Will Be Delivered in 40 Minutes."<< endl;
    cout << "Total Amount: ?" << totalBill << endl;
    cout << "Thank you for your order!\n";
    cout << "==============================\n";

    return 0;
}

