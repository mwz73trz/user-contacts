package com.mzura.dao;

import com.mzura.model.Contact;

import java.util.List;

public interface ContactDoa {

    public List<Contact> getAllContacts();
    public Contact getContactById(int contactId);
    public Contact createContact(Contact contact);
    public boolean updateContact(int contactId, Contact contact);
    public boolean deleteContact(int contactId);
}
