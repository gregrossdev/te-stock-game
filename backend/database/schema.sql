BEGIN
    TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS portfolios;
DROP TABLE IF EXISTS stockWrappers;
DROP TABLE IF EXISTS transactions;

DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE CACHE 1;

CREATE TABLE users
(
    user_id       int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
    username      varchar(50)                                  NOT NULL,
    password_hash varchar(200)                                 NOT NULL,
    role          varchar(50)                                  NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username, password_hash, role)
-- ALL PRE-BUILT PASSWORDS ARE 'password'
VALUES ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       ('alex', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       ('aubrey', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       ('denny', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       ('greg', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
       ('josh', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (username, password_hash, role)
VALUES ('admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');

CREATE TABLE games
(
    game_id         SERIAL,
    game_organizer  int         NOT NULL,
    game_winner     int         NULL,
    start_timestamp timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_timestamp   timestamp   NOT NULL CHECK (start_timestamp < end_timestamp),
    game_status     varchar(20) NOT NULL DEFAULT 'ACTIVE' CHECK (game_status IN ('ACTIVE', 'ARCHIVED')),
    PRIMARY KEY (game_id)
);

CREATE TABLE portfolios
(
    portfolio_id           SERIAL,
    user_id                int         NOT NULL,
    game_id                int         NOT NULL,
    portfolio_cash         decimal     NOT NULL DEFAULT 100000.00,
    portfolio_stocks_value decimal     NOT NULL DEFAULT 0,
    portfolio_total_value  decimal     NOT NULL DEFAULT 100000.00,
    portfolio_status       varchar(20) NOT NULL DEFAULT 'ACTIVE' CHECK (portfolio_status IN ('PENDING', 'ACTIVE', 'ARCHIVED')),
    PRIMARY KEY (portfolio_id)
);

CREATE TABLE stocks
(
    stock_symbol    varchar(10) NOT NULL,
    share_price     decimal     NOT NULL,
    quote_timestamp timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (stock_symbol)
);

CREATE TABLE portfolios_stocks
(
    portfolio_id SERIAL,
    stock_symbol varchar(10),
    total_shares int,
    PRIMARY KEY (portfolio_id, stock_symbol)
);

CREATE TABLE transactions
(
    transaction_id                   SERIAL,
    portfolio_id                     int         NOT NULL,
    stock_symbol                     varchar(10) NOT NULL,
    transaction_type                 varchar(10) NOT NULL CHECK (transaction_type in ('BUY', 'SELL')),
    transaction_amount               decimal     NOT NULL,
    transaction_shares               decimal     NOT NULL,
    share_price                      decimal     NOT NULL,
    transaction_timestamp            timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    transaction_status               varchar(10) NOT NULL DEFAULT 'PENDING' CHECK (transaction_status in ('PENDING', 'COMPLETED')),
    portfolio_cash_after_transaction decimal     NOT NULL,
    PRIMARY KEY (transaction_id)
);

ALTER TABLE games
    ADD CONSTRAINT "FK_games.game_organizer"
        FOREIGN KEY (game_organizer)
            REFERENCES users (user_id);

ALTER TABLE games
    ADD CONSTRAINT "FK_games.game_winner"
        FOREIGN KEY (game_winner)
            REFERENCES users (user_id);

ALTER TABLE portfolios
    ADD CONSTRAINT "FK_portfolios.user_id"
        FOREIGN KEY (user_id)
            REFERENCES users (user_id);
ALTER TABLE portfolios
    ADD CONSTRAINT "FK_portfolios.game_id"
        FOREIGN KEY (game_id)
            REFERENCES games (game_id);

ALTER TABLE transactions
    ADD CONSTRAINT "FK_transactions.stock_symbol"
        FOREIGN KEY (stock_symbol)
            REFERENCES stocks (stock_symbol);
ALTER TABLE transactions
    ADD CONSTRAINT "FK_transactions.portfolio_id"
        FOREIGN KEY (portfolio_id)
            REFERENCES portfolios (portfolio_id);

ALTER TABLE portfolios_stocks
    ADD CONSTRAINT "FK_portfolios_stocks.portfolio_id"
        FOREIGN KEY (portfolio_id)
            REFERENCES portfolios (portfolio_id);
ALTER TABLE portfolios_stocks
    ADD CONSTRAINT "FK_portfolios_stocks.stock_symbol"
        FOREIGN KEY (stock_symbol)
            REFERENCES stocks (stock_symbol);

INSERT INTO games (game_id, game_organizer, start_timestamp, end_timestamp, game_status)
VALUES (1, 2, '2021-12-01 12:00:00', '2021-12-11 12:00:00', 'ACTIVE'),
       (2, 3, '2021-12-01 12:00:00', '2021-12-12 12:00:00', 'ACTIVE'),
       (3, 4, '2021-12-01 12:00:00', '2021-12-13 12:00:00', 'ACTIVE'),
       (4, 5, '2021-12-01 12:00:00', '2021-12-14 12:00:00', 'ACTIVE'),
       (5, 6, '2021-12-01 12:00:00', '2021-12-15 12:00:00', 'ACTIVE');

INSERT INTO portfolios (portfolio_id, user_id, game_id, portfolio_status)
VALUES (1, 2, 1, 'ACTIVE'),
       (2, 3, 1, 'ACTIVE'),
       (3, 4, 1, 'ACTIVE'),
       (4, 5, 1, 'ACTIVE'),
       (5, 6, 1, 'ACTIVE'),
       (6, 2, 2, 'ACTIVE'),
       (7, 3, 2, 'ACTIVE'),
       (8, 4, 2, 'ACTIVE'),
       (9, 5, 2, 'ACTIVE'),
       (10, 6, 2, 'ACTIVE'),
       (11, 2, 3, 'ACTIVE'),
       (12, 3, 3, 'ACTIVE'),
       (13, 4, 3, 'ACTIVE'),
       (14, 5, 3, 'ACTIVE'),
       (15, 6, 3, 'ACTIVE'),
       (16, 5, 4, 'ACTIVE'),
       (17, 6, 5, 'ACTIVE'),
       (18, 2, 4, 'PENDING'),
       (19, 3, 4, 'PENDING'),
       (20, 4, 4, 'PENDING'),
       (21, 6, 4, 'PENDING'),
       (22, 2, 5, 'PENDING'),
       (23, 3, 5, 'PENDING'),
       (24, 4, 5, 'PENDING'),
       (25, 5, 5, 'PENDING');

INSERT INTO stocks (stock_symbol, share_price)
VALUES ('SPCE', '12.70'),
       ('AMD', '15.50'),
       ('MP', '45.00'),
       ('MSFT', '333.71'),
       ('IRM', '25.70'),
       ('LCID', '65.20'),
       ('CGC', '10.77'),
       ('AMZN', '1005.20'),
       ('ICLN', '43.50'),
       ('AAPL', '99.00'),
       ('RKLB', '100.00');

INSERT INTO transactions (portfolio_id, stock_symbol, transaction_type, transaction_amount,
                          transaction_shares, share_price, transaction_timestamp, transaction_status,
                          portfolio_cash_after_transaction)
VALUES (1, 'RKLB', 'BUY', 1000, 10, 100, '2021-12-01 13:00:00', 'COMPLETED', 99000),
       (2, 'RKLB', 'BUY', 2000, 20, 100, '2021-12-01 14:00:00', 'COMPLETED', 98000),
       (3, 'RKLB', 'BUY', 3000, 30, 100, '2021-12-01 15:00:00', 'COMPLETED', 97000),
       (4, 'RKLB', 'BUY', 4000, 40, 100, '2021-12-01 16:00:00', 'COMPLETED', 96000),
       (5, 'RKLB', 'BUY', 5000, 50, 100, '2021-12-01 17:00:00', 'COMPLETED', 95000);

INSERT INTO portfolios_stocks (portfolio_id, stock_symbol, total_shares)
VALUES (1, 'RKLB', 10),
       (2, 'RKLB', 20),
       (3, 'RKLB', 30),
       (4, 'RKLB', 40),
       (5, 'RKLB', 50);

UPDATE portfolios
SET portfolio_cash = portfolio_cash - 1000
WHERE portfolio_id = 1;
UPDATE portfolios
SET portfolio_cash = portfolio_cash - 2000
WHERE portfolio_id = 2;
UPDATE portfolios
SET portfolio_cash = portfolio_cash - 3000
WHERE portfolio_id = 3;
UPDATE portfolios
SET portfolio_cash = portfolio_cash - 4000
WHERE portfolio_id = 4;
UPDATE portfolios
SET portfolio_cash = portfolio_cash - 5000
WHERE portfolio_id = 5;

COMMIT TRANSACTION;
