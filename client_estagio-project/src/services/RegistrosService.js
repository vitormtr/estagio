import axios from 'axios';

const API_URL = 'http://localhost:8080/registros';

export default {
  async getRegistros(page, size) {
    try {
      const response = await axios.get(`${API_URL}`, {
        params: {
          page: page,
          size: size
        }
      });
      return response.data;
    } catch (error) {
      console.error('Erro ao encontrar registros: ', error);
      throw error;
    }
  },

  async getTotalRegistros() {
    try {
      const response = await axios.get(`${API_URL}/total`);
      return response.data;
    } catch (error) {
      console.error('Erro ao buscar total de registros: ', error);
      throw error;
    }
  },

    async getRegistrosFiltrados(nome = '', page = 0, size = 5) {
      try {
        const response = await axios.get(`${API_URL}/filtrar`, {
          params: {
            nome: nome,
            page: page,
            size: size
          }
        });
        return response.data;
      } catch (error) {
        console.error('Erro ao encontrar registros filtrados: ', error);
        throw error;
      }
    },

  async adicionarRegistro(registro) {
    try {
      const response = await axios.post(`${API_URL}/adicionar`, registro);
      return response.data;
    } catch (error) {
      console.error('Erro ao adicionar registro: ', error);
      throw error;
    }
  },

  async removerRegistro(registroId){
      try{
          const response = await axios.delete(`${API_URL}/deletar/${registroId}`);
          return response.data;
      } catch(error) {
          console.error('Erro ao remover registro: ', error);
          throw error;
      }
  },

  async atualizarRegistro(registro, registroId){
      try{
          const response = await axios.put(`${API_URL}/atualizar/${registroId}`, registro);
          return response.data;
      } catch(error) {
          console.error('Erro ao atualizar registro: ', error);
      }
  }
};

