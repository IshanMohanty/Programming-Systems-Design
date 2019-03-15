// Name: ISHAN MOHANTY   
// USC NetID: 4461-3447-18
// CSCI 455 PA5
// Fall 2018


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in header files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
/**
   Node
   
   Struct data structure that forms an entity containing a name-score pair
   as key-value pair and has a pointer next which points to the next node
   to form a linked-list else call also be initialized to "NULL".
   
   It has member functions that behave like constructors for setting up
   information for the data members in the Node structure.
*/
struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};

//Replacing Node * with ListType for ease of programming
typedef Node * ListType;


//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//Inserts name-score pair entity in-front of a linked-list
bool listInsert( ListType & list, const std::string & target, int value);

//Searches name in the linked-list and returns it's respective score if name is present
int * listSearch( ListType & list, const std::string & target );

//Prints all name-score pair entries in the linked-list
void listDisplay(ListType & list);

//Returns the number of entities in the linked-list
int listSize(ListType & list);

//Deletes the name-score pair entity if name is present in the linked list else returns false
bool listRemove(ListType & list, const std::string & target);

// keep the following line at the end of the file
#endif
