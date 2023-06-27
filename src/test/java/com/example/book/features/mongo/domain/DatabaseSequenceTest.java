package com.example.book.features.mongo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseSequenceTest {

    @Test
    void shouldTestGetId() {
        // Given
        String id = "testId";
        DatabaseSequence databaseSequence = new DatabaseSequence();
        databaseSequence.setId(id);

        // When
        String actualId = databaseSequence.getId();

        // Then
        assertEquals(id, actualId);
    }

    @Test
    void shouldTestSetId() {
        // Given
        String id = "testId";
        DatabaseSequence databaseSequence = new DatabaseSequence();

        // When
        databaseSequence.setId(id);

        // Then
        assertEquals(id, databaseSequence.getId());
    }

    @Test
    void shouldTestGetSequence() {
        // Given
        long sequence = 12345L;
        DatabaseSequence databaseSequence = new DatabaseSequence();
        databaseSequence.setSequence(sequence);

        // When
        long actualSequence = databaseSequence.getSequence();

        // Then
        assertEquals(sequence, actualSequence);
    }

    @Test
    void shouldTestSetSequence() {
        // Given
        long sequence = 12345L;
        DatabaseSequence databaseSequence = new DatabaseSequence();

        // When
        databaseSequence.setSequence(sequence);

        // Then
        assertEquals(sequence, databaseSequence.getSequence());
    }

}
