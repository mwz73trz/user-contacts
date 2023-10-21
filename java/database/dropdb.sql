-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'user_login';

DROP DATABASE user_login;

DROP USER user_login_owner;
DROP USER user_login_appuser;
