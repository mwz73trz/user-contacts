-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER user_login_owner
WITH PASSWORD 'userlogin';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO user_login_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO user_login_owner;

CREATE USER user_login_appuser
WITH PASSWORD 'userlogin';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO user_login_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO user_login_appuser;
