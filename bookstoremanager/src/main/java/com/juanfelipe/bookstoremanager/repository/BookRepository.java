package com.juanfelipe.bookstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanfelipe.bookstoremanager.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
