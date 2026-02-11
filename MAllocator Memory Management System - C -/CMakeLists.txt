cmake_minimum_required(VERSION 4.1)
project(MAllocator_Project C)

set(CMAKE_C_STANDARD 11)

include_directories(my_malloc)

add_executable(MAllocator_Project
        my_malloc/allocator.c
        my_malloc/allocator.h
        my_malloc/main.c)
