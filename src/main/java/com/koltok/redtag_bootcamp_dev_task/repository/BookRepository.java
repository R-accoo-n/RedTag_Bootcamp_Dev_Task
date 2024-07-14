package com.koltok.redtag_bootcamp_dev_task.repository;

import com.koltok.redtag_bootcamp_dev_task.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByAuthorNameContaining(String authorName);
}
