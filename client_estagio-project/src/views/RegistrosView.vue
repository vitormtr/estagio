<script>
import RegistrosService from '@/services/RegistrosService';
import EditarRegistroModal from '../components/EditarRegistroModal.vue';

export default {
  name: 'RegistrosView',

  /* -> componentes filhos */
  components: {
    EditarRegistroModal
  },

  /*
    -> retorna um objeto que define o estado inicial do componente vue
    -> essas propriedades sao reativas, mudar elas mudará também a interface onde elas são usadas
  */
  data() {
    return {
      registros: [],
      totalRegistros: 0,
      mostrarModal: false,
      registroAtual: null,
      registroIdAtual: null,
      nomeFiltro: '',
      page: 0,
      size: 5,
      totalPages: 0
    };
  },

  /* -> hook do vue.js 
     -> é chamado automaticamente assim que a instancia é criada 
     -> bom pra inicializar os dados por ex. 
  */ 
  created() {
    this.fetchRegistros();
    this.fetchTotalRegistros();
  },

  methods: {
    async fetchRegistros() {
      try {
        this.registros = await RegistrosService.getRegistros(this.page, this.size);

      } catch (error) {
        console.error('Erro ao buscar os registros:', error);
      }
    },

    async fetchTotalRegistros() {
      try {
        this.totalRegistros = await RegistrosService.getTotalRegistros();
        this.totalPages = Math.ceil(this.totalRegistros / this.size);
      } catch (error) {
        console.error('Erro ao buscar o total de registros:', error);
      }
    },

    async fetchRegistrosFiltrados() {
      if (this.nomeFiltro.trim() === '') {
        await this.fetchRegistros();
        await this.fetchTotalRegistros();
      } else {
        try {
          const registrosFiltrados = await RegistrosService.getRegistrosFiltrados(this.nomeFiltro);
          if (registrosFiltrados.length === 0) {
            this.registros = [];
            this.totalPages = 1;
          } else {
            this.registros = registrosFiltrados;
            this.totalPages = Math.ceil(this.registros.length / this.size);
          }
        } catch (error) {
          console.error('Erro ao buscar os registros filtrados:', error);
        }
      }
  },
    async removerRegistro(registroId) {
      try {
        await RegistrosService.removerRegistro(registroId);
        if (this.page > 0 && this.registros.length === 1) {
          this.page--;
        }
        this.fetchRegistros();
      } catch (error) {
        console.error('Erro ao remover os registros:', error);
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


    editarRegistro(registroId) {
      const registro = this.registros.find(r => r.id === registroId);
      this.registroIdAtual = registroId;
      this.fetchRegistros();
      if (registro) {
        this.registroAtual = registro;
        this.mostrarModal = true;
      }
    },
    
    cancelarEdicao() {
      this.mostrarModal = false;
      this.registroAtual = null;
    },

    async nextPage() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      if (this.nomeFiltro.trim() === '') {
        this.fetchRegistros();
      } else {
        this.fetchRegistrosFiltrados();
      }
    }
  },

    async previousPage() {
      if (this.page > 0) {
        this.page--;
        if (this.nomeFiltro.trim() === '') {
          this.fetchRegistros();
        } else {
          this.fetchRegistrosFiltrados();
        }
      }
    }
  }
};
</script>

<template>
<div>
  <div class="dashboard">
    <h1 class="dashboard__title">Registros</h1>
      <div class="dashboard__search">
        <input v-model="nomeFiltro" placeholder="Pesquisar por nome" />
        <button @click="fetchRegistrosFiltrados">Filtrar</button>
      </div>
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

        <tr v-if="registros.length === 0">
          <td colspan="4" class="sem-registros">Sem registros</td>
        </tr>

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

    <div class="paginacao">
        <button @click="previousPage" :disabled="page === 0">Anterior</button>
        <span>Página {{ page + 1 }} de {{ totalPages }}</span>
        <button @click="nextPage" :disabled="page >= totalPages - 1">Próxima</button>
      </div>
  </div>

  <!-- salvar e fechar sao eventos personalizados emitidos no componente filho EditarRegistroModal-->
   <EditarRegistroModal
      :mostrar="mostrarModal"
      :registro="registroAtual"
      @salvar="salvarEdicao"
      @fechar="cancelarEdicao"
    />

    <RouterLink to="/" class="dashboard__adicionar">Adicionar Registro</RouterLink>
</div>

</template>
<style>

/* .bloco   -> bloco
   .bloco__ -> componente dentro do bloco
   .bloco-- -> modifica o bloco 
*/

.dashboard {
  padding: 20px;
}

.dashboard__title {
  font-size: 24px;
  margin-bottom: 20px;
}

.dashboard__search {
  margin-bottom: 20px;
}

.dashboard__search input {
  padding: 8px;
  width: 100%;
  max-width: 150px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.dashboard__search button {
  margin-left: 10px;
  padding: 8px 12px;
  font-size: 1em;
  font-family: Arial;
  color: white;
  background-color: #6d74af;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.dashboard__search button:hover {
  background-color: hsl(226, 18%, 58%);
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

.sem-registros {
  text-align: center;
  font-size: 1.2em;
  color: #888;
  padding: 20px;
}

.paginacao {
  margin-top: 20px;
}

.paginacao button {
  padding: 8px 12px;
  font-size: 1em;
  font-family: Arial;
  color: white;
  background-color: #6d74af;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.paginacao button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.paginacao span {
  margin: 0 10px;
}

.dashboard__adicionar{  
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
