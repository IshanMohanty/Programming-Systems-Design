// Name: ISHAN MOHANTY
// USC NetID: 4461-3447-18
// CSCI 455 PA5
// Fall 2018

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */


/**
 * Packages used are displayed below
 */
#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

//namespace used
using namespace std;

//function declaration
//Displays command menu
void displayHelpMenu();

int main(int argc, char * argv[]) {

	// gets the hash table size from the command line

	int hashSize = Table::HASH_SIZE;

	Table * grades;  // Table is dynamically allocated below, so we can call
	// different constructors depending on input from the user.

	if (argc > 1) {
		hashSize = atoi(argv[1]);  // atoi converts c-string to int

		if (hashSize < 1) {
			cout << "Command line argument (hashSize) must be a positive number" 
				<< endl;
			return 1;
		}

		grades = new Table(hashSize);

	}
	else {   // no command line args given -- use default table size
		grades = new Table();
	}


	grades->hashStats(cout);

	// add more code here
	// Reminder: use -> when calling Table methods, since grades is type Table*

	string userCommand;
	string name;
	int score;
	bool shouldRepeat = true;

	while(shouldRepeat){
       
		cout << "cmd> ";
		cin >> userCommand;
       
		if( userCommand == "insert" ){
			cin >> name;
			cin >> score;
			if( grades->insert(name, score) ){
				cout << "Insert operation successful!" << endl;
			}
			else{
				cout << "This name already exists! insert operation failed!" << endl;
			}
		}
       
		else if( userCommand == "change" ){
			cin >> name;
			cin >> score;
			int * scoreUpdate = grades->lookup(name);
			if( scoreUpdate != NULL ){
				*(scoreUpdate) = score; 
				cout << "The score is updated!" << endl;
			}
			else{
				cout << "This name is not found!" << endl;
			}
		}
       
		else if( userCommand == "lookup" ){
			cin >> name;
			int * score = grades->lookup(name);
			if( score == NULL ){
				cout << "This name is not found!" << endl;
			}
			else{
				cout << name << " : " << *(score) << endl;
			}
		}
       
		else if ( userCommand == "remove" ){
			cin >> name;
			if( grades->remove(name) ){
				cout << "Deletion successful!" << endl;
			}
			else{
				cout << "This name is not found!" << endl;
			}
		}
       
		else if ( userCommand == "print" ){
			grades->printAll();
		}
       
		else if ( userCommand == "size" ){
			cout << "Size : " << grades->numEntries() << endl;
		}
       
		else if ( userCommand == "stats" ){
			grades->hashStats(cout);
		}
       
		else if ( userCommand == "help" ){
			displayHelpMenu();
		}
       
		else if ( userCommand == "quit" ){
			shouldRepeat = false;
		}
       
		else {
			cout << "ERROR: invalid command" << endl;
			displayHelpMenu();
		}
       
	}

	return 0;
   
}


//Helper function to print command menu
void displayHelpMenu(){
	cout << "insert name score     ---- Inserts the name and score in the grade table." << endl;
	cout << "change name newscore  ---- Changes the score for the given name." << endl;
	cout << "lookup name           ---- Searches the given name and prints out his or her respective score." << endl;
	cout << "remove name           ---- Deletes the entry for this particular student" << endl;
	cout << "print                 ---- Displays all entries in the name and score format." << endl;
	cout << "size                  ---- Displays the number of entries." << endl;
	cout << "stats                 ---- Displays the statistics about the hash table at this particular point." << endl;
	cout << "help                  ---- Displays a brief command summary." << endl;
	cout << "quit                  ---- Exits the program." << endl;
}
