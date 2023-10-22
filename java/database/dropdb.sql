-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'user_contacts';

DROP DATABASE user_contacts;

DROP USER user_contacts_owner;
DROP USER user_contacts_appuser;
