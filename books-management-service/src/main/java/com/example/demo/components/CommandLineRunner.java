package com.example.demo.components;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunner(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = false; // Change this to true

        System.out.println("Welcome to the library management system");
        System.out.println("Type 'help' to see the available commands");

        while(running) {
            System.out.print("> ");
            String command = scanner.nextLine();

            switch(command) {
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("help - show this message");
                    System.out.println("exit - exit the application");
                    System.out.println("books - list all books");
                    System.out.println("add book - add a new book");
                    System.out.println("delete book - delete a book");
                    break;
                case "authors":
                    authorService.getAllAuthors().forEach(System.out::println);
                    break;
                case "delete book":
                    System.out.print("Enter book title: ");
                    String deleteBookTitle = scanner.nextLine();
                    Book deleteBook = bookService.getBookByTitle(deleteBookTitle);
                    if (deleteBook == null) {
                        System.out.println("Book not found");
                        break;
                    }
                    bookService.deleteBook(deleteBook);
                    System.out.println("Book deleted successfully!");
                    break;
                case "exit":
                    running = false;
                    break;
                case "books":
                    bookService.getAllBooks().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to see the available commands");
            }
        }
    }
}
