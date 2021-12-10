<template>
  <section>
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
</template>

<script>
export default {
  name: "game-list",
};
</script>

<style scoped>
section {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
}

article {
  max-width: 320px;
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


