## Implementation of classes, objects, encapsulation and class relationships
- Private attributes, getter setters, public methods, constructors have been used in classes.
- Classes:
    - four classes have been used in this code
        - Book
        - Member
        - Librarian
        - App (Library Class)
- Objects:
    - objects have been created according to its use
    * for example: in line 437, a librarian object is being created 
    ```java
    Librarian librarian = new Librarian();
    ```
- Encapsulation:
    - Used in each class Book, Member, Librarian, App
    * for example: attributes of class Book are encapsulated
    ```java
    class Member {
    private String name;
    private int age;
    private static int memberId = 0;
    private int Id;
    private Long phoneno;
    private List<Book> booksIssued;
    private int fine;
    }
    ```
- Class Relationships:
    - Composition:
        - The librarian object created in like 437 is passed in the method 'librianMode' (line 339) and 'methodMode'(line 383)

    - Association:
        - The Member class has a List Book booksIssued attribute, which represents the books that a member has issued. This is association between the Member class and the Book class.
        - The Librarian class maintains a list of Member objects using the List Member members attribute. This is an association between the Librarian class and the Member class.
        - In the main method, the program allows the user to enter as either a librarian or a member. Depending on the choice, it interacts with the Librarian or Member class. 

    - Dependency:
        - The calculate fine method in the member class uses dateIssue as a parameter which is an attribute of the book class.
    

## Explanation of the code
The code is compiled using Maven and includes a pom.xml file and a src folder containing the App.java file. 

When the code is run, it gives 3 options to the user:
- Enter as Librarian
- Enter as Member
- Exit

The code implements input validation to handle incorrect or invalid user inputs, ensures proper error handling, and displays user-friendly error messages.

### When entering as Librarian
- provides options:
    - Register a member: Enter name, age and phone number 
        - Generates member Id starting from 0
        - Handles edges cases
            - Phone number should be unique
            - Phone number cannot have more than or less than 10 digits.
    - Remove a member: Enter name, phone number
        - As phone number is unique, the method uses phone number to identify and remove the member.
    - Add a book: Enter title, author, number of copies.
        - Generates member Id starting from 0
    - Remove a book: Enter book Id
    - View all members along with their books and fines to be paid: Display their member Id, name, age, and phone number, fine and list of books.

    **Note** : Fine displayed would be at the instant the 5th option of view all members is called ( would be different from the one when the book would be returned )
    - View all books: Displays title, author and bookId 
    - Back: returns to the previous options

### When entering as Member
- Asks to input name and phone number
- Checks if the member is registered using phone number 
- Then provides options:
    - List Available Books: Shows all the book available in the library
    - List My Books: Shows all the issued books.
        - Member cannot issue more than 2 books
    - Issue book: Shows all the available books and asks to enter book Id and name
        - Can only issue book if the fine is 0 and maximum 2 books can be issued. If there is fine, it prompts the member to first pay the dues.
    - Return book: Shows the list of issued books. Enter book Id. It calculates the fine and shows the amount charged.

    **Note** : The fine would be calculated only when a person has returned book(s); otherwise, the pay fine function would display zero only.
    - Pay Fine: To pay the fine calculated after returning the book(s) ( would display 0 fine unless return book function is called )
    - Back: returns to the previous option

### Exit
- Terminates the code




