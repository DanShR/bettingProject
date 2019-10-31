import Vue from 'vue'
import VueRouter from 'vue-router'
import BetTable from 'components/bets/BetTable.vue'
import UserProfile from 'components/bets/UserProfile.vue'
import UserBetsTable from 'components/bets/UserBetsTable.vue'
import StatisticsTable from 'components/bets/StatisticsTable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/profile', component: UserProfile },
    { path: '/userBets', component: UserBetsTable },
    { path: '/statistics', component: StatisticsTable },
    { path: '/', component: BetTable }
]

export default new VueRouter({
    mode: 'history',
    routes
})