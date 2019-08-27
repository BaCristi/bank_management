CREATE TABLE users
(
    id    BIGINT PRIMARY KEY     NOT NULL AUTO_INCREMENT,
    email CHARACTER VARYING(255) NOT NULL UNIQUE
);

CREATE TABLE bank_account
(
    id           BIGINT PRIMARY KEY     NOT NULL AUTO_INCREMENT,
    account_name CHARACTER VARYING(255) NOT NULL UNIQUE,
    balance      int                    not null,
    user_id      BIGINT                 not null REFERENCES users (id)
);
