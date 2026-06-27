package com.csc3402.lab.onetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.csc3402.lab.onetomany.model.Book;
import com.csc3402.lab.onetomany.model.Page;
import com.csc3402.lab.onetomany.repositories.BookRepository;
import com.csc3402.lab.onetomany.repositories.PageRepository;

@SpringBootApplication
public class OnetomanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetomanyApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingOneToMany(BookRepository bookRepository, PageRepository pageRepository) {
		return (args) -> {

			// BOOK (TITLE, AUTHOR, ISBN)
			Book book0 = new Book("The Great Gatsby", "F. Scott Fitzgerald",
					"9780743273565");

			Book book1 = new Book("To Kill a Mockingbird", "Harper Lee",
					"9780061120084");

			bookRepository.save(book0);
			bookRepository.save(book1);

			// 4 pages for book0
			pageRepository.save(new Page(1,
					"In my younger and more vulnerable years my father gave me some advice that I’ve been turning over in my mind ever since.",
					"Chapter 1",
					book0));

			pageRepository.save(new Page(2,
					"It was a queer, sultry summer, the summer they electrocuted the Rosenbergs, and I didn’t know what I was doing in New York.",
					"Chapter 2",
					book0));

			pageRepository.save(new Page(65,
					"Maycomb was an old town, but it was a tired old town when I first knew it.",
					"Chapter 1",
					book1));

			pageRepository.save(new Page(226,
					"Atticus was right. One time he said you never really lorem ipsum . . .",
					"Chapter 31",
					book1));

			// 3 pages for book1
			pageRepository.save(new Page(100,
					"Mockingbirds don’t do one thing but make music for us to enjoy. They don’t eat up people’s gardens, don’t nest in corncribs, they don’t do one thing but sing their hearts out for us. That’s why it’s a sin to kill a mockingbird.",
					"Chapter 10",
					book1));

			pageRepository.save(new Page(150,
					"You never really understand a person until you consider things from his point of view… Until you climb inside of his skin and walk around in it.",
					"Chapter 3",
					book1));

			pageRepository.save(new Page(200,
					"People generally see what they look for, and hear what they listen for.",
					"Chapter 17",
					book1));

		};
	}

}
