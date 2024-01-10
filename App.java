package com.nandini;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.time.Duration;
import java.time.Instant;

class Book {
    private String title;
    private String author;
    private int copies;
    private int copiesAvailable;
    private int bookId;
    private Instant dateIssue;
    private Instant dateReturn;
    public Book(String title, String author, int bookId) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copies;
        this.bookId = bookId;
        this.dateIssue = null;
        this.dateReturn = null;
    }
    public Instant getdateIssue() {
        return dateIssue;
    }
    public void setdateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
    }
    public Instant getdateReturn() {
        return dateReturn;
    }
     public void setdateReturn(Instant dateReturn) {
        this.dateReturn = dateReturn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getCopiesAvailable() {
        return copiesAvailable;
    }
    public int getBookID() {
        return bookId;
    }
}

class Member {
    private String name;
    private int age;
    private static int memberId = 0;
    private int Id;
    private Long phoneno;
    private List<Book> booksIssued;
    private int fine;

    public Member(String name, int age, Long phoneno) {
        this.name = name;
        this.age = age;
        this.Id = memberId++;
        this.phoneno = phoneno;
        booksIssued = new ArrayList<>();
        fine=0;
    }
    public int getFine(){
        return fine;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return Id;
    }
    public long getPhoneNo() {
        return phoneno;
    }
    public List<Book> getbooksIssued(){
        return booksIssued;

    }
    public void booksIssued(){
        for (Book issuedBook : booksIssued) {
            System.out.println("Title: " + issuedBook.getTitle());
            System.out.println("Author: " + issuedBook.getAuthor());
            System.out.println("Book ID: " + issuedBook.getBookID());
        }
    }
    public void bookIssue(List<Book> books){
        if (booksIssued.size() >= 2) {
            System.out.println("You have already issued 2 books. Return one book to issue another.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Book ID");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Book Title: ");
        String title=scanner.next();
        boolean isFound = false;
        Book found = null;
        for (Book book : books) {
            if (book.getBookID() == bookId) {
                book.setdateIssue(Instant.now());
                isFound = true;
                found = book;
                booksIssued.add(book);
                int copy = book.getCopiesAvailable();
                copy--;
                System.out.println("Book issued succcessfully!");
                break;
            }
        }
        if (isFound) {
            books.remove(found);
        }else {
            System.out.println("Book not found!");
        }
    }

    public void bookReturn(List<Book> books){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Book ID");
        int bookId = scanner.nextInt();
        boolean isFound = false;
        Book found = null;
        for (Book book: booksIssued){
            if(book.getBookID()==bookId){
                isFound = true;
                booksIssued.remove(book);
                books.add(book);
                found = book;
                int amount = calculateFine(book.getdateIssue());
                fine += amount;
                System.out.print("Book returned successfully!");
                
                break;
            }
        }
        if (isFound) {
            booksIssued.remove(found);
                books.add(found);
        }else{
                System.out.println("Book not found!");
        }
    }
    public int calculateFine(Instant dateIssue) {
        if (dateIssue == null) {
            System.out.println("Book issue date is missing.");
            return 0;
        }
        Instant timeNow = Instant.now();
        long days = Duration.between(dateIssue, timeNow).toSeconds(); 
        if (days > 10) {
            long fine = (days - 10) * 3; 
            System.out.println("Fine: " + fine);
            return (int) fine;
        } else {
            System.out.println("No fine.");
            return 0;
        }
    }
    public int calculateFineForIssuedBooks(List<Book> issuedBooks) {
        int totalFine = 0;
        Instant timeNow = Instant.now();
        for (Book book : issuedBooks) {
            Instant dateIssue = book.getdateIssue();
            if (dateIssue != null) {
                long days = Duration.between(dateIssue, timeNow).toSeconds();
                if (days > 10) {
                    totalFine += (int) ((days - 10) * 3);
                }
            }
        }
        return totalFine;
    }

    
    public void payDue(int amount){
        System.out.println("You had fine: "+ amount);
        fine -= amount;
        System.out.println("Fine paid successfully!");
    }
}

class Librarian {
   private List<Member> members;
   public static List<Book> books;
   private List<Book> allBooks;
   private int bookIdnumber=0;
   public Librarian() {
        members = new ArrayList<>();
        books = new ArrayList<>();
        allBooks = new ArrayList<>();
    }
    private int getbookIdnumber(){
        return bookIdnumber++;
   }
    public List<Member> getMembers() {
        return members;
    }
    public List<Book> getBooks() {
        return books;
    }

    public void bookAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Name:");
        String title = scanner.nextLine();
        System.out.println("Enter the name of the Author:");
        String author = scanner.nextLine();
        System.out.println("Enter total number of copies:");
        int copies = scanner.nextInt();
        if(copies==0){
            System.out.println("Number of copies cannot be zero");
        }
        else{
            for(int i = 0; i < copies; i++){
                int bookId = getbookIdnumber();
                Book bookNew = new Book(title, author, bookId);
                books.add(bookNew);
                allBooks.add(bookNew);
            }
        }System.out.println("Book added!");
        
    }
    public void bookRemove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bookId = Integer.parseInt(scanner.next());
        boolean isFound = false;

