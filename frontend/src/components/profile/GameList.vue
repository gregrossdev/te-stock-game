<template>
  <div>
    <section>
      <article>
        <header>
          <!-- game.game_id -->
          <h2>Game 1</h2>
          <!-- for loop based on portfolio -->
          <p>Rank: 01/25</p>
          <!-- portfolio.portfolio_value -->
          <h3>Money: $756</h3>
          <img src="https://source.unsplash.com/300x150/?coins" alt="stocks" />
        </header>
        <main>
          <div class="leaderboard">
            <!-- for loop based on portfolio return username and balance -->
            <h2>Leader Board</h2>
            <h3>Ellen Davis</h3>
            <table>
              <tr>
                <th>Username</th>
                <th>Score</th>
              </tr>
              <tr>
                <td>Ellen Davis</td>
                <td>$4500000</td>
              </tr>
              <tr>
                <td>Alex Deise</td>
                <td>$4000000</td>
              </tr>
              <tr>
                <td>Josh Steele</td>
                <td>$3700000</td>
              </tr>
              <tr>
                <td>Aubrey Strauss</td>
                <td>$3500000</td>
              </tr>
              <tr>
                <td>Denny Walter</td>
                <td>$3350000</td>
              </tr>
              <tr>
                <td>Greg Ross</td>
                <td>-$20</td>
              </tr>
            </table>
          </div>
        </main>
      </article>
      <article>
        <header>
          <!-- game.game_id -->
          <h2>Game 1</h2>
          <!-- for loop based on portfolio -->
          <p>Rank: 01/25</p>
          <!-- portfolio.portfolio_value -->
          <h3>Money: $756</h3>
          <img src="https://source.unsplash.com/300x150/?cash" alt="stocks" />
        </header>
        <main>
          <div class="leaderboard">
            <!-- for loop based on portfolio return username and balance -->
            <h2>Leader Board</h2>
            <h3>Ellen Davis</h3>
            <table>
              <tr>
                <th>Username</th>
                <th>Score</th>
              </tr>
              <tr>
                <td>Ellen Davis</td>
                <td>$4500000</td>
              </tr>
              <tr>
                <td>Alex Deise</td>
                <td>$4000000</td>
              </tr>
              <tr>
                <td>Josh Steele</td>
                <td>$3700000</td>
              </tr>
              <tr>
                <td>Aubrey Strauss</td>
                <td>$3500000</td>
              </tr>
              <tr>
                <td>Denny Walter</td>
                <td>$3350000</td>
              </tr>
              <tr>
                <td>Greg Ross</td>
                <td>-$20</td>
              </tr>
            </table>
          </div>
        </main>
      </article>
      <article>
        <header>
          <!-- game.game_id -->
          <h2>Game 1</h2>
          <!-- for loop based on portfolio -->
          <p>Rank: 01/25</p>
          <!-- portfolio.portfolio_value -->
          <h3>Money: $756</h3>
          <img src="https://source.unsplash.com/300x150/?money" alt="stocks" />
        </header>
        <main>
          <div class="leaderboard">
            <!-- for loop based on portfolio return username and balance -->
            <h2>Leader Board</h2>
            <h3>Ellen Davis</h3>
            <table>
              <tr>
                <th>Username</th>
                <th>Score</th>
              </tr>
              <tr>
                <td>Ellen Davis</td>
                <td>$4500000</td>
              </tr>
              <tr>
                <td>Alex Deise</td>
                <td>$4000000</td>
              </tr>
              <tr>
                <td>Josh Steele</td>
                <td>$3700000</td>
              </tr>
              <tr>
                <td>Aubrey Strauss</td>
                <td>$3500000</td>
              </tr>
              <tr>
                <td>Denny Walter</td>
                <td>$3350000</td>
              </tr>
              <tr>
                <td>Greg Ross</td>
                <td>-$20</td>
              </tr>
            </table>
          </div>
        </main>
      </article>
      <article>
        <header>
          <!-- game.game_id -->
          <h2>portfolio.game_id</h2>
          <!-- for loop based on portfolio -->
          <p>Rank: 01/25</p>
          <!-- portfolio.portfolio_value -->
          <h3>portfolio.portfolio_value</h3>
          <img src="https://source.unsplash.com/300x150/?stocks" alt="stocks" />
        </header>
        <main>
          <div class="leaderboard">
            <!-- for loop based on portfolio return username and balance -->
            <h2>Leader Board</h2>
            <h3>user.username: portfolio.portfolio_value</h3>
            <table>
              <tr>
                <th>Username</th>
                <th>Score</th>
              </tr>
              <tr>
                <td>user.username</td>
                <td>portfolio.portfolio_value</td>
              </tr>
            </table>
          </div>
        </main>
      </article>
    </section>
  </div>
</template>

<script>
export default {
  name: "game-list",
};
</script>

<style scoped>
section {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 0.5rem;
}

article {
  width: 300px;
  overflow: hidden;
  border: 1px solid var(--clr-grey-30);
  padding: 0.25em;
  border-radius: 0.25em;
}

article > * {
  overflow-wrap: break-word;
}

table tr {
  text-align: left;
}

table th {
  margin-left: 10em;
}

th,
td {
  padding: 0 15px 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>


