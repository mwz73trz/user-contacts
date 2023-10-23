package com.mzura.dao;

import com.mzura.exception.DaoException;
import com.mzura.model.Contact;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcContactDoa implements ContactDoa{

    private final JdbcTemplate jdbcTemplate;

    public JdbcContactDoa(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT contact_id, " +
                "first_name, " +
                "last_name, " +
                "address, " +
                "city, " +
                "state, " +
                "zip, " +
                "phone_number, " +
                "email, " +
                "birth_date, " +
                "note, " +
                "grouping_id " +
                "FROM contact;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                contacts.add(mapRowToContact(results));
            }
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return contacts;
    }

    @Override
    public Contact getContactById(int contactId) {
        Contact contact = null;
        String sql = "SELECT contact_id, " +
                "first_name, " +
                "last_name, " +
                "address, " +
                "city, " +
                "state, " +
                "zip, " +
                "phone_number, " +
                "email, " +
                "birth_date, " +
                "note, " +
                "grouping_id " +
                "FROM contact " +
                "WHERE contact_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, contactId);
            if (results.next()) {
                contact = mapRowToContact(results);
            }
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }
        return contact;
    }

    @Override
    public Contact createContact(Contact contact) {
        String sql = "INSERT INTO contact(" +
                "first_name, last_name, address, city, state, zip, phone_number, " +
                "email, birth_date, note, grouping_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "RETURNING contact_id;";
        try {
            int newContactId = jdbcTemplate.queryForObject(sql, int.class, contact.getFirstName(), contact.getLastName(),
                    contact.getAddress(), contact.getCity(), contact.getState(), contact.getZip(), contact.getPhoneNumber(),
                    contact.getEmail(), contact.getBirthDate(), contact.getNote(), contact.getGroupingId());
            contact.setContactId(newContactId);
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Unable to connect to server or database", ex);
        }catch (DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation", ex);
        }
        return contact;
    }

    @Override
    public boolean updateContact(int contactId, Contact contact) {
        String sql = "UPDATE contact " +
                "SET contact_id=?, " +
                "first_name=?, " +
                "last_name=?, " +
                "address=?, " +
                "city=?, " +
                "state=?, " +
                "zip=?, " +
                "phone_number=?, " +
                "email=?, " +
                "birth_date=?, " +
                "note=?, " +
                "grouping_id=? " +
                "WHERE contact_id = ?;";
        int count = jdbcTemplate.update(sql, contact.getContactId(), contact.getFirstName(), contact.getLastName(),
                contact.getAddress(), contact.getCity(), contact.getState(), contact.getZip(),
                contact.getPhoneNumber(), contact.getEmail(), contact.getBirthDate(), contact.getNote(),
                contact.getGroupingId(), contactId);
        return count == 1;
    }

    @Override
    public boolean deleteContact(int contactId) {
        String sql = "DELETE FROM contact " +
                "WHERE contact_id = ?;";
        int count = jdbcTemplate.update(sql, contactId);
        return count == 1;
    }

    private Contact mapRowToContact(SqlRowSet rowSet) {
        return new Contact(
                rowSet.getInt("contact_id"),
                rowSet.getString("first_name"),
                rowSet.getString("last_name"),
                rowSet.getString("address"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getString("zip"),
                rowSet.getString("phone_number"),
                rowSet.getString("email"),
                rowSet.getDate("birth_date"),
                rowSet.getString("note"),
                rowSet.getInt("grouping_id")
        );
    }
}
