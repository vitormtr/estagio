<template>
  <div class="dashboard">
    <h1 class="dashboard__title">Registros</h1>
    <table class="dashboard__table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="user.id">
          <td>{{ index + 1 }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.email }}</td>
        </tr>
      </tbody>
    </table>
    <div class="form__button">
            <RouterLink to="/registros" class="form__editar">Registros</RouterLink>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      users: [
        { id: 1, name: 'John Doe', email: 'john.doe@example.com' },
        { id: 2, name: 'Jane Smith', email: 'jane.smith@example.com' },
        { id: 3, name: 'Alice Johnson', email: 'alice.johnson@example.com' }
      ]
    };
  }
};
</script>

<style>
.dashboard {
  padding: 20px;
}

.dashboard__title {
  font-size: 24px;
  margin-bottom: 20px;
}

.dashboard__table {
  width: 100%;
  border-collapse: collapse;
}

.dashboard__table th, .dashboard__table td {
  border: 1px solid #ddd;
  padding: 8px;
}

.dashboard__table th {
  background-color: #f4f4f4;
  text-align: left;
}

.dashboard__table tr:nth-child(even) {
  background-color: #f9f9f9;
}

.dashboard__table tr:hover {
  background-color: #f1f1f1;
}
</style>
