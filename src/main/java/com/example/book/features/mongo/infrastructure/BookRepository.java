package com.example.book.features.mongo.infrastructure;

import com.example.book.features.mongo.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

}
