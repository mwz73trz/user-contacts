-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER user_contacts_owner
WITH PASSWORD 'usercontacts';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO user_contacts_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO user_contacts_owner;

CREATE USER user_contacts_appuser
WITH PASSWORD 'usercontacts';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO user_contacts_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO user_contacts_appuser;
