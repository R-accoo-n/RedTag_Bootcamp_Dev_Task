package com.koltok.redtag_bootcamp_dev_task.repository;

import com.koltok.redtag_bootcamp_dev_task.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
