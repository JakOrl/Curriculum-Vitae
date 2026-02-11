# Custom C Memory Allocator (Windows-Compatible)

A lightweight, manual memory management system implemented in C. This project simulates the behavior of `malloc` and `free` by managing a statically allocated memory pool using a linked-list of metadata headers.

## Overview
Most modern systems use `sbrk` or `mmap` to request memory from the OS. This project implements a "Sandbox Heap" using a `uint8_t` buffer, making it fully compatible with Windows (MinGW/Clang) without needing Unix-specific system calls.

This project was built to learn:
- **C language:** Becoming more fimiliar with the C language outside of my college module.
- **Pointer Arithmetic:** Navigating memory addresses manually.
- **Metadata Management:** Using structs to "label" memory chunks.
- **Linked Lists:** Implementing a "First-Fit" search algorithm to find reusable memory.

## 🛠️ How it Works
1. **The Header:** Every time memory is requested, a `Block` struct is prepended to the data. This "label" stores the size of the block, its status (Free/Used), and a pointer to the next block.
2. **First-Fit Search:** The allocator traverses the linked list to find the first available block that fits the requested size.
3. **The Offset:** If no free block is found, the allocator carves new space out of the 1MB pre-allocated pool using a global offset.



## Visualizing the Heap and Testing
The project main shows and proves that the functions work correctly.
The project includes a `print_heap()` utility that provides a visual representation of the memory state in the console:

```
--- Heap Map ---
[Used | 100 bytes] ---> [Free | 200 bytes] ---> [Used | 50 bytes]
----------------