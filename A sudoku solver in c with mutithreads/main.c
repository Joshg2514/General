#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <ctype.h>

int board[9][9];
int rowcheck[9];
int colcheck[9];
int gridcheck[9];

//Function to check if row is correct
void *checkrows(void* args){
    //sets row to true
    int check = 1;
    //rowval is board position
    int rowval = *((int*)args);
    //initializes array for the counter of digits 0 to 9
    int row[10]={0};
    //Counts how many times a number appears in a row
    for(int i = 0; i < 9; i++){
        if(board[rowval][i] != 0){
            row[board[rowval][i]] += 1;
        }
        if(row[board[rowval][i]] >= 2){
            check = 0;
            break;
        }
    }
    //returns if row is valid
    rowcheck[rowval] = check;
    return NULL;
}

//Function to check column
void *checkcols(void* args) {
    int check = 1;
    int colval = *((int*)args);
    int col[10] = {0};

    for(int i = 0; i < 9; i++)
        {
        if(board[i][colval] != 0){
            col[board[i][colval]] += 1;
        }
        if(col[board[i][colval]] >= 2){
            check = 0;
            break;
        }
    }
    colcheck[colval] = check;
    return NULL;
}

//Function to check grid
void *checkgrids(void* args){
    int check = 1;
    int gridval = *((int*)args);
    //initializes array for the counter of digits 0 to 9
    int grid[10] = {0};
    int g_row;
    int g_col;

   //setting starting position on the board of threads with the switch statement below
    switch(gridval) {
      case 0:
         g_row = 0;
         g_col = 0;
         break;
      case 1:
         g_row = 0;
         g_col = 3;
         break;
      case 2:
         g_row = 0;
         g_col = 6;
         break;
      case 3:
         g_row = 3;
         g_col = 0;
         break;
      case 4:
         g_row = 3;
         g_col = 3;
         break;
      case 5:
         g_row = 3;
         g_col = 6;
         break;
      case 6:
         g_row = 6;
         g_col = 0;
         break;
      case 7:
         g_row = 6;
         g_col = 3;
         break;
      case 8:
         g_row = 6;
         g_col = 6;
         break;
    }

    //Counts how many times a number appears in a sub grid
    for(int i = g_row; i < (g_row+3); i++){

            for(int j = g_col; j < (g_col+3); j++){

                //ignoring zeroes
                if(board[i][j] != 0){

                    //Add one to the counter for the digit
                    grid[board[i][j]] += 1;
                }

                //if a digit appears twice then sub grid is unvalid, sets it to false and breaks
                if(grid[board[i][j]] >= 2){
                    check = 0;
                    //Exits the double for loop and goes to the flag i have set
                    goto OUT_OF_LOOP;
                }
            }
    }

    //flag
    OUT_OF_LOOP:

    //returns whether the sub grid is correct(1) or wrong(0)
    gridcheck[gridval] = check;
    return NULL;
}

int main() {

    //Time to read the file
    FILE *file;
    file = fopen("sudokufile.txt", "r");

    for(int i = 0; i < 9; i++) {
        for(int j = 0; j < 9; j++) {
            char c;

            if (fscanf(file, " %c", &c) != 1) { printf("Error\n"); }
            else if (isdigit((unsigned char)c)) {
                board[i][j] = c - '0';
            } else {
                board[i][j] = 0;
            }
        }
    }
    fclose(file);

    //Creating the threads
    pthread_t tid_r[9]; //threads 1-9 rows
    pthread_t tid_c[9]; //thread 10-18 columns
    pthread_t tid_Sg[9]; //threads 19-27 grids

    //constant array used to 'number' each thread
    int Digit[9]= {0, 1, 2, 3, 4, 5, 6, 7, 8};

    //the next section creates threads, then waits for each to finish its process
    //rows
    for (int i = 0; i < 9; i++) {
        pthread_create(&tid_r[i], NULL, checkrows, &Digit[i]);
    }
    for (int i = 0; i < 9; i++){
        pthread_join(tid_r[i], NULL);
    }
    //columns
    for (int i = 0; i < 9; i++) {
        pthread_create(&tid_c[i], NULL, checkcols, &Digit[i]);
    }
    for (int i=0; i < 9; i++){
        pthread_join(tid_c[i], NULL);
    }
    //Sub-grid
    for (int i = 0; i < 9; i++) {
        pthread_create(&tid_Sg[i], NULL, checkgrids, &Digit[i]);
    }
    for (int i=0; i < 9; i++){
        pthread_join(tid_Sg[i], NULL);
    }

    //Checking if rows are Valid
    //prints row results
        for(int i = 0; i < 9; i++) {
            if(rowcheck[i] == 0){
                printf("Thread %d, Row %d invalid\n", i+1, i+1);
            }
            else{
               printf("Thread %d, Row %d valid\n", i+1, i+1);
            }
        }
        printf("\n");

    //Checking if columns are Valid
    //prints col result
        for(int i = 0; i < 9; i++) {
            if(colcheck[i] == 0){
                printf("Thread %d, Column %d, invalid\n", i+10, i+1);
            }
            else{
                printf("Thread %d, Column %d, valid\n", i+10, i + 1);

            }
        }
        printf("\n");

    //Checking if Sub Grids are Valid
    //prints results of grid check
        for(int i = 0; i < 9; i++) {
            if(gridcheck[i] == 0){
                printf("Thread %d, Grid %d invalid\n", i+19, i+1);
            }
            else{
                printf("Thread %d, Grid %d valid\n", i+19, i+1);
            }
        }
        printf("\n");

    return 0;
}
