package com.SKDEV9.corporate_library_api.repository;


import com.SKDEV9.corporate_library_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
