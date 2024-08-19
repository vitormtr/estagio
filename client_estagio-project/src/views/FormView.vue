<script>
import RegistrosService from '@/services/RegistrosService';

export default {
  data() {
    return {
      registro: {
        nome: '',
        idade: '',
        email: ''
      },
      message: '',  // Mensagem de sucesso ou erro
      errorMessage: ''  // Mensagem de erro
    };
  },
  methods: {
    async adicionarRegistro() {
      try {
        await RegistrosService.adicionarRegistro(this.registro);
        this.message = 'Registro adicionado com sucesso!'; 
        this.registro = { nome: '', idade: '', email: '' };
        this.errorMessage = ''; 
      } catch (error) {
        console.error('Erro ao adicionar o registro:', error);
        this.errorMessage = 'Erro ao adicionar o registro. Por favor, tente novamente.';
        this.message = '';
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
      <button type="submit" class="form__adicionar">Adicionar Usuário</button>
      <RouterLink to="/registros" class="form__editar">Registros</RouterLink>
    </div>

    <div v-if="message" class="form__message form__message--success">
      {{ message }}
    </div>
    <div v-if="errorMessage" class="form__message form__message--error">
      {{ errorMessage }}
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
  margin-bottom: 24px;
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
  gap: 16px;
}

.form__adicionar {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  font-family: Arial; 
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  background-color: hsl(224, 34%, 47%); 
  transition: background-color 0.3s ease;
  display: flex;
  justify-content: center;
  align-items: center;
}

.form__editar {
  max-width: 100%;
  padding: 10px;
  font-size: 1em;
  font-family: Arial; 
  color: white;
  border: none;
  border-radius: 4px;
  background-color: hsl(224, 34%, 47%); ; 
  transition: background-color 0.3s ease;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
}

.form__adicionar:hover {
  background-color: hsl(224, 9%, 52%); 
}

.form__editar:hover {
  background-color: hsl(224, 9%, 52%); 
}

.form__message {
  margin-top: 20px;
  padding: 10px;
  border-radius: 4px;
  text-align: center;
}

.form__message--success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.form__message--error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

</style>
