//
//  searchPagerank.c
//  pagerank
//
//  Created by HongningShangguan on 2018/1/27.
//  Copyright © 2018 HongningShangguan. All rights reserved.
//


#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef struct ranks{//store urls and their rank
    char urlName[10];
    double rank;
}ranks;

typedef struct contains{//connect words with urls
    char word[30];
    ranks *url[100];//which urls contain this word
    int has;//has how many urls
}contains;

typedef struct urlStore{
    char urlNames[100][10];
    int elements;
}urlStore;

int searchPagerank(int argc, char *argv[]){
    if (argc < 1) {
        printf("need at least one word to search.\n");
        return -1;
    }
    FILE *fp;//read from files as always
    char links[300][10];
    double rks[300];
    char urls[300][10];
    int N = 0;
    if ((fp = fopen("pagerankList.txt", "r")) == NULL) {
        printf("File open failed.\n");
    }
    while (fscanf(fp, "%s",links[N]) != EOF) {
        if (links[N][0] == 'u') {
            links[N][strlen(links[N])-1] = '\0';
            strcpy(urls[N], links[N]);
        }else if (links[N][1] == '.'){
            rks[N] = atof(links[N]);
            N++;
        }
    }
    fclose(fp);
    
    ranks *myranks[N];
    int i;
    for (i = 0; i<N; i++) {
        myranks[i] = malloc(sizeof(ranks));
        strcpy( myranks[i]->urlName, urls[i]);
        myranks[i]->rank = rks[i];
    }
    FILE *fp2;
    char link[100];
    int wcount = -1;
    int urlcount = 0;
    if ((fp2 = fopen("invertedIndex.txt", "r")) == NULL) {
        printf("File open failed.\n");
    }
    contains *mycontain[200];
    while (fscanf(fp2, "%s",link) != EOF) {
        if (link[0] == 'u'&&link[1] == 'r'&&link[2] == 'l') {//if start with "url", then it is belong to this word
            for (i = 0; i<N; i++) {//find with node(url) with this name and link to words
                if (strcmp(myranks[i]->urlName, link)==0) {
                    mycontain[wcount]->url[urlcount]  = myranks[i];
                    mycontain[wcount]->has += 1;
                    urlcount ++;
                    break;
                }
            }
        }
        else{//not start with "url", must be new word
            wcount++;
            urlcount = 0;
            mycontain[wcount] = malloc(sizeof(contains));
            strcpy(mycontain[wcount]->word, link);
            mycontain[wcount]->has = 0;
        }
    }
    wcount ++;
    contains *searchWord[argc];
    for (i = 0; i < argc; i++) {//record which word node was been searched
        searchWord[i] = malloc(sizeof(contains));
        int j = 0;
        while (j < wcount) {
            if (strcmp(mycontain[j]->word, argv[i])==0) {
                searchWord[i] = mycontain[j];
                break;
            }
            j++;
        }
    }
    urlStore *mystore[argc];//use to store url by search terms
    char allurls[argc*30][10];
    int myurlcount = 0;
    for (i = 0; i < argc; i++) {
        mystore[i] = malloc(sizeof(urlStore));//initial nodes
        mystore[i]->elements = 0;
    }
    for (i = 0; i < argc; i++) {//store all urls even if duplicate
        int j;
        for (j = 0; j < searchWord[i]->has; j++) {
            strcpy(allurls[myurlcount], searchWord[i]->url[j]->urlName);
            myurlcount++;
        }
    }
    for (i = 0; i < myurlcount; i++) {//divide urls by terms
        int j;
        int flag = 0;
        for (j = 0; j < argc; j++) {//if this url already exist
            int k;
            for (k = 0; k < mystore[j]->elements; k++) {
                if (strcmp(allurls[i], mystore[j]->urlNames[k]) == 0) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {//if this url not exist in any node
            int urlwordcount = 0;
            for (j = 0; j < myurlcount; j++) {
                if (strcmp(allurls[j], allurls[i]) == 0) {
                    urlwordcount++;
                }
            }
            strcpy(mystore[urlwordcount-1]->urlNames[mystore[urlwordcount-1]->elements], allurls[i]);
            mystore[urlwordcount-1]->elements++;
        }
    }
    for (i = 0; i < argc; i++) {//sort each url using bubble sort
        int j;
        for (j = 0; j < mystore[i]->elements; j++) {
            double rankj = 0;
            int k;
            for (k = 0; k < N; k++) {//find rank of this url
                if (strcmp(myranks[k]->urlName, mystore[i]->urlNames[j]) == 0) {
                    rankj = myranks[k]->rank;
                    break;
                }
            }
            for (k = 0; k < mystore[i]->elements; k++) {
                double rankk = 0;
                int l;
                for (l = 0; l < N; l++) {
                    if (strcmp(myranks[l]->urlName, mystore[i]->urlNames[k]) == 0) {
                        rankk = myranks[l]->rank;
                        break;
                    }
                }
                if (rankk < rankj) {//swap if rank is less
                    char *temp = malloc(10*sizeof(char));
                    strcpy(temp, mystore[i]->urlNames[j]);
                    strcpy(mystore[i]->urlNames[j], mystore[i]->urlNames[k]);
                    strcpy(mystore[i]->urlNames[k],temp);
                    free(temp);
                    int m;
                    for (m = 0; m < N; m+=1) {
                        if (strcmp(myranks[m]->urlName, mystore[i]->urlNames[j]) == 0) {
                            rankj = myranks[m]->rank;
                            break;
                        }
                    }
                }
            }
            
        }
    }
    int total = 0;
    for (i = argc-1; i>=0; i--) {//output to stdout
        int j;
        for (j = 0; j < mystore[i]->elements; j++) {
            if (total == 30) {
                return 0;
            }
            fprintf(stdout,"%s\n",mystore[i]->urlNames[j]);
            total ++;
        }
    }
    
    return 0;
}

int main(int argc, char * argv[]) {
    // insert code here...
    searchPagerank(argc - 1 , argv + 1);//pass arguement to local function
    
    return 0;
}

