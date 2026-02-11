#include "allocator.h"
#include <stdint.h>
#include <stdio.h>

//-----------------------------------------------------
// Starting the list of memory available (fake for now, before using Windows calls)
//-----------------------------------------------------
#define HEAP_SIZE (1024 * 1024)
static uint8_t global_memory_pool[HEAP_SIZE];
static size_t heap_offset = 0;

//-----------------------------------------------------
// Starting the linked list of available memory
//-----------------------------------------------------
Block *free_list_head = NULL;

//-----------------------------------------------------
// The search algorithm
//-----------------------------------------------------
Block *find_free_block(size_t size) {
    Block *current = free_list_head;
    while (current != NULL) {
        if (current->size >= size) {
            return current;
        }
        current = current->next;
    }
    return NULL;
}
//-----------------------------------------------------
// Take more memory from pool in case of not enough space (Memory Request)
//-----------------------------------------------------
Block *request_space(size_t size) {
    //Calc to see how much size we want in total
    size_t total_needed = size + sizeof(Block);

    // Safety guard, to check if there is enough left in out 1MB pool
    if (heap_offset + total_needed > HEAP_SIZE) {
        return NULL;
    }

    //Getting address of next block
    Block *block = (Block *) & global_memory_pool[heap_offset];

    //Init metadata
    block->size = size;
    block->is_free = 0;
    block->next = NULL;

    //Move pointer forward
    heap_offset += total_needed;
    return block;
}

//-----------------------------------------------------
// Allocator
//-----------------------------------------------------
void *my_malloc(size_t size) {
    // Sanity check, If nothing is requested nothing is returned
    if (size == 0) {
        return NULL;
    }

    //Finding an available block of memory
    Block *block = find_free_block(size);
    if (block) {
        block->is_free = 0;
        // skip meta data and return the data pointer
        return (block + 1);
    }

    //Block not found? Build a new one
    block = request_space(size);
    if (!block) {
        return NULL;
    }
    // Add this new block to the end of the list
    if (free_list_head == NULL) {
        free_list_head = block;
    }
    else {
        Block *last = free_list_head;
        while (last->next != NULL) {
            last = last->next;
        }
        last->next = block;
    }
    return (block + 1);
}

//-----------------------------------------------------
// Free memory function
//-----------------------------------------------------
void my_free(void *ptr) {
    //If you try free-ing a null pointer, it stops
    if (!ptr) {
        return;
    }
    // We move the pointer back to the meta data then flip the free switch
    // Such that find free block can use it again and over write
    Block *block = (Block *)ptr - 1;
    block->is_free = 1;

}

//-----------------------------------------------------
// Visualising the heap for ease of understanding
//-----------------------------------------------------
void print_heap() {
    Block *current = free_list_head;

    printf("\n\n--- Heap Map ---\n");

    if (current == NULL) {
        printf("[EMPTY HEAP]\n");
    }
    while (current != NULL) {
        printf("[%s | %zu bytes]",
            current->is_free ? "Free" : "Heap",
            current -> size);

        if (current -> next != NULL) {
            printf("--->");
        }
        current = current->next;
    }
    printf("\n----------------\n\n");
}