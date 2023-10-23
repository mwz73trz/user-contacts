package com.mzura.controller;

import com.mzura.dao.ContactDoa;
import com.mzura.exception.DaoException;
import com.mzura.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ContactController {

    private final ContactDoa contactDoa;

    @Autowired
    public ContactController(ContactDoa contactDoa) {
        this.contactDoa = contactDoa;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/contacts", method = RequestMethod.GET)
    public List<Contact> getContacts() {
        List<Contact> contacts = contactDoa.getAllContacts();
        if (contacts == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Contacts found");
        }
        return contacts;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/contacts/{contactId}")
    public Contact getContactById(@PathVariable int contactId) {
        Contact result = contactDoa.getContactById(contactId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
        }
        return result;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact newContact) {

        return contactDoa.createContact(newContact);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(@PathVariable int contactId, @RequestBody Contact updatedContact) {
        updatedContact.setContactId(contactId);
        if (contactDoa.updateContact(contactId, updatedContact)) {
            return updatedContact;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found to update");
        }
    }

    @DeleteMapping("/contacts/{contactId}")
    public void deleteContact(@PathVariable int contactId) {
        if (!contactDoa.deleteContact(contactId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found to delete");
        }
    }
}
