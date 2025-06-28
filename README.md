# Book Catalog System

## Overview
A book collections manager system with advanced search, filtering, and pagination capabilities.

## Features
- CRUD operations for books  
- Advanced search across multiple fields  
- Filtering by genre, author, publication year  
- Pagination and sorting  
- Data validation  

## API Wrapper Package
The system provides a ready-to-use API wrapper package for easy integration:

```java
// Maven Dependency
<dependency>
    <groupId>learning</groupId>
    <artifactId>bookcatalogsystem</artifactId>
    <version>1.0.0</version>
</dependency>

// Basic Usage
BookCatalogClient client = new BookCatalogClient("http://api.yourdomain.com");
Page<Book> books = client.searchBooks("Spring Boot", 0, 10);
```

## API Endpoints

| Method | Endpoint                          | Description                  |
|--------|-----------------------------------|------------------------------|
| GET    | `/api/books`                      | Get paginated list of books |
| POST   | `/api/books`                      | Add a new book               |
| GET    | `/api/books/search?q={query}`     | Full-text search             |
| GET    | `/api/books/genre/{genre}`        | Filter by genre              |

## Installation

Clone the repository:

```bash
git clone https://github.com/yourusername/book-catalog-system.git
```

Configure PostgreSQL in `application.properties`

Run the application:

```bash
mvnd spring-boot:run
```

## Database Schema

```sql
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    publication_year INT,
    page_count INT,
    created_at TIMESTAMP
);
```
