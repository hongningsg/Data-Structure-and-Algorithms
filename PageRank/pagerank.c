//
//  pagerank.c
//  pagerank
//
//  Created by HongningShangguan on 2018/1/23.
//  Copyright © 2018 HongningShangguan. All rights reserved.
//

//#include "pagerank.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct linkinfo{  //structure that store each url node
    char number[10];
    int indegree;
    int outdegree;
    char M[100][10];
    char L[100][10];
    struct linkinfo *Ms[100];
    struct linkinfo *next;
    double rank;
}linkinfo;

typedef struct pages{//link all url nodes
    linkinfo *head;
    linkinfo *tail;
    linkinfo *curr;
}pages;

void sortNodes(pages *p){
    linkinfo *tempNode;
    tempNode = malloc(sizeof(linkinfo));
    tempNode = p->head;
    int count = 0;
    while (tempNode != NULL) {//find how many nodes
        count ++;
        tempNode = tempNode->next;
    }
    int i;
    for (i = 0; i < count; i++) {
        tempNode = p->head;
        linkinfo *prevNode = malloc(sizeof(linkinfo));
        prevNode = NULL;
        while (tempNode != NULL) {//sort nodes by their rank usin bubble sort
            if (tempNode->next != NULL) {
                if (tempNode->next->rank > tempNode->rank) {
                    linkinfo *temp = tempNode->next;
                    tempNode->next = temp->next;
                    temp->next = tempNode;
                    if (tempNode == p->head) {//if head swaped, need a new head
                        p->head = temp;
                    }
                    if (temp == p->tail) {//if tail swaped need a new tail
                        p->tail = tempNode;
                    }
                    if (prevNode != NULL) {
                        prevNode->next = temp;
                    }
                    tempNode = temp;
                }
            }
            
            if (prevNode != NULL) {
                prevNode = prevNode->next;
            }else{
                prevNode = p->head;
            }
            tempNode = tempNode->next;
        }
    }
}

void pagerank(double d, double diffPR, int maxIterations){
    FILE *fp;
    char links[300][10];
    int N = 0;
    if ((fp = fopen("collection.txt", "r")) == NULL) {
        printf("File open failed.\n");
        return;
    }
    
    while (fscanf(fp, "%s",links[N]) != EOF) {//read from file word by word and count how many words
        N++;
    }
    fclose(fp);
    pages *mypages;
    mypages = malloc(sizeof(pages));//initial nodes
    mypages->curr = NULL;
    mypages->head = NULL;
    mypages->tail = NULL;
    linkinfo *newlink[N];
    int j = 0;
    for (j = 0; j < N; j++) {//read from other files using name in collections.txt
        char filename[100];
        char *path;
        path = "";
        strcpy(filename, path);
        strcat(filename, links[j]);
        char *filetail;
        filetail = ".txt";
        strcat(filename, filetail); //combine string to have .txt suffix
        FILE *fp2;
        char content[300][10];
        int x = 0;
        if ((fp2 = fopen(filename, "r")) == NULL) {
            printf("File %s open failed.\n", filename);
            return;
        }

        newlink[j] = malloc(sizeof(linkinfo));
        newlink[j]->indegree = 0;
        newlink[j]->rank = 0.0;
        strcpy(newlink[j]->number , links[j]);
        newlink[j]->outdegree = 0;
        newlink[j]->next = NULL;
        int k = 0;
        for (k = 0; k < 10; k++) {
            strcpy(newlink[j]->L[k],"nn");
            strcpy(newlink[j]->M[k],"nn");
        }
        int index = 0;
        while (fscanf(fp2, "%s",content[x]) != EOF) {
            if (content[x][0]=='u' && content[x][1]=='r' && content[x][2]=='l') { //read words that start with "url" and store into my nodes
                strcpy(newlink[j]->L[index],content[x]); //point to other urls
                index ++;
                newlink[j]->outdegree ++;
            }
            x++;
        }
        if (mypages->head == NULL) { //start a new link
            mypages->head = newlink[j];
            mypages->tail = newlink[j];
            mypages->curr = newlink[j];
        }else{
            mypages->curr->next = newlink[j];
            mypages->tail = newlink[j];
            mypages->curr = newlink[j];
        }
        fclose(fp2);
    }
    int k = 0;
    for (k = 0; k < N; k++) {
        linkinfo *temp = mypages->head;
        int m = 0;
        while (temp!=NULL) {
            int l = 0;
            while (strcmp(temp->L[l],"nn") != 0) { //find how many urls point to this url
                if (strcmp(temp->L[l], links[k]) == 0) {
                    strcpy(newlink[k]->M[m], temp->number);
                    newlink[k]->Ms[m] = temp;
                    newlink[k]->indegree ++;
                    m++;
                }
                l++;
            }
            temp=temp->next;
        }
    }
    
    double P[N];
    int index = 0;
    for (index = 0; index < N; index++) { //initial PR(P0,0) = 1/N
        P[index] = 1.0/N;
        newlink[index]->rank = 1.0/N;
    }
    int iteration = 0;
    double diff = diffPR;

    while (iteration <= maxIterations && diff >= diffPR) {
        iteration++;
        for (index = 0; index < N; index++) {
            double sum = 0.0;
            for (j = 0; j < newlink[index]->indegree; j++) {
                sum += (newlink[index]->Ms[j]->rank)/(newlink[index]->Ms[j]->outdegree);
            }//count sum of PR(Pj,t)/L(pj)
            P[index] = (1.0 - d)/N + d * sum; // calculate PR(t+1)

            sum = 0.0;
            double abs = 0.0;
            int i = 0;
            
            for (i = 0; i < N; i++) {
                abs =P[i] - newlink[i]->rank; //get difference of each node
                if (abs < 0.0) {//get absolute value
                    abs = -abs;
                }
                sum += abs;
            }
           
            diff = sum;
        }
        int i;
        for (i = 0; i < N; i++) {
             newlink[i]->rank = P[i];//change previous data to this iteration data
        }
    }
    
        sortNodes(mypages);
    //write in to files
    FILE *wf;
    wf = fopen("pagerankList.txt", "a");
    
    linkinfo *temp = mypages->head;
    while (temp!=NULL) {
        fprintf(wf,"%s, %d, %.7f\n",temp->number,temp->outdegree, temp->rank);
        temp=temp->next;
    }
    fclose(wf);

}

int main(int argc, const char * argv[]) {
    // insert code here...
    char *ptr1;
    char *ptr2;
    double d = strtod(argv[1],&ptr1);
    double diifR = strtod(argv[2], &ptr2);
    int maxIteration = atoi(argv[3]);
    pagerank(d, diifR, maxIteration);
    
    return 0;
}

