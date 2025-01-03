package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

    public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntry, ObjectId> {

    }
