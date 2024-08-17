<template>
  <div v-if="mostrar" class="modal">
    <div class="modal__content">
      <h2>Editar Registro</h2>
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
        <button type="submit">Salvar</button>
        <button type="button" @click="fecharModal">Cancelar</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    mostrar: {
      type: Boolean,
      required: true
    },
    registro: {
      type: Object,
      required: true,
      default: () => ({})
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

.modal__content h2 {
  margin-top: 0;
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

.modal__content form button {
  margin-right: 10px;
}
</style>
