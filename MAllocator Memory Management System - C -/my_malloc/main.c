#include <stdio.h>
#include "allocator.h"

int main() {
    printf("--- Custom Allocator Test ---\n\n");
    printf("=== Inital State of Heap ===");
    print_heap();

    //Request 100 bytes for Block A

    printf("Allocating Block A (100 bytes)...\n");
    int *ptrA= (int*)my_malloc(100);

    //If failed
    if (ptrA == NULL) {
        printf("Failed to allocated memory to block A...\n");
        return 1;
    }

    //If successful, show address

    printf("Block A address: %p\n\n", (void*)ptrA);

    //request 200 bytes for Block B

    printf("Allocating Block B (200 bytes)...\n");
    int *ptrB= (int*)my_malloc(200);
    if (ptrB == NULL) {
        printf("Failed to allocated memory to block B...\n");
        return 1;
    }
    printf("Block B address: %p\n\n", (void*)ptrB);

    printf("\n\n==== Current State of Heap ====");
    print_heap();

    //Free block A of its memory
    printf("Freeing block A...\n\n");
    my_free(ptrA);

    printf("\n\n ==== State of Heap after freeing Block====");
    print_heap();


    //allocating 100 bytes again, as block 'c'
    printf("Allocating 100 bytes to block c\n");
    int *ptrC= (int*)my_malloc(100);
    printf("Block C address: %p\n\n", (void*)ptrC);

    // Testing if it worked
    if (ptrA == ptrC) {
        printf("Success, Block C reused the space that block A had previously.\n");
        printf("This proves, my_alloc works, and my_free works aswell\n");
    }
    else {
        printf("Something went wrong...\n");
    }

    printf("\n\n==== Final State of Heap ====");
    print_heap();
    getchar();
}