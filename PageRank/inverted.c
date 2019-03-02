//
//  inverted.c
//  pagerank
//
//  Created by HongningShangguan on 2018/1/26.
//  Copyright © 2018 HongningShangguan. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

typedef struct URLs{   //used to store urls for each word
    char name[10];
    int label;
    int WordsCount;
    char words[500][30];
}URLs;

void inverted(){
    FILE *fp;
    char links[1000][10];
    int N = 0;
    if ((fp = fopen("collection.txt", "r")) == NULL) { // read from collection.txt
        printf("File open failed.\n");
        return;
    }
    
    while (fscanf(fp, "%s",links[N]) != EOF) {
        N++;
    }
    fclose(fp);
    URLs *myurl[N];
    int j = 0;
    for (j = 0; j < N; j++) {
        myurl[j]=malloc(sizeof(URLs));
        myurl[j]->WordsCount = 0;
        strcpy(myurl[j]->name , links[j]); //stroe names "url**"
        char tempint[10];
        strcpy(tempint, links[j]);
        int strc = 3;
        while (tempint[strc] != '\0') { // remove prefix "url" so that they can be convert to float like "11" instead of "url11"
            tempint[strc - 3] = tempint[strc];
            strc ++;
        }
        tempint[strc - 3] = '\0';
        myurl[j]->label = atoi(tempint); //store them as label in order to sort later

        char filename[100];
        char *path;
        path = "";
        strcpy(filename, path);
        strcat(filename, links[j]);
        char *filetail;
        filetail = ".txt";
        strcat(filename, filetail); // combine strings so that "url11" became "url11.txt"
        FILE *fp2;
        char content[1000][30];
        int x = 0;
        int flag = 0;
        if ((fp2 = fopen(filename, "r")) == NULL) {
            printf("File %s open failed.\n", filename);
        }
        while (fscanf(fp2, "%s",content[x]) != EOF) {
            if (content[x-2][0]=='#'&&content[x-1][0]=='S'&&content[x-1][1]=='e'&&content[x-1][7]=='-'&&content[x-1][8]=='2') { // start from previous's previous is #start and previous is section-2
                flag = 1;
            }
            if (content[x][0]=='#'&&content[x][1]=='e'&&content[x][2]=='n'&&content[x][3]=='d') {
                flag = 0; // end at #end
            }
            if (flag == 1) {
                if (content[x][strlen(content[x])-1] == ',' ||content[x][strlen(content[x])-1] == '.' ||content[x][strlen(content[x])-1] == '?' ||content[x][strlen(content[x])-1] == ';') {
                    content[x][strlen(content[x])-1] = '\0';//remove punctuation marks
                }
                int i;
                for (i = 0; i < strlen(content[x]); i++) {
                    content[x][i] = tolower(content[x][i]);//convert characters into lower case
                }
                
                strcpy(myurl[j]->words[myurl[j]->WordsCount], content[x]);
                myurl[j]->WordsCount++;
                
            }
            x++;
        }
        fclose(fp2);
    }
    
    int nod;
    for (nod = 0; nod < N; nod++) { //store words into structure and label its urls
        int inner;
        for (inner = 0; inner < N; inner++) {
            if (myurl[nod]->label < myurl[inner]->label) {//sort urls by their labels using bubble sort
                URLs *tempNode = malloc(sizeof(URLs));
                tempNode->label = myurl[nod]->label;
                tempNode->WordsCount = myurl[nod]->WordsCount;
                strcpy(tempNode->name, myurl[nod]->name);
                int m;
                for (m = 0; m < myurl[nod]->WordsCount; m++) {//link urls with words by string
                    strcpy(tempNode->words[m], myurl[nod]->words[m]);
                }
                myurl[nod] = myurl[inner];
                myurl[inner] = tempNode;
            }
        }
    }
    
    
    int i,k;
    int allwordcount = 0;
    for (i = 0; i < N; i++) {
        allwordcount += myurl[i]->WordsCount;
    }
    
    char AllWords[allwordcount][30];
    k = 0;
    for (i = 0; i < N; i++) {
        int j;
        for (j = 0; j < myurl[i]->WordsCount; j++) {
            strcpy(AllWords[k], myurl[i]->words[j]);
            
            k++;
        }
    }
    qsort(AllWords, allwordcount, 30, (int(*)(const void*,const void*))strcmp);//sort words by alphabetically order
    k = 1;
    while (k < allwordcount) {//remove duplicates
        if (strcmp(AllWords[k], AllWords[k-1]) == 0) {
            int l = k;
            while (l < allwordcount) {
                strcpy(AllWords[l-1], AllWords[l]);
                l++;
            }
            allwordcount--;
        }
        else{
            k++;
        }
    }

    FILE *wf;
    wf = fopen("invertedIndex.txt", "a");//write into file
    for (k = 0; k < allwordcount; k++) {
        fprintf(wf, "%s ",AllWords[k]);
        char printurls[N][10];
        int wordc = 0;
        for (i = 0; i < N; i++) {
            for (j = 0; j < myurl[i]->WordsCount ; j++) {
                if (strcmp(myurl[i]->words[j] , AllWords[k])==0) {
                    strcpy(printurls[wordc], myurl[i]->name);
                    wordc++;
                    break;
                }
            }
        }
        for (i=0; i<wordc; i++) {
            fprintf(wf, " %s", printurls[i]);
        }
        fprintf(wf, "\n");
    }
    fclose(wf);
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    inverted();
    
    return 0;
}
