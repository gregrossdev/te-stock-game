<template>
    <div>
      
    </div>
</template>

<script>
export default {
  name: "game-card",
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


