import {createRouter, createWebHistory} from 'vue-router'
import FormView from '../views/FormView.vue'
import RegistrosView from '../views/RegistrosView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'form',
            component: FormView
        },
        {
            path: '/registros',
            name: 'registros',
            component: RegistrosView
        }
    ]
})
export default router

/* router tutorial usado: https://www.youtube.com/watch?v=PBqQO-keR1s */