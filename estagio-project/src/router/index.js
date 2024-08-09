import {createRouter, createWebHistory} from 'vue-router'
import FormView from '../views/FormView.vue'
import EditarView from '../views/EditarView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'form',
            component: FormView
        },
        {
            path: '/editar',
            name: 'editar',
            component: EditarView
        }
    ]
})
export default router