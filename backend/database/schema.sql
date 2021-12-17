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
VALUES ('demo', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN'),
       ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER'),
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
    game_name       varchar(50) NOT NULL,
    game_organizer  int         NOT NULL,
    game_winner     int NULL,
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
    PRIMARY KEY (portfolio_id),
    UNIQUE (user_id, game_id)
);

CREATE TABLE stocks
(
    stock_symbol    varchar(10) NOT NULL,
    stock_name      varchar(50) NULL,
    share_price     decimal     NOT NULL,
    quote_timestamp timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (stock_symbol)
);

CREATE TABLE portfolios_stocks
(
    portfolio_id SERIAL,
    stock_symbol varchar(10),
    total_shares decimal,
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

INSERT INTO games (game_name, game_organizer, start_timestamp, end_timestamp, game_status)
VALUES ('STONKS DEMO GAME', 1, '2021-12-15 12:00:00', '2021-12-16 17:00:00', 'ACTIVE'),
       ('USER gonna Win', 2, '2021-12-15 12:00:00', '2021-12-17 10:00:00', 'ACTIVE'),
       ('ALEX for the W', 3, '2021-12-15 12:00:00', '2021-12-17 11:00:00', 'ACTIVE'),
       ('AUBREY is Golden', 4, '2021-12-15 12:00:00', '2021-12-17 12:00:00', 'ACTIVE'),
       ('It is DENNY Time!', 5, '2021-12-15 12:00:00', '2021-12-17 12:15:00', 'ACTIVE'),
       ('GREG the Bug-Crusher', 6, '2021-12-15 12:00:00', '2021-12-17 12:30:00', 'ACTIVE'),
       ('Not JOSHing Around', 7, '2021-12-15 12:00:00', '2021-12-17 12:45:00', 'ACTIVE');

-- INSERT INTO games (game_name, game_organizer, start_timestamp, end_timestamp, game_status)
-- VALUES ('STONKS DEMO GAME', 1, '2021-12-15 12:00:00', '2021-12-16 12:00:00', 'ARCHIVED'),
--        ('USER gonna Win', 2, '2021-12-15 12:00:00', '2021-12-16 12:00:00', 'ARCHIVED'),
--        ('ALEX for the W', 3, '2021-12-15 12:00:00', '2021-12-17 10:00:00', 'ACTIVE'),
--        ('AUBREY is Golden', 4, '2021-12-15 12:00:00', '2021-12-17 11:00:00', 'ACTIVE'),
--        ('It is DENNY Time!', 5, '2021-12-15 12:00:00', '2021-12-17 12:00:00', 'ACTIVE'),
--        ('GREG the Bug-Crusher', 6, '2021-12-15 12:00:00', '2021-12-17 12:15:00', 'ACTIVE'),
--        ('Not JOSHing Around', 7, '2021-12-15 12:00:00', '2021-12-17 12:30:00', 'ACTIVE');

INSERT INTO portfolios (user_id, game_id, portfolio_status)
VALUES (1, 1, 'ACTIVE'),
       (2, 1, 'ACTIVE'),
       (3, 1, 'ACTIVE'),
       (4, 1, 'ACTIVE'),
       (5, 1, 'ACTIVE'),
       (6, 1, 'ACTIVE'),
       (7, 1, 'ACTIVE'),
       (1, 2, 'ACTIVE'),
       (2, 2, 'ACTIVE'),
       (3, 2, 'ACTIVE'),
       (4, 2, 'ACTIVE'),
       (5, 2, 'ACTIVE'),
       (6, 2, 'ACTIVE'),
       (7, 2, 'ACTIVE'),
       (1, 3, 'ACTIVE'),
       (2, 3, 'ACTIVE'),
       (3, 3, 'ACTIVE'),
       (4, 3, 'ACTIVE'),
       (5, 3, 'ACTIVE'),
       (6, 3, 'ACTIVE'),
       (7, 3, 'ACTIVE'),
       (1, 4, 'ACTIVE'),
       (2, 4, 'ACTIVE'),
       (3, 4, 'ACTIVE'),
       (4, 4, 'ACTIVE'),
       (5, 4, 'ACTIVE'),
       (6, 4, 'ACTIVE'),
       (7, 4, 'ACTIVE'),
       (1, 5, 'ACTIVE'),
       (2, 5, 'ACTIVE'),
       (3, 5, 'ACTIVE'),
       (4, 5, 'ACTIVE'),
       (5, 5, 'ACTIVE'),
       (6, 5, 'ACTIVE'),
       (7, 5, 'ACTIVE');

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
       ('SNAP', '99.00'),
       ('TSLA', '99.00'),
       ('AMC', '99.00'),
       ('F', '99.00'),
       ('SNDL', '99.00'),
       ('SBUX', '99.00'),
       ('DIS', '99.00'),
       ('NIO', '99.00'),
       ('FB', '99.00'),
       ('AAL', '99.00'),
       ('PLUG', '99.00'),
       ('GPRO', '99.00'),
       ('NFLX', '99.00'),
       ('CCL', '99.00'),
       ('HOOD', '99.00'),
       ('NVDA', '99.00'),
       ('BABA', '99.00'),
       ('ACB', '99.00'),
       ('ZOM', '99.00'),
       ('NOK', '99.00'),
       ('NAKD', '99.00'),
       ('GE', '99.00'),
       ('VOO', '99.00'),
       ('DAL', '99.00'),
       ('PLTR', '99.00'),
       ('BAC', '99.00'),
       ('GME', '99.00'),
       ('AAPL', '99.00'),
       ('SPY', '99.00'),
       ('COIN', '99.00'),
       ('TLRY', '99.00'),
       ('TWTR', '99.00'),
       ('MRNA', '99.00'),
       ('BB', '99.00'),
       ('T', '99.00'),
       ('RIVN', '99.00'),
       ('FCEL', '99.00'),
       ('UBER', '99.00'),
       ('KO', '99.00'),
       ('ABNB', '99.00'),
       ('PNC', '99.00'),
       ('GIB', '99.00'),
       ('WMT', '99.00'),
       ('DWAC', '99.00'),
       ('DKNG', '99.00'),
       ('SIRI', '99.00'),
       ('NNDM', '99.00'),
       ('COST', '99.00'),
       ('FUBO', '99.00'),
       ('NRZ', '99.00'),
       ('IVR', '99.00'),
       ('TSM', '99.00'),
       ('ZM', '99.00'),
       ('GSAT', '99.00'),
       ('INTC', '99.00'),
       ('CPRX', '99.00'),
       ('PENN', '99.00'),
       ('SONY', '99.00'),
       ('V', '99.00'),
       ('ET', '99.00'),
       ('CLOV', '99.00'),
       ('RCL', '99.00'),
       ('BNGO', '99.00'),
       ('JBLU', '99.00'),
       ('PTON', '99.00'),
       ('QQQ', '99.00'),
       ('RIOT', '99.00'),
       ('WISH', '99.00'),
       ('XOM', '99.00'),
       ('NKE', '99.00'),
       ('ARKK', '99.00'),
       ('GNUS', '99.00'),
       ('CRSR', '99.00'),
       ('MTN', '99.00'),
       ('DASH', '99.00'),
       ('ATVI', '99.00'),
       ('RUN', '99.00'),
       ('TMX', '99.00'),
       ('ADGI', '99.00'),
       ('BIRD', '99.00'),
       ('BBAI', '99.00'),
       ('SOLY', '99.00'),
       ('LMDX', '99.00'),
       ('MESO', '99.00'),
       ('OSCR', '99.00'),
       ('PL', '99.00'),
       ('AXDX', '99.00'),
       ('CPS', '99.00'),
       ('SQ', '99.00'),
       ('GS', '99.00'),
       ('RKLB', '100.00');

INSERT INTO transactions (portfolio_id, stock_symbol, transaction_type, transaction_amount,
                          transaction_shares, share_price, transaction_timestamp, transaction_status,
                          portfolio_cash_after_transaction)
VALUES (1, 'AAPL', 'BUY', 1000, 10, 100, '2021-12-15 13:00:00', 'COMPLETED', 99000),
       (2, 'TSLA', 'BUY', 2000, 20, 100, '2021-12-15 14:00:00', 'COMPLETED', 98000),
       (3, 'NKE', 'BUY', 3000, 30, 100, '2021-12-15 15:00:00', 'COMPLETED', 97000),
       (4, 'UBER', 'BUY', 4000, 40, 100, '2021-12-15 16:00:00', 'COMPLETED', 96000),
       (5, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (6, 'ABNB', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (7, 'ACB', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (8, 'ADGI', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (9, 'AMC', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (10, 'AMD', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (11, 'ARKK', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (12, 'ATVI', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (13, 'AXDX', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (14, 'BABA', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (15, 'BAC', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (16, 'BB', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (17, 'BBAI', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (18, 'BIRD', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (19, 'BNGO', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (20, 'ZOM', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (21, 'ZM', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (22, 'WISH', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (23, 'TSLA', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (24, 'SPCE', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (25, 'SBUX', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (26, 'VOO', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (27, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (28, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (29, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (30, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (31, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (32, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (33, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (34, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000),
       (35, 'AMZN', 'BUY', 5000, 50, 100, '2021-12-15 17:00:00', 'COMPLETED', 95000);

INSERT INTO portfolios_stocks (portfolio_id, stock_symbol, total_shares)
VALUES  (1, 'AAPL', 10),
        (2, 'TSLA', 20),
        (3, 'NKE', 30),
        (4, 'UBER', 40),
        (5, 'AMZN', 50),
        (6, 'ABNB', 50),
        (7, 'ACB', 50),
        (8, 'ADGI', 50),
        (9, 'AMC', 50),
        (10, 'AMD', 50),
        (11, 'ARKK', 50),
        (12, 'ATVI', 50),
        (13, 'AXDX', 50),
        (14, 'BABA', 50),
        (15, 'BAC', 50),
        (16, 'BB', 50),
        (17, 'BBAI', 50),
        (18, 'BIRD', 50),
        (19, 'BNGO', 50),
        (20, 'ZOM', 50),
        (21, 'ZM', 50),
        (22, 'WISH', 50),
        (23, 'TSLA', 50),
        (24, 'SPCE', 50),
        (25, 'SBUX', 50),
        (26, 'VOO', 50),
        (27, 'AMZN', 50),
        (28, 'AMZN', 50),
        (29, 'AMZN', 50),
        (30, 'AMZN', 50),
        (31, 'AMZN', 50),
        (32, 'AMZN', 50),
        (33, 'AMZN', 50),
        (34, 'AMZN', 50),
        (35, 'AMZN', 50);

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
WHERE portfolio_id BETWEEN 4 AND 36;

COMMIT TRANSACTION;
