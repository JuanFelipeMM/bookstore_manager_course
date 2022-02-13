package com.juanfelipe.bookstoremanager.utils;

import static com.juanfelipe.bookstoremanager.utils.AuthorUtils.createFakeAuthor;
import static com.juanfelipe.bookstoremanager.utils.AuthorUtils.createFakeAuthorDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.juanfelipe.bookstoremanager.dto.BookDTO;
import com.juanfelipe.bookstoremanager.entity.Book;

public class BookUtils {
	
	private static final Faker faker = Faker.instance();
	
	public static BookDTO createFakeBookDTO() {
		return BookDTO.builder()
				.id(faker.number().randomNumber())
				.name(faker.book().title())
				.pages(faker.number().numberBetween(0,200))
				.chapters(faker.number().numberBetween(0,20))
				.isbn("0-596-52868-9")
				.publisherName(faker.book().publisher())
				.author(createFakeAuthorDTO())
				.build();
				
	}
	
	public static Book createFakeBook() {
		return BookDTO.builder()
				.id(faker.number().randomNumber())
				.name(faker.book().title())
				.pages(faker.number().numberBetween(0,200))
				.chapters(faker.number().numberBetween(0,20))
				.isbn("0-596-52868-9")
				.publisherName(faker.book().publisher())
				.author(createFakeAuthor())
				.build();
				
	}
	
	public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
