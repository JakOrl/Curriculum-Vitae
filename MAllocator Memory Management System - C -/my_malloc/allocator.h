#ifndef ALLOCATOR_H
#define ALLOCATOR_H
#include <stddef.h> // So that size_t is usable

// The interface
typedef struct block {
    size_t size;
    int is_free;

    struct block *next;
}
Block;
extern Block *free_list_head;


// Library Functions
void *my_malloc(size_t size);
void my_free(void *ptr);

// Visualisation of heap
void print_heap();

#endif
