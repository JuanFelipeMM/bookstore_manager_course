package com.juanfelipe.bookstoremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.juanfelipe.bookstoremanager.dto.MessageResponseDTO;
import com.juanfelipe.bookstoremanager.entity.Book;
import com.juanfelipe.bookstoremanager.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public MessageResponseDTO create (Book book) {
		Book savedBook = bookRepository.save(book);
		return MessageResponseDTO.builder()
				.message("Book created with ID "+ savedBook.getId())
				.build();
	}
}
