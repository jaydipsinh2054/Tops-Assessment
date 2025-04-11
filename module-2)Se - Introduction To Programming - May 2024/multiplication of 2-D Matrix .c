#include <stdio.h>

int main() {
    int r1, c1, r2, c2,i,j,k;

    // Get dimensions for matrix1
    printf("-------------------------------------------------------");
    printf("\nEnter number of rows for Matrix 1: ");
    scanf("%d", &r1);
    printf("Enter number of columns for Matrix 1: ");
    scanf("%d", &c1);
    // Get dimensions for matrix2
    printf("\nEnter number of rows for Matrix 2: ");
    scanf("%d", &r2);
    printf("Enter number of columns for Matrix 2: ");
    scanf("%d", &c2);

    // Check if multiplication is possible
    if (c1 != r2) {
        printf("Matrix multiplication not possible (columns of Matrix 1 != rows of Matrix 2).\n");
        return 0;
    }

    // Declare matrices
    int matrix1[r1][c1], matrix2[r2][c2], result[r1][c2];

    // Take input for Matrix 1
    printf("-------------------------------------------------------");
    printf("\nEnter elements of Matrix 1:\n");
    for (i = 0; i < r1; i++) {
        for (j = 0; j < c1; j++) {
            printf("matrix1[%d][%d]: ", i, j);
            scanf("%d", &matrix1[i][j]);
        }
    }

    // Take input for Matrix 2
    printf("-------------------------------------------------------");
    printf("\nEnter elements of Matrix 2:\n");
    for (i = 0; i < r2; i++) {
        for (j = 0; j < c2; j++) {
            printf("matrix2[%d][%d]: ", i, j);
            scanf("%d", &matrix2[i][j]);
        }
    }

    // Initialize result matrix to 0
    for (i = 0; i < r1; i++) {
        for (j = 0; j < c2; j++) {
            result[i][j] = 0;
        }
    }

    // Perform matrix multiplication
    for (i = 0; i < r1; i++) {
        for (j = 0; j < c2; j++) {
            for (k = 0; k < c1; k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }

    // Print result matrix
    printf("-------------------------------------------------------");
    printf("\nResultant Matrix:\n");
    for (i = 0; i < r1; i++) {
        for (j = 0; j < c2; j++) {
            printf("%d ", result[i][j]);
        }
        printf("\n");
    }

    return 0;
}

