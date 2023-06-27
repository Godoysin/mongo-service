package com.example.book.features.mongo.domain;

import java.time.Instant;

public record BookDTO (String title, String author, String genre, Instant publicationDate, String isbn) { }
