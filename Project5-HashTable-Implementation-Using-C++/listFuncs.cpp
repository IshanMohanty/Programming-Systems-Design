// Name: ISHAN MOHANTY
// USC NetID: 4461-3447-18
// CSCI 455 PA5
// Fall 2018

/**
 * Packages used are displayed below
 */
#include <iostream>

#include <cassert>

#include "listFuncs.h"

//namespace used
using namespace std;

/**
   Member function of struct Node used for initializing the
   key-value pair with name-score of the student and
   the next pointer to NULL.
*/
Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

/**
   Member function of struct Node used for initializing the
   key-value pair with name-score of the student and
   the next pointer to point to another given node.
*/
Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

/**
  Insert name and score of the student in-front of the list.
  if the name was already present don't do the insert.
  @param list Linkedlist on which insert operation is performed
  @param target name of student 
  @param value Score of student
  @return isInsertSuccess true if insert operation is successful
                          false if insert operation failed
*/
bool listInsert(ListType & list, const string & target, int value){
   
   bool isInsertSuccess = false;
   
   if( listSearch(list,target) != NULL ){
      isInsertSuccess = false;
   }
   else{
      
      ListType newNode = new Node(target,value,list);
      list = newNode;
      isInsertSuccess = true;
   }
   
   return isInsertSuccess;
   
}


/**
  Lookup is performed for getting the score of a particular
  student. If name is present, score is returned else NULL
  is returned.
  @param list Linkedlist on which search operation is performed
  @param target name of student 
  @return If name found, return score else "NULL"
          
*/
int * listSearch(ListType & list, const string & target){
   
   ListType traverseList = list;
   
   while( traverseList != NULL ){
      if( traverseList->key == target ){
         //returns address of the score of the student
         return &(traverseList->value);
      }
      traverseList = traverseList->next;
   }
   
   return NULL;
   
}


/**
  Displays entries in list in the name-score format.
  @param list Linkedlist on which iteration is performed  
*/
void listDisplay(ListType & list){  
   
   ListType iterate = list;
   
   while( iterate != NULL ){    
      cout<<iterate->key<<" "<<iterate->value<<endl;
      iterate = iterate->next;
   }
   
}

/**
  Calculates the number of entities containing name-score pair.
  @param list Linkedlist on which iteration is performed for counting
  @return count Number of entries
*/
int listSize(ListType & list){  
   
   int count=0;
   ListType walkThrough = list;
   
   while( walkThrough != NULL ){
      count+=1;
      walkThrough = walkThrough->next;
   }
   
   return count;
   
}

/**
  Removes name and score of the student present in the list.
  if the name is not present then the list does not change.
  @param list Linkedlist on which delete operation is performed
  @param target name of student 
  @return Delivers true if entry of name-score pair is deleted 
          else sends back false
          
*/
bool listRemove(ListType & list, const string & target){
   
   /**
      pointers to node for maintaining information 
      essential for deletion
   */
   ListType currentNode = list;
   ListType previousNode = NULL;
   
   //return false if list is empty
   if( currentNode == NULL ){
      return false;
   }
   
   /**
      If the list has only one entry and the given name 
      matches the name in the first entity.
   */
   if( currentNode->key == target ){
      ListType eraseNode = list;
      //list = list->next;
      list = currentNode->next;
      delete eraseNode;
      return true;
   }
   
   /**
      for list with multiple entities find given name and
      remove the matching entity.
   */
   while( currentNode->next != NULL ){
      previousNode = currentNode;
      currentNode = currentNode->next;
      if( currentNode->key == target ){
         previousNode->next = currentNode->next;
         delete currentNode;
         return true;
      }
   }
   
   //If given name is not found in the entire list
   return false;
   
}
         
      
      
   
   
   
   
   
      
   
        
      