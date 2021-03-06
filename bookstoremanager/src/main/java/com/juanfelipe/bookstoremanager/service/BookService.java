package com.juanfelipe.bookstoremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanfelipe.bookstoremanager.dto.BookDTO;
import com.juanfelipe.bookstoremanager.dto.MessageResponseDTO;
import com.juanfelipe.bookstoremanager.entity.Book;
import com.juanfelipe.bookstoremanager.mapper.BookMapper;
import com.juanfelipe.bookstoremanager.repository.BookRepository;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	private final BookMapper bookMapper = BookMapper.INSTANCE;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public MessageResponseDTO create (BookDTO bookDTO) {
		Book bookToSave= bookMapper.toModel(bookDTO); 
		
		Book savedBook = bookRepository.save(bookToSave);
		return MessageResponseDTO.builder()
				.message("Book created with ID "+ savedBook.getId())
				.build();
	}
}
