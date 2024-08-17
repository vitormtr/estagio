<script>
import RegistrosService from '@/services/RegistrosService';
import EditarRegistroModal from '../components/EditarRegistroModal.vue';

export default {
  name: 'RegistrosView',
  components: {
    EditarRegistroModal
  },
  data() {
    return {
      registros: [],
      mostrarModal: false,
      registroAtual: null,
      registroIdAtual: null
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
    },

    editarRegistro(registroId) {
      const registro = this.registros.find(r => r.id === registroId);
      this.registroIdAtual = registroId;
      if (registro) {
        this.registroAtual = registro;
        this.mostrarModal = true;
      }
    },
    
     async salvarEdicao(registro) {
      try {
        await RegistrosService.atualizarRegistro(registro, this.registroIdAtual);
        this.fetchRegistros();
        this.mostrarModal = false;
        this.registroAtual = null;
      } catch (error) {
        console.error('Erro ao atualizar o registro:', error);
      }
    },

    cancelarEdicao() {
      this.mostrarModal = false;
      this.registroAtual = null;
    }
  }
};
</script>

<template>
<div>
  <div class="dashboard">
    <h1 class="dashboard__title">Registros</h1>
    <table class="dashboard__table">
      <thead>
        <tr>
          <th>Nome</th>
          <th>Idade</th>
          <th>Email</th>
          <th>Ações</th> 
        </tr>
      </thead>
      <tbody>
        <tr v-for="(registro) in registros" :key="registro.id">
          <td>{{ registro.nome }}</td>
          <td>{{ registro.idade }}</td>
          <td>{{ registro.email }}</td>
          <td>
            <button class="dashboard__editar" @click="editarRegistro(registro.id)">Editar</button>
            <button class="dashboard__excluir" @click="removerRegistro(registro.id)">Excluir</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
   <EditarRegistroModal
      :mostrar="mostrarModal"
      :registro="registroAtual"
      @salvar="salvarEdicao"
      @fechar="cancelarEdicao"
    />

    <RouterLink to="/" class="adicionar">Adicionar Registro</RouterLink>
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


.adicionar{  
  margin-left: 20px;
  padding: 8px;
  display: inline-flex;
  font-size: 1em;
  font-family: Arial;
  color: white;
  background-color: #658f75;;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-decoration: none;

}

.dashboard__editar{
  padding: 8px;
  font-size: 1em;
  font-family: Arial;
  color: white;
  background-color: #6d74af;;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  
}

.dashboard__excluir{
  padding: 8px;
  font-size: 1em;
  font-family: Arial;
  color: white;
  background-color: #b33f48;;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.adicionar:hover {
  background-color: hsl(145, 11%, 43%); 
}

.dashboard__editar:hover {
  background-color: hsl(226, 18%, 58%); 
}

.dashboard__excluir:hover {
  background-color: hsl(0, 23%, 44%); 
}


</style>
