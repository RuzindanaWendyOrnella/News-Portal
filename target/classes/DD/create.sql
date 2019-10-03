SET MODE PostgreSQL;

CREATE DATABASE news_portal;
\c news_portal;

CREATE TABLE department (
 id SERIAL PRIMARY KEY,
 Depart_name VARCHAR,
 numberOfEmployees VARCHAR,
 description VARCHAR

);

CREATE TABLE UserInCompany (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 positionInCompany VARCHAR

);


CREATE TABLE DEpartment_userInCompany (
 id SERIAL PRIMARY KEY,
 Departmentid INTEGER,
 UserInCompanyid INTEGER
);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;