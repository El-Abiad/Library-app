package com.elabiad;

import com.elabiad.dao.BookDao;
import com.elabiad.model.Book;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);

    static void main() {

        BookDao dao = new BookDao();

        while(true){
            System.out.println("""
                
                -------------------------
                1) Print all books
                2) Find a book by id
                3) Add book
                4) Delete book
                5) Exit
                -------------------------
                """);
            int x;
            while (true) {
                x = readInt("Choose between 1-5: ");

                if (x >= 1 && x <= 5) {
                    break;
                }

                System.out.println("Please enter a number between 1 and 5.");
            }

            switch (x){
                case 1: { // Print all books
                    List<Book> books;
                    try{books = dao.getAll();}
                    catch(SQLException e){
                        printError("SQL error");
                        waitForContinue();
                        break;
                    }
                    if(books.isEmpty()){
                        printError("There are no books");
                        waitForContinue();
                        break;
                    }
                    System.out.printf("%-5s %-30s %-20s %-5s%n",
                            "ID", "Title", "Author", "Year");
                    for(var book : books){
                        System.out.println(book);
                    }
                    waitForContinue();
                    break;
                }
                case 2: { // Find a book by id
                    int id = readInt("Enter book's id: ");
                    Book book;
                    try{book = dao.findById(id);}
                    catch (SQLException e){
                        printError("SQL error");
                        waitForContinue();
                        break;
                    }
                    if(book != null)
                        System.out.println(book);
                    else{
                        printError("Could not find the book");
                    }
                    waitForContinue();
                    break;
                }
                case 3: { // Add book
                    String title = readString("title");
                    String author = readString("author");
                    int publishedYear = readInt("Enter book's Published_Year: ");
                    Book book = new Book(title, author, publishedYear);
                    try{dao.add(book);}
                    catch(SQLException e){
                        printError("SQL error");
                        waitForContinue();
                        break;
                    }
                    System.out.println("Book added successfully!");
                    waitForContinue();
                    break;
                }
                case 4: { // Delete book
                    int id = readInt("Enter book's id: ");
                    boolean deleted;
                    try{deleted = dao.delete(id);}
                    catch (SQLException e){
                        printError("SQL error");
                        waitForContinue();
                        break;
                    }
                    if(deleted)
                        System.out.println("Book deleted successfully");
                    else
                        printError("Could not delete book");
                    waitForContinue();
                    break;
                }
                case 5: {
                    System.out.println("Bye......");
                    return;
                }
            }
        }
    }

    // Helper methods:
    public static void printError(String error){
        System.out.println("Error: " + error + "!");
    }

    public static void waitForContinue(){
        System.out.print("Press Enter to continue....");
        input.nextLine();
    }

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                int value = input.nextInt();
                input.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    public static String readString(String message){
        while(true){
            System.out.print("Enter book's " + message + ": ");
            String s = input.nextLine().trim();
            if(s.isEmpty())
                System.out.println("Error: " + message + " cannot be empty!");
            else
                return  s;
        }
    }
}
