
<template>
  <form class="form">
    <p class="form__titulo">Adicionar Usuário</p>
    <p class="form__caixa">
      <label for="registroNome" class="form__label">Nome:</label>
      <input type="text" id="registroNome" class="form__input" required>
    </p>
    <p class="form__caixa">
      <label for="registroEmail" class="form__label">Email:</label>
      <input type="text" id="registroEmail" class="form__input" required>
    </p>
    <div class="form__button">
        <button type="submit" class="form__button">Adicionar Usuário</button>
        <button type="submit" class="form__button">Editar Registros</button>
    <div/>

  </form>  
</template>
<script setup>
</script>

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

.form__button {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  color: white;
  background-color: #007BFF;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  display: flex;
}

.form__button:hover {
  background-color: #0056b3;
}




</style>
