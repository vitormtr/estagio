<template>
  <div v-if="mostrar" class="modal">
    <div class="modal__content">
      <h2>Editar Registro</h2>
      <!-- prevent -> impede a pagina de atualizar -->
      <form @submit.prevent="salvarEdicao">
        <div>
          <label for="nome">Nome:</label>
          <input v-model="registro.nome" id="nome" />
        </div>
        <div>
          <label for="idade">Idade:</label>
          <input v-model="registro.idade" id="idade" type="number" />
        </div>
        <div>
          <label for="email">Email:</label>
          <input v-model="registro.email" id="email" type="email" />
        </div>
        <div class="button-group">
          <button type="submit" class="button button--salvar">Salvar</button>
          <button type="button" class="button button--cancelar" @click="fecharModal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  /* -> estas propriedades sao recebidas do componente pai
     -> RegistrosView é o pai neste caso
  */
  props: {
    mostrar: {
      type: Boolean,
      required: true
    },
    registro: {
      type: Object,
      required: true
    }
  },

  methods: {
    salvarEdicao() {
      this.$emit('salvar', this.registro); 
    },
    fecharModal() {
      this.$emit('fechar'); 
    }
  }
};
</script>

<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal__content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  width: 300px;
  max-width: 90%;
}


.modal__content form div {
  margin-bottom: 15px;
}

.modal__content form label {
  display: block;
  margin-bottom: 5px;
}

.modal__content form input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.button-group {
  display: flex;
  justify-content: space-between;
}

.button {
  padding: 10px 20px;
  font-size: 1em;
  font-family: Arial, sans-serif;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.button--salvar {
  background-color: #658f75;
}

.button--salvar:hover {
  background-color: #4e715d;
}

.button--cancelar {
  background-color: #a94442;
}

.button--cancelar:hover {
  background-color: #8b3634;
}
</style>
