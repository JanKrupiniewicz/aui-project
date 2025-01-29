package com.example.demo.components;

import com.example.demo.entities.Author;
import com.example.demo.services.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final AuthorService authorService;

    public CommandLineRunner(AuthorService authorService) {
        this.authorService = authorService;
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
                    System.out.println("authors - list all authors");
                    System.out.println("add author - add a new author");
                    break;
                case "add author":
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Enter author surname: ");
                    String authorSurname = scanner.nextLine();

                    if (authorService.getAuthorByNameAndSurname(authorName, authorSurname) != null) {
                        System.out.println("Author already exists");
                        break;
                    }
                    // authorService.createAuthor(Author.builder().id(UUID.randomUUID()).name(authorName).surname(authorSurname).build());
                    break;
                case "exit":
                    running = false;
                    break;
                case "authors":
                    authorService.getAllAuthors().forEach(System.out::println);
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to see the available commands");
            }
        }
    }
}
