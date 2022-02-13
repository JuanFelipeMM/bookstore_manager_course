package com.juanfelipe.bookstoremanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.juanfelipe.bookstoremanager.dto.BookDTO;
import com.juanfelipe.bookstoremanager.dto.MessageResponseDTO;
import com.juanfelipe.bookstoremanager.service.BookService;
import com.juanfelipe.bookstoremanager.utils.BookUtils;

import static com.juanfelipe.bookstoremanager.utils.BookUtils.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((viewName, locale)->new MappingJackson2JsonView())
				.build();
	}
	
	@Test
	void testWhenPOSTisCalledThenABookShouldBeCreated() {
		BookDTO bookDTO = BookUtils.createFakeBookDTO();
		MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
				.message("Book created with ID " + bookDTO.getId())
				.build();
		
		when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);
		
		mockMvc.perform(post("/api/v1/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message"),Is.is(expectedMessageResponse.getMessage()));
				
		
	}
}
