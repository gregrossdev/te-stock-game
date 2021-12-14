<template>
  <div>
    <div class="users">
      <table
        class="user"
        v-for="user in this.$store.state.users"
        v-bind:key="user.id"
      >
      <thead>
          <tr>
            <th>id</th>
            <th>username</th>
            <th>password_hash</th>
            <th>authorities</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{user.id}}</td>
            <td>{{user.username}}</td>
            <td>{{user.passwordHash}}</td>
            <td>{{user.authorities}}</td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</template>

<script>
import requestUsers from "@/services/ServiceUsers";

export default {
  name: "user-table",
  methods: {
    getUsers() {
      requestUsers.list().then((response) => {
        this.$store.commit("SET_USERS", response.data);
      });
    },
  },
  created() {
    this.getUsers();
  },
};
</script>

<style scoped>

table {
  margin-bottom: 1em;  
}

table tr {
  text-align: left;
}

th,
td {
  padding: 0 1em 0 0;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>


