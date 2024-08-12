<script>
import RegistrosService from '@/services/RegistrosService';

export default {
  data() {
    return {
      registros: []
    };
  },
  created() {
    this.fetchRegistros();
  },
  methods: {
    async fetchRegistros() {
      try {
        this.registros = await RegistrosService.getRegistros();
      } catch (error) {
        console.error('Erro ao buscar os registros:', error);
      }
    },

    async removerRegistro(registroId) {
      try {
        await RegistrosService.removerRegistro(registroId);
        this.fetchRegistros();
      } catch (error) {
        console.error('Erro ao remover os registros:', error);
      }
    }
  }
};
</script>

<template>
  <div class="dashboard">
    <h1 class="dashboard__title">Registros</h1>
    <table class="dashboard__table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Idade</th>
          <th>Email</th>
          <th>Ações</th> 
        </tr>
      </thead>
      <tbody>
        <tr v-for="(registro, index) in registros" :key="registro.id">
          <td>{{ index }}</td>
          <td>{{ registro.nome }}</td>
          <td>{{ registro.idade }}</td>
          <td>{{ registro.email }}</td>
          <td>
            <button class="dashboard__adicionar">Adicionar</button>
            <button class="dashboard__editar" @click="editarRegistro(registro.id)">Editar</button>
            <button class="dashboard__excluir" @click="removerRegistro(registro.id)">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
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
  background-color: #a5a9cc;
  text-align: left;
}

.dashboard__table tr:hover {
  background-color: #f1f1f1;
}

.dashboard__table td button {
  margin-left: 5px;
  padding: 5px 10px;
  cursor: pointer;
}

.dashboard__table td button.btn-acao {
  margin-left: 5px;
  padding: 5px 10px;
  cursor: pointer;
  color: white;
  border: none;
  border-radius: 4px;
}

.dashboard__editar{
  padding: 8px;
  font-size: 16px;
  color: white;
  background-color: #6d74af;;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.dashboard__excluir{
  padding: 8px;
  font-size: 16px;
  color: white;
  background-color: #b33f48;;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.dashboard__editar:hover {
  background-color: hsl(226, 18%, 58%); 
}

.dashboard__excluir:hover {
  background-color: hsl(0, 23%, 44%); 
}
</style>
