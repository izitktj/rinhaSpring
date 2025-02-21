SELECT 'CREATE DATABASE apiteste'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'apiteste')\gexec

-- A overkill way to create a database if not exists in postgres