#include <iostream>
#include <fstream>
using namespace std;
#include <string>

void frequencyFunc()
{
    
    int frequency[128] = { 0 };
    ifstream inFile;
    char c;

    inFile.open("Marco U.txt");

    c = inFile.get();

    while (c != EOF)
    {
        frequency[c]++;
        c = inFile.get();
    }

    cout << "Letter frequencies: " << endl << endl;

    for (char c = 'A'; c <= 'Z'; c++)
    {
        cout << c << ": " << frequency[c] << endl;

    }

    inFile.close();

}

void decrypt()
{
    char c;
    ifstream inFile;
    ofstream outFile;

    inFile.open("Marco U.txt");
    outFile.open("decrypt.txt");

    c = inFile.get();
    while (c != EOF)
    {

        switch (c) 
        {
        case 'F':
            outFile << 'A';
            break;
        case 'K':
            outFile << 'B';
            break;
        case 'W':
            outFile << 'C';
            break;
        case 'M':
            outFile << 'D';
            break;
        case 'T':
            outFile << 'E';
            break;
        case 'Y':
            outFile << 'F';
            break;
        case 'D':
            outFile << 'G';
            break;
        case 'E':
            outFile << 'H';
            break;
        case 'Z':
            outFile << 'I';
            break;
        case 'U':
            outFile << 'J';
            break;
        case 'L':
            outFile << 'K';
            break;
        case 'R':
            outFile << 'L';
            break;
        case 'P':
            outFile << 'M';
            break;
        case 'O':
            outFile << 'N';
            break;
        case 'Q':
            outFile << 'O';
            break;
        case 'N':
            outFile << 'P';
            break;
        case 'X':
            outFile << 'Q';
            break;
        case 'C':
            outFile << 'R';
            break;
        case 'J':
            outFile << 'S';
            break;
        case 'V':
            outFile << 'T';
            break;
        case 'I':
            outFile << 'U';
            break;
        case 'G':
            outFile << 'V';
            break;
        case 'A':
            outFile << 'W';
            break;
        case 'H':
            outFile << 'X';
            break;
        case 'B':
            outFile << 'Y';
            break;
        case 'S':
            outFile << 'Z';
            break;
        default:
            outFile << c;
        }

        c = inFile.get();

    }

    inFile.close();
    outFile .close();

    cout << endl << "Decryption Complete";

}

int main()
{

    frequencyFunc();

    decrypt();

    return 0;
}