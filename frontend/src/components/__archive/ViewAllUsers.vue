<template>
  <div>
    <h2>All Users</h2>
    <user-table />
  </div>
</template>

<script>
import UserTable from "@/components/users/UserTable";

export default {
  name: "allusers",
  components: { UserTable },
};
</script>

<style>
</style>