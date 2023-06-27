package com.example.book;

import com.example.book.features.mongo.domain.Book;
import com.example.book.features.mongo.infrastructure.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class MongoPracticeApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) {

		bookRepository.deleteAll();

		// Save a book
		Book book = new Book();
		book.setId(1);
		book.setTitle("title");
		book.setAuthor("author");
		book.setGenre("genre");
		book.setPublicationDate(Instant.now());
		book.setIsbn("isbn");
		bookRepository.save(book);

		// Fetch all books
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book bookFound : bookRepository.findAll()) {
			System.out.println(bookFound);
		}
		System.out.println();

		// Fetch an individual book
		System.out.println("Book found with findById(1):");
		System.out.println("--------------------------------");
		System.out.println(bookRepository.findById(1));

	}


}