        for (Book book : books) {
            if (book.getBookID() == bookId) {
                books.remove(book);
                isFound = true;
                System.out.println("Book removed succcessfully!");
                break;
            }
        }
            if (!isFound) {
                System.out.println("Book not found!");
        }
    }
    public void bookView() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
        } else {
            for (Book book : allBooks) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("BookId: " + book.getBookID());
            }
        }
    }
    public void bookAView() {
        if (books.isEmpty()) {
            System.out.println("No books available!");
        } else {
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("BookId: " + book.getBookID());
            }
        }
    }
    public void memberView(){ 
        if(members.isEmpty()){
            System.out.println("No Members Available");
        }
        else{
            for (Member member : members) {
                System.out.println("Name: " + member.getName());
                System.out.println("Age: " + member.getAge());
                System.out.println("Phone Number: " + member.getPhoneNo());
                List<Book> issuedBooks = member.getbooksIssued();
                if (!issuedBooks.isEmpty()) {
                    System.out.println("Books Issued:");
                    for (Book issuedBook : issuedBooks) {
                        System.out.println("Title: " + issuedBook.getTitle());
                        System.out.println("Author: " + issuedBook.getAuthor());
                        System.out.println("Book ID: " + issuedBook.getBookID());
                    }
                } else {
                    System.out.println("No books issued by this member.");
                }
                int fine = member.calculateFineForIssuedBooks(issuedBooks);
                if (fine > 0) {
                    System.out.println("Fine due: " + fine);
                } else {
                    System.out.println("No fine.");
                }    
            }
        }    
    }
    public void memberRemove(long phoneno){
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getPhoneNo() == phoneno) {
                Member removedMember = members.remove(i);
                System.out.println("Member Successfully Removed With Member ID: " + removedMember.getId());
                break;}
        }
    }
    public boolean checkPhoneno(long phoneno) {
        for (Member member : members) {
            if (member.getPhoneNo() == phoneno){
                return true; 
            }
        }
        return false;
    }

    public void memberRegister(Scanner scanner){
        System.out.println("Enter Name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter Age:");
        int age = scanner.nextInt();
        System.out.println("Enter Phone Number:");
        long phoneno = Long.parseLong(scanner.next().trim());
        if (checkPhoneno(phoneno)) {
            System.out.println("Member with Phone Number " + phoneno + " already exists.");
        } 
        else{
            String phone = Long.toString(phoneno);
            if(phone.length() != 10 ){
                System.out.println("Invalid Phone Number");
            }
            else{
                Member memberNew = new Member(name, age, phoneno);
                members.add(memberNew);
                System.out.println("Member added!");
            }
        }
    }   
}
public class App 
{
    private static void librarianMode(Librarian librarian){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Select Option:");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1){
                librarian.memberRegister(scanner);
            }
            else if(choice == 2){
                System.out.println("Enter Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Phone no:");
                long phoneNo = Integer.parseInt(scanner.next());
                librarian.memberRemove(phoneNo);
            }
            else if(choice==3){
                librarian.bookAdd();
            }
            else if(choice==4){
                librarian.bookRemove();
            }
            else if(choice==5){
                librarian.memberView();
            }
            else if(choice==6){
                librarian.bookView();
            }
            else if(choice==7){
                System.out.println("back"); 
                break;
            }
            else{
                System.out.println("Enter a valid option.");
            }
        }
    }
    private static void memberMode(Member member, Librarian librarian) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select Option:");
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            int choice = scanner.nextInt();
            if (choice == 1) {
                librarian.bookAView();
            }    
            else if(choice == 2){
                member.booksIssued();
            }
            else if(choice==3){
                System.out.println("Available Books");
                librarian.bookAView();
                if (member.getbooksIssued().isEmpty()) {
                    member.bookIssue(librarian.getBooks());
                }
                else{
                    for (Book book : member.getbooksIssued()) {
                        member.calculateFine(book.getdateIssue());
                        if(member.calculateFine(book.getdateIssue())==0){
                            member.bookIssue(librarian.getBooks());
                            break;
                        }
                        else{
                            System.out.println("Pay your due first.");
                        }
                    }
                }  
            }
            else if(choice==4){
                System.out.println("My books issued: ");
                member.booksIssued();
                member.bookReturn(librarian.getBooks());
            }
            else if(choice==5){
                int fine = member.getFine();
                // System.out.println(fine);
                member.payDue(fine);      
            }
            else{
                System.out.println("back"); 
                break;
            }
        }
    }

    public static void main(String[] args) {
        Librarian librarian = new Librarian();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Enter as Librarian");
            System.out.println("2. Enter as a Member");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            if (choice == 1){
                librarianMode(librarian);
            }
            else if(choice == 2){
                List<Member> members = librarian.getMembers();
                System.out.println("Enter your Name:");
                String name = scanner.next();
                System.out.println("Enter your Phone Number:");
                Long phoneno = Long.parseLong(scanner.next().trim());
                boolean memberfound = false;
                for (Member member : members) { 
                    if (member.getPhoneNo() == phoneno) {
                        System.out.println("Welcome " + name + " Member ID: " + member.getId());
                        memberfound = true;
                        memberMode(member, librarian);
                        break;                
                    }
                if (memberfound == false) {
                        System.out.println("Member with Name: " + name + " and Phone No: " + phoneno + " doesn't exist");
                }

                }
            } 
            else{
                System.out.println("Thanks for visiting!");
                break;
            }
        }
    }
}




