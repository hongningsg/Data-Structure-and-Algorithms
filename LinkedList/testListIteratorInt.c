#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
#include "listIteratorInt.h"

char *myitoa(int *i){
  char *str;
  if(i == NULL){ return "NULL";}
  
  if ((str = malloc(20*sizeof(char))) == NULL) { return "Error";}
  sprintf(str, "%d", *i);
  return str;
}


void adddsp(IteratorInt it, int v){
  int result = add(it , v); 
  printf("add %d, returns %d \n", v, result );
   
}
void nextdsp(IteratorInt it){
  printf("next, returns %s \n", myitoa(next(it)) ) ;   
}
void prevdsp(IteratorInt it){
  printf("previous, returns %s \n", myitoa(previous(it)) );   
}
void deletedsp(IteratorInt it){
  printf("delete, returns %d \n", deleteElm(it)) ;   
}

int main(int argc, char *argv[]) {
	  IteratorInt it = IteratorIntNew();
	  int TestNo = 0;
	  int i = 0;

	  assert(argc > 1);
	  sscanf(argv[1], "%d", &TestNo);

	  if(TestNo <= 3){
	    int a[5] = { 25, 14, 32, 53 , 8  };
	  
	    for(i=0; i<5; i++){
	      adddsp(it, a[i]);
	    }
	  }
	  else {
	    
	  }
	  printf(" --- --- --- --- --- \n" );

	  
	  if(TestNo == 1) { 
	    prevdsp(it);
	    prevdsp(it);
	    prevdsp(it);
	  }
	  else if(TestNo == 2) {
	    prevdsp(it);
	    prevdsp(it);
	    prevdsp(it);   
	    nextdsp(it);
	    nextdsp(it);
	  }
	  else if(TestNo == 3) { 
	    prevdsp(it);
	    prevdsp(it);
	    prevdsp(it);
	    deletedsp(it);
	    nextdsp(it);

	    prevdsp(it);
	    prevdsp(it);
	  }


  	return EXIT_SUCCESS;

}
