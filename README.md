# Paging/Caching Problem 

The paging/caching problem arises from memory management, between external storage and main memory and between main memory and the cache. The memory is divided into fixed-size blocks called pages. When a process requests access to a page and it is already in the cache, we call it a **hit**. If the requested page is not in the cache, we call it a
**miss** , and one of the pages in cache must be evicted to make room for the requested page. The challenge lies in selecting which page to evict to minimize future page misses.

## Project Overview

This project explores two algorithms to handle this decision-making process:
- **FIFO** "first in first out": Evicts the page that was loaded into the cache first.
- **LFD** "longets forward distance" : Evicts the page that will not be used for the longest time in the future, based on the given request sequence.

The results of the methods will be stored in an object of the class COMP108PagingOutput that has the following attributes:
- hitPattern : stores the hit/ miss pattern e.g [hmhhm]
- hitCount : stores the number of hits
- missCount : stores the number of misses
- cache : stores the final cache content


## File Descriptions

### 1. `COMP108Paging.java`
- **Description**: Contains the core logic for managing the cache and implementing the FIFO and LFD page eviction algorithms, with time complexity analysis for each algorithm.
- **Key Methods**:
  - `fifoEviction()`: Handles page eviction using the FIFO method.
  - `lfdEviction()`: Handles page eviction using the LFD method.
  - Other helper methods


### 2. `COMP108PagingApp.java`
- **Description**: The main application that initializes the cache, runs the page request sequence, and prints the results for both the FIFO and LFD algorithms. "Testing"

### 3. `COMP108PagingOutput.java`
- **Description**: Contains the output formatting and reporting functions, such as printing the cache state after each request and eviction, and providing a summary of the results.

