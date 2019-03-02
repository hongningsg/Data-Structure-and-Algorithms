#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include "listIteratorInt.h"

typedef struct Node {

	int value;
	Node *next;
	Node *prev;
} Node;

typedef struct IteratorIntRep {
	Node *head;
	Node *tail;
	Node *cur;
	int cursor;
  
} IteratorIntRep;

IteratorInt IteratorIntNew(){

	IteratorIntRep *myList;
	myList = malloc(sizeof(IteratorIntRep));
	myList->head = NULL;
	myList->tail = NULL;
	myList->cur = NULL;
	myList->cursor = 0;
	

  return NULL; 
}

void reset(IteratorInt it){

	it->cursor = 0;
	it->cur = NULL;
}


int add(IteratorInt it, int v){
	Node *addNode;
	addNode = malloc(sizeof(Node));
	addNode->value = v;
	addNode->prev = NULL;
	addNode->next = NULL;

	if (it->head == NULL) {//if list has no element
		it->head = addNode;
		it->tail = addNode;
		
	}
	else
	{
		if (it->cursor == 0) { //if cursor at head
			addNode->next = it->head;
			it->head->prev = addNode;
			it->head = addNode;			
		}
		else
		{
			addNode->prev = it->cur;
			it->cur->next = addNode;
			if (it->cur = it->tail) {//if cursor at tail
				it->tail = addNode;
			}
			else
			{
				addNode->next = it->cur->next;
				it->cur->next->prev = addNode;
			}
		}
		
	}
	it->cur = addNode;
	it->cursor += 1;
  return 1;


int hasNext(IteratorInt it){ 
	if ((it->head != it->tail) && (it->cur != NULL)) { 
		if (it->cur != it->tail) {
			return 1;
		}
  }
  return 0; 
}

int hasPrevious(IteratorInt it){

	if (it->cursor != 0) {
		return 1;
  }
  return 0;
}


int *next(IteratorInt it){
	if ((it->cur == NULL) && (it->tail != NULL) && (it->cursor == 0)) {
		it->cur = it->head;
		it->cursor++;
		return &it->cur->value;
	}
	if (hasNext(it)) {
		it->cur = it->cur->next;
		it->cursor++;
		return &(it->cur->value);
  }
  return NULL;
}

int *previous(IteratorInt it){ 
	if (hasPrevious(it)) {
		it->cur = it->cur->prev;
		it->cursor--;
		return &(it->cur->value);
  }
  return NULL; 

}


int deleteElm(IteratorInt it){
	if (hasPrevious(it)) {
		if (it->head == it->tail) {
			it->head = NULL;
			it->tail = NULL;
			it->cursor = 0;
			free(it->cur);
			it->cur = NULL;
			return 1;
		}
		if (it->cur == it->tail) {
			it->tail = it->cur->prev;
			it->tail->next = NULL;
			free(it->cur);
			it->cur = it->tail;
		}
		else if (it->cur == it->head) {
			it->head = it->cur->next;
			it->head->prev = NULL;
			free(it->cur);
			it->cur = NULL;
		}
		else 
		{
			it->cur->next->prev = it->cur->prev;
			it->cur->prev->next = it->cur->next;
			Node *temp = it->cur->prev;
			free(it->cur);
			it->cur = temp;
		}
		it->cursor--;
		return 1;
  }

  
  return 0; 
}


int set(IteratorInt it, int v){
	if (hasPrevious(it)) {
		it->cur->value = v;
		return 1;
	}
  return 0;
}

int *findNext(IteratorInt it, int v){
	int cursorpos = it->cursor;
	Node *temp = it->cur;
	while ((it->cur->value) != v) {
		if (it->cur = it->tail) {
			it->cur = temp;
			it->cursor = cursorpos;
			return NULL;
		}
		it->cursor++;
		it->cur = it->cur->next;

	}
  
  return &(it->cur->value);
}

int *findPrevious(IteratorInt it, int v){
	int cursorpos = it->cursor;
	Node *temp = it->cur;
	while ((it->cur->value) != v) {
		if (it->cur = it->head) {
			it->cur = temp;
			it->cursor = cursorpos;
			return NULL;
		}
		it->cursor--;
		it->cur = it->cur->prev;

	}

	return &(it->cur->value);
}

void freeIt(IteratorInt it){
	it->cur = it->tail;
	while (it->cur != NULL) {
		Node *temp = it->cur->prev;
		free(it->cur);
		it->cur = temp;
	}
	it->cursor = 0;
  return;

}





