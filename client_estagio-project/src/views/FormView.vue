<script>
import RegistrosService from '@/services/RegistrosService';

export default {
  data() {
    return {
      registro: {
        nome: '',
        idade: '',
        email: ''
      }
    };
  },
  methods: {
    async adicionarRegistro() {
      try {
        await RegistrosService.adicionarRegistro(this.registro);
      } catch (error) {
        console.error('Erro ao adicionar o registro:', error);
      }
    }
  }
};
</script>


<template>
  <form class="form" @submit.prevent="adicionarRegistro">
    <p class="form__titulo">Adicionar Registro</p>

    <p class="form__caixa">
      <label for="registroNome" class="form__label">Nome:</label>
      <input type="text" id="registroNome" class="form__input" v-model="registro.nome" required>
    </p>

    <p class="form__caixa">
      <label for="registroIdade" class="form__label">Idade:</label>
      <input type="text" id="registroIdade" class="form__input" v-model="registro.idade" required>
    </p>

    <p class="form__caixa">
      <label for="registroEmail" class="form__label">Email:</label>
      <input type="email" id="registroEmail" class="form__input" v-model="registro.email" required>
    </p>

    <div class="form__button-container">
      <div class="form__button">
        <button type="submit" class="form__adicionar">Adicionar Usuário</button>
      </div>
      <div class="form__button">
        <button class="form__editar">Registros</button>
      </div>
    </div>
  </form>  
</template>

<style scoped>
.form {
  max-width: 400px;
  margin: 0 auto;
  padding: 24px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form__titulo {
  font-size: 1.5em;
  margin-bottom: 20px;
  text-align: center;
  color: #333;
}

.form__caixa {
  margin-bottom: 15px;
}

.form__label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.form__input {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  transition: border-color 0.3s ease;
}

.form__input:focus {
  border-color: #007BFF;
  outline: none;
}

.form__button-container {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form__button {
  width: 100%;
}

.form__adicionar, .form__editar {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}

.form__adicionar {
  background-color: hsl(224, 34%, 47%); 
}

.form__adicionar:hover {
  background-color: hsl(224, 9%, 52%); 
}

.form__editar {
  background-color: hsl(224, 34%, 47%); 
}

.form__editar:hover {
  background-color: hsl(224, 9%, 52%); 
}




</style>
