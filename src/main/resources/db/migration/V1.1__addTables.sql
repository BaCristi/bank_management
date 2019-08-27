CREATE TABLE users
(
    id           BIGINT PRIMARY KEY     NOT NULL AUTO_INCREMENT,
    email        CHARACTER VARYING(255) NOT NULL UNIQUE,
    created_date DATETIME               NOT NULL
);

CREATE TABLE bank_account
(
    id           BIGINT PRIMARY KEY     NOT NULL AUTO_INCREMENT,
    account_name CHARACTER VARYING(255) NOT NULL UNIQUE,
    balance      int                    not null,
    created_date DATETIME               NOT NULL,
    user_id      BIGINT                 not null REFERENCES users (id)
);

CREATE TABLE bank_account_deposit_audit
(
    id              BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    deposited       int                not null,
    user_id         BIGINT             not null REFERENCES users (id),
    bank_account_id BIGINT             not null REFERENCES bank_account (id),
    created_date    DATETIME           NOT NULL
);

CREATE TABLE bank_account_transfer_audit
(
    id                          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    transferred                 int                not null,
    user_id                     BIGINT             not null REFERENCES users (id),
    bank_account_source_id      BIGINT             not null REFERENCES bank_account (id),
    bank_account_destination_id BIGINT             not null REFERENCES bank_account (id),
    created_date                DATETIME           NOT NULL
);

CREATE TABLE bank_account_balance_checkout_audit
(
    id              BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    balance       int                not null,
    user_id         BIGINT             not null REFERENCES users (id),
    bank_account_id BIGINT             not null REFERENCES bank_account (id),
    created_date    DATETIME           NOT NULL
);
