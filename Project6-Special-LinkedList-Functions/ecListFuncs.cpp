/*  Name: ISHAN MOHANTY
 *  USC NetID: imohanty
 *  CS 455 Fall 2018
 *
 *  See ecListFuncs.h for specification of each function.
 *
 *  NOTE: remove the print statements below as you implement each function
 *  or you will receive no credit for that function.
 *
 */

#include <iostream>
#include <vector>

#include "ecListFuncs.h"

using namespace std;


ListType vectorToList(const vector<int> & nums) {
   
   if( nums.empty() ){
      return NULL;
   }
   
   if( nums.size() == 1 ){
      ListType builtList = new Node( nums[0] );
      return builtList;
   }
   
   ListType newNode = new Node( nums[0] );
   ListType head = newNode;
   ListType traverse = newNode;
   
   for( unsigned int i=1 ; i < nums.size() ; i++ ){
      
      ListType newNode = new Node( nums[i] );
      traverse->next = newNode;
      traverse = newNode;
      
   }
   
   ListType builtList = head;
   
   return builtList; 
   
}


int countRuns(ListType list) {

   if( list == NULL ){
      return 0;
   }
   
   ListType previousNode = NULL;
   ListType currentNode = list;
   ListType nextNode = NULL;
   
   //One node Case
   if( currentNode->next == NULL ){
      return 0;
   }
   
   int count = 0;
   nextNode = currentNode->next;
   
   //Case where in there are more than one node
   while( currentNode != NULL ){
      
      
      if( currentNode->data == nextNode->data ){
         
         if( previousNode == NULL || previousNode->data != nextNode->data ){
            count++;
         }
         
      }
      
      previousNode = currentNode;
      currentNode = nextNode;
      
      if( currentNode->next == NULL ){
         break;
      }
      else{
         nextNode = currentNode->next;
      }
      
   }
     
   return count;  

}


ListType reverse(ListType list) {
   
   if( list == NULL ){
      return NULL;
   }
   
   //Form a newly copied list from original list
   ListType traverse = list;
   ListType newNode = new Node(traverse->data);
   ListType headNewNode = newNode;
   traverse = traverse->next;
   ListType traverseNew = newNode;
   
   while( traverse != NULL ){
      ListType newNode = new Node(traverse->data);
      traverseNew->next = newNode;
      traverseNew = newNode;
      traverse = traverse->next;
   }
   
   ListType finalCopiedList = headNewNode;
      
   //Reverse newly copied linked list
   ListType previousNode = NULL;
   ListType currentNode = finalCopiedList;
   ListType nextNode = NULL;
   
   while( currentNode != NULL ){
      
      nextNode =  currentNode->next;
      currentNode->next = previousNode;
      
      previousNode = currentNode;
      currentNode = nextNode;
      
   }
   
   ListType reversedCopiedList = finalCopiedList;
   reversedCopiedList = previousNode;
        
   return reversedCopiedList;

}


void removeMiddle(ListType &list) {
   
   if( list == NULL ){
      return;
   }
   
   ListType traverse = list;
   
   //Calculate number of nodes
   int numNodes = 0;
   while( traverse != NULL ){
      numNodes++;
      traverse = traverse->next;
   }
   
   //Calculate Middle Element
   int middleElement = 0;
   if( numNodes%2 != 0 ){
      middleElement = (int)( (numNodes/2)  + 1 ); 
   }     
   else{
      middleElement = numNodes/2;
   }
   
   //Check for one node case and remove middle
   if( numNodes == 1 ){
        ListType eraseNode = list;
		list = NULL;
		delete eraseNode;
		return;
   }
   
   //Check for two node case and remove middle
   if( numNodes == 2 ){
      ListType eraseNode = list;
      list = list->next;
      delete eraseNode;
      return;
   }
   
   //Check for number nodes greater than 2 and delete middle
   traverse = list;
   
   int nodeCount = 1;
   
   while( nodeCount < (middleElement-1) ){
      traverse = traverse->next;
      nodeCount++;
   }
   
   ListType eraseNode = traverse->next;
   traverse->next = traverse->next->next;
   delete eraseNode;   
   
}


void split(ListType &list, int splitVal, ListType &a, ListType &b) {
     
   if( list == NULL ){
      return;
   }
   
   ListType traverse = list;
   
   //Calculate number of Nodes
   int numNodes = 0;
   while( traverse != NULL ){
      numNodes++;
      traverse = traverse->next;
   }
   
   traverse = list;
   
   //Check for node case and perform split
   if( traverse->next == NULL ){
      
      if( traverse->data == splitVal ){
         a = NULL;
         b = NULL;
         delete list;
         list = NULL;
      }
      else{
         a = list;
         b = NULL;
         list = NULL;
      } 
      
      return;  
      
   }
   
   //Split on the list having 2 or more nodes
   a = list;
   int count = 1;
   ListType previousNode = NULL;
   ListType nextNode = traverse->next;
   bool isValueFound = false;
    
   while( traverse != NULL ){
      
      if( traverse->data == splitVal ){   
         
         if( count == 1 ){
            a = NULL;
            b = traverse->next;
            delete traverse;
            traverse = NULL;
            list = NULL;
         }     
         else{
            b = nextNode;
            previousNode->next = NULL;
            delete traverse;
            traverse = NULL;
            list = NULL;
         }   
         
         isValueFound = true;
         break;  
         
      }
      
      else{
         
         if( count == numNodes ){
            break;
         }
         previousNode = traverse;        
         traverse = traverse->next;
         nextNode = nextNode->next;
               
      }
      
      count++;  
      
   }
   
   //Split value is not found in list, take special action
   if( isValueFound == false ){      
      a = list;
      b = NULL;
      list = NULL;      
   }
   
}

