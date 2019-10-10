import Vue from 'vue'
import VueRouter from 'vue-router'
import BetTable from 'components/bets/BetTable.veu'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: BetTable }
]

export default new VueRouter({
    mode: 'history',
    routes
})