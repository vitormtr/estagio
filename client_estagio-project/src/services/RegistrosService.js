import axios from 'axios';

const API_URL = 'http://localhost:8080/registros';

export default {
    async getRegistros() {
      try {
        const response = await axios.get(API_URL);
        return response.data;
      } catch (error) {
        console.error('Erro ao encontrar registros: ', error);
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

