# Library Management System

This is a simple and user-friendly web application built using Java and Spring Boot for managing a library with books and authors. The application allows users to perform the following operations:

1. Add a new book to the library.
2. View a list of all books.
3. Update details of an existing book.
4. Delete a book from the library.
5. Filter a list of books by title or author.
6. Add a new author.
7. Delete an author and their books.
8. Export a list of books to a CSV file.

## Setup and Running the Application

### Prerequisites

- Java 8 or higher
- Maven

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/library-management.git
   cd library-management
   
2. Update the application.properties file to configure the database. Here, I use an in-memory H2 database for simplicity:
   
   ```bash
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.datasource.initialize=true
    spring.jpa.hibernate.ddl-auto=none

3. Run the application using Maven:
   ```bash
   mvn spring-boot:run

4. Open your web browser and navigate to http://localhost:8080 to access the application.

## Application Features
### Adding a New Book
#### Endpoint: /books/new
Click on "Add New Book".
Fill in the book name and select an author from the dropdown menu.
Click "Save" to add the book to the library.
### Viewing All Books
#### Endpoint: /books
Navigate to the homepage.
All books in the library will be listed in a table.
### Updating an Existing Book
#### Endpoint: /books/edit/{id}
Click "Edit" next to the book you want to update.
Modify the book details and click "Save".
### Deleting a Book
#### Endpoint: /books/delete/{id}
Click "Delete" next to the book you want to remove.
Confirm the deletion if prompted.
### Filtering Books by Title or Author
#### Endpoint: /books/search
Use the search form on the homepage.
Enter a title or author name to filter the book list.
### Adding a New Author
#### Endpoint: /authors
Navigate to the authors page by clicking "Authors".
Click "Add New Author".
Fill in the author details and click "Save".
### Deleting an Author
#### Endpoint: /authors/delete/{id}
Click "Delete" next to the author you want to remove.
Confirm the deletion if prompted.
### Exporting Books to CSV
#### Endpoint: /books/export
On the homepage, click "Export Books to CSV".
The browser will download a CSV file containing all books.
## Example Test Cases
### Test Case 1: Adding a New Book
Navigate to the homepage.
Click "Add New Book".
Enter "Test Book" as the title.
Select "Author 1" from the dropdown.
Click "Save".
Verify that "Test Book" appears in the book list.
### Test Case 2: Updating a Book
Navigate to the homepage.
Click "Edit" next to "Test Book".
Change the title to "Updated Test Book".
Click "Save".
Verify that "Updated Test Book" appears in the book list.
### Test Case 3: Deleting a Book
Navigate to the homepage.
Click "Delete" next to "Updated Test Book".
Verify that "Updated Test Book" no longer appears in the book list.
### Test Case 4: Exporting Books to CSV
Navigate to the homepage.
Click "Export Books to CSV".
Verify that a CSV file is downloaded and contains the correct book data.
## Additional Notes
The application uses Thymeleaf for rendering HTML templates.
The H2 database is used for simplicity and is configured to run schema and data SQL scripts at startup.
Make sure to update the application.properties file according to your environment if you are using a different database.
