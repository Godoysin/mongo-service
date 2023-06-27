package com.example.book.features.mongo.application.service;

import com.example.book.features.mongo.domain.DatabaseSequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorImp implements SequenceGenerator {

    private final MongoOperations mongoOperations;

    public SequenceGeneratorImp(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public long generateSequence(String sequenceName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(sequenceName)),
                new Update().inc("sequence", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }
}
