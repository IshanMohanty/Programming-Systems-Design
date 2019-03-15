// Name: ISHAN MOHANTY   
// USC NetID: 4461-3447-18
// CSCI 455 PA5
// Fall 2018

// Table.cpp  Table class implementation

/**
 * Packages used are displayed below
 */
#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

//namespace used 
using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


/**
   Constructor to initialize HashTable of constant size
 */
Table::Table() {  
   
   hashSize = HASH_SIZE;
   hashTable = new ListType[hashSize]();
   
}


/**
   Constructor to initialize HashTable of user-given size
   @param hSize User-given hashtable size
 */
Table::Table(unsigned int hSize) {
   
   hashSize = hSize;
   hashTable = new ListType[hashSize]();
   
}


/**
 Searches the value of a given key in the hashtable
 @param key Name of the student in the table
 @return Pointer to the value(score) of the key(name)
*/
int * Table::lookup(const string &key) {
   
   ListType requiredNode = hashTable[hashCode(key)];  
   return listSearch(requiredNode,key);   
   
}


/**
 Removes the given key-value pair present in the hashtable
 @param key Name of the student in the table
 @return if deletion was a success, returns true
         if key was not found, returns false
*/
bool Table::remove(const string &key) {
   
   ListType & requiredNode = hashTable[hashCode(key)];  
   return listRemove(requiredNode,key); 
   
}


/**
 Inserts the given key-value pair present in the hashtable
 @param key Name of the student in the table
 @return if insertion was a success, returns true
         if key was already present, returns false
*/
bool Table::insert(const string &key, int value) {
   
   ListType & requiredNode = hashTable[hashCode(key)]; 
   return listInsert(requiredNode,key,value);
   
}


/**
 Gives the number of entries in the hashtable
 @return Number of entries in the hashtable
*/
int Table::numEntries() const {
   
   int totalEntries = 0;
   for( int i=0 ; i < hashSize ; i++ ){
      totalEntries += listSize(hashTable[i]);
   }
   return totalEntries;   
   
}

/**
   Displays all entries in the hashtable
*/
void Table::printAll() const { 
   
   for( int i=0 ; i < hashSize ; i++ ){
      listDisplay(hashTable[i]);
   }
   
}

/** 
   Displays the hashtable statistics:
   1. number of buckets 
   2. number of entries
   3. number of non-empty buckets 
   4. longest chain
*/
void Table::hashStats(ostream &out) const {
   
    int noEntries = numEntries();
  	int noBuckets = hashSize;
  	int numNonEmpBuckets = 0;
    
    /**
       Calculate longest chain in the linkedlist 
       when chaining takes place in the hashtable.
    */
  	int longestChain = 0;
  	for( int i=0 ; i < hashSize ; i++ ){
  		int chainLength = listSize(hashTable[i]);
  		if( chainLength > longestChain ){
  			longestChain = chainLength;
  		}
  	}
    
    //Calculate the number of non-empty entries in the hashtable
    for( int i=0 ; i < hashSize ; i++ ){
  		if( hashTable[i] != NULL ){
  		   numNonEmpBuckets+=1;
  		}
  	}
    
    //display the hashStats
  	out << "number of buckets: " << noBuckets << endl;
  	out << "number of entries: " << noEntries << endl;
  	out << "number of non-empty buckets: " << numNonEmpBuckets << endl;
    out << "longest chain: " << longestChain << endl;
   
}


// add definitions for your private methods here
