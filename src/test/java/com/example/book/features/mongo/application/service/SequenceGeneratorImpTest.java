package com.example.book.features.mongo.application.service;

import com.example.book.features.mongo.domain.DatabaseSequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SequenceGeneratorImpTest {

    @InjectMocks
    private SequenceGeneratorImp sequenceGeneratorImpInjectedMocks;

    @Mock
    private MongoOperations mongoOperationsMocked;

    @Test
    void shouldImplementSequenceGenerator() {
        // given
        Class<?>[] interfaces = SequenceGeneratorImp.class.getInterfaces();

        // when
        SequenceGeneratorImp sequenceGeneratorImp = new SequenceGeneratorImp(null);

        // then
        assertAll(
                "Implements BookService interface",
                () -> assertEquals(1, interfaces.length),
                () -> assertTrue(sequenceGeneratorImp instanceof SequenceGenerator)
        );
    }

    @Test
    void shouldGenerateSequence() {
        // given
        String sequenceName = "No sequence name";
        long sequenceNumber = 3;

        DatabaseSequence databaseSequence = new DatabaseSequence();
        databaseSequence.setId(sequenceName);
        databaseSequence.setSequence(sequenceNumber);

        when(mongoOperationsMocked.findAndModify(Mockito.any(Query.class), Mockito.any(UpdateDefinition.class), Mockito.any(FindAndModifyOptions.class), Mockito.any())).thenReturn(databaseSequence);

        // when
        long generatedSequenceNumber = sequenceGeneratorImpInjectedMocks.generateSequence(sequenceName);

        // then
        assertAll(
                "Sequence number from DB response",
                () -> assertEquals(sequenceNumber, generatedSequenceNumber)
        );

        verify(mongoOperationsMocked).findAndModify(Mockito.any(Query.class), Mockito.any(UpdateDefinition.class), Mockito.any(FindAndModifyOptions.class), Mockito.any());
    }

    @Test
    void shouldGetOneAsSequenceNumberFromNullDBResponse() {
        // given
        String sequenceName = "No sequence name";
        long expectedSequence = 1;

        DatabaseSequence databaseSequence = null;

        when(mongoOperationsMocked.findAndModify(Mockito.any(Query.class), Mockito.any(UpdateDefinition.class), Mockito.any(FindAndModifyOptions.class), Mockito.any())).thenReturn(databaseSequence);

        // when
        long generatedSequenceNumber = sequenceGeneratorImpInjectedMocks.generateSequence(sequenceName);

        // then
        assertAll(
                "Sequence number from null response",
                () -> assertEquals(expectedSequence, generatedSequenceNumber)
        );

        verify(mongoOperationsMocked).findAndModify(Mockito.any(Query.class), Mockito.any(UpdateDefinition.class), Mockito.any(FindAndModifyOptions.class), Mockito.any());
    }

}
