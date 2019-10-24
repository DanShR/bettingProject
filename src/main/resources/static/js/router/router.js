import Vue from 'vue'
import VueRouter from 'vue-router'
import BetTable from 'components/bets/BetTable.vue'
import UserProfile from 'components/bets/UserProfile.vue'
import UserBetsTable from 'components/bets/UserBetsTable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/profile', component: UserProfile },
    { path: '/userBets', component: UserBetsTable },
    { path: '/', component: BetTable }
]

export default new VueRouter({
    mode: 'history',
    routes
})