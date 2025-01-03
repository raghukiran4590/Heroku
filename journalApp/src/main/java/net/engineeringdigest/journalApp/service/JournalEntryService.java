package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

@Autowired
private JournalEntryRepository journalEntryRepository;

@Autowired
private UserService userService;

public List<JournalEntry> getAllEntries() {
    return journalEntryRepository.findAll();
}

public Optional<JournalEntry> findById(ObjectId myId) {
    return journalEntryRepository.findById(myId);
}

@Transactional
public void saveEntry(JournalEntry journalEntry, String userName) {
    try {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    } catch (Exception e) {
        System.out.println(e);
        throw new RuntimeException("An error occurred while executing the program",e);
    }
}

public void saveEntry(JournalEntry journalEntry) {
    journalEntryRepository.save(journalEntry);
}

@Transactional
public boolean deleteEntryById(ObjectId myId, String userName) {
    boolean removed = false;
    try {
        User user = userService.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(myId);
        }
    } catch (Exception e) {
        System.out.println(e);
        throw  new RuntimeException("An error occurred while deleting the journal entry", e);
    }
    return removed;
}

}
