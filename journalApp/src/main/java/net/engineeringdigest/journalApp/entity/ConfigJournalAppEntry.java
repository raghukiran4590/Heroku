package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@Builder
public class ConfigJournalAppEntry {

    @NonNull
    private String key;

    @NonNull
    private String value;

}
