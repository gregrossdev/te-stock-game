BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

DROP TABLE IF EXISTS games;
CREATE TABLE games (
                         game_id SERIAL,
                         game_organizer int NOT NULL,
                         game_winner int,
                         start_datetime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         end_datetime timestamp NOT NULL,
                         game_status varchar(20) NOT NULL DEFAULT 'ACTIVE' CHECK (game_status IN ('ACTIVE', 'ARCHIVED')),
                         PRIMARY KEY (game_id),
                         CONSTRAINT "FK_games.game_organizer"       -- Unsure if game_organizer should reference users.user_id or portfolios.portfolio_id
                             FOREIGN KEY (game_organizer)
                                 REFERENCES users(user_id),
                         CONSTRAINT "FK_games.game_winner"          -- Unsure if game_winner should reference users.user_id or portfolios.portfolio_id
                             FOREIGN KEY (game_winner)
                                 REFERENCES users(user_id)
);

DROP TABLE IF EXISTS portfolios;
CREATE TABLE portfolios (
                              portfolio_id SERIAL,
                              user_id int NOT NULL,
                              game_id int NOT NULL,
                              portfolio_balance decimal NOT NULL DEFAULT 100000.00,
                              portfolio_value decimal NOT NULL DEFAULT 100000.00,
                              portfolio_status varchar(20) NOT NULL DEFAULT 'ACTIVE' CHECK (portfolio_status IN ('ACTIVE', 'ARCHIVED')),
                              PRIMARY KEY (portfolio_id),
                              CONSTRAINT "FK_portfolios.user_id"
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(user_id),
                              CONSTRAINT "FK_portfolios.game_id"
                                  FOREIGN KEY (game_id)
                                      REFERENCES games(game_id)
);

DROP TABLE IF EXISTS stocks;
CREATE TABLE stocks (
                          stock_symbol varchar(10),
                          share_price decimal,
                          quote_datetime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (stock_symbol)
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
                                transaction_id SERIAL,
                                portfolio_id int NOT NULL,
                                stock_symbol varchar(10) NOT NULL,
                                transaction_type varchar(10) NOT NULL CHECK (transaction_type in ('BUY', 'SELL')),
                                transaction_amount decimal NOT NULL,
                                transaction_shares decimal NOT NULL,
                                share_price decimal NOT NULL,
                                transaction_datetime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                transaction_status varchar(10) NOT NULL DEFAULT 'PENDING' CHECK (transaction_status in ('PENDING', 'COMPLETED')),
                                portfolio_balance decimal NOT NULL,
                                portfolio_value decimal NOT NULL,
                                PRIMARY KEY (transaction_id),
                                CONSTRAINT "FK_transactions.stock_symbol"
                                    FOREIGN KEY (stock_symbol)
                                        REFERENCES stocks(stock_symbol),
                                CONSTRAINT "FK_transactions.portfolio_id"
                                    FOREIGN KEY (portfolio_id)
                                        REFERENCES portfolios(portfolio_id)
);

COMMIT TRANSACTION;
