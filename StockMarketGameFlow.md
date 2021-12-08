# Virtual Stock Market Game Flow

2021-12-08, Wed, 15:59:38

- User registers -> program creates user
    - username
    - password
- User logs in
- User creates game
    - Set current user as game_organizer
    - Current user selects start_datetime and end_datetime
    - game_status is set to "ACTIVE" (by default)
- Program creates portfolio for game organizer
    - portfolio_balance and portfolio_value are set to 100000.00 (by default)
    - portfolio_status is set to "ACTIVE"
- User (game_organizer) invites other users to join a particular game
    - requires that the a user can VIEW:
        - all games they are participating in, with their corresponding active portfolio for each game
        - all games they are the game_organizer for
        - all users who are eligible to be invited to a game
        - all pending invitations that they have received
    - user selects a game that they're the game_organizer for
    - user selects another user to invite to the game (from a list or a search of all users, probably by username)
    - once invite is sent, program creates a portfolio with the status of "PENDING" for the invited user
- User accepts or rejects pending invitations (invitations = portfolios with a portfolio_status of "PENDING")
    - if invitation is accepted, the portfolio_status for that portfolio is changed to "ACTIVE"
    - if invitation is rejected, then the portfolio is either (1) deleted (preferably) or (2) the status is set to "ARCHIVED"
- Within each active game:
    - User can VIEW:
        - the current value of other users' portfolios (calculated from portfolio balances plus the value of investments in the portfolio_stocks table, given the most recent prices listed in the stocks table)
        - the current value of their own portfolio, including extra details about how their specific stocks are doing
        - the most recent stock prices
    - User can BUY OR SELL whole shares of particular stocks (one stock per transaction)
        - program creates or updates a row in the portfolio_stocks table every time a stock is bought or sold within a particular portfolio
        - program adjusts portfolio balance down or up, depending upon whether transaction is BUY or SELL, and logs it in the transaction being created
        - program calculates portfolio value, and logs it in the transaction being created
        - program saves the transaction to the database

- NOTE: Portfolio values should be updated every time the stock prices are updated.

- Once game end date arrives:
    - all the shares in the porfolio_stocks table for the portfolios in that particular game are sold at the most recent prices in the stocks table.
    - final portfolio balance/value is then calculated--the largest portfolio wins the game.
    - game_winner is logged,
    - the winning user is given some kind of badge/counter to mark the win (calculated from COUNT of times the user appears as a game_winner in the games table),
    - game is set to "ARCHIVED" status,
    - all portfolios for that game are set to "ARCHIVED" status.
