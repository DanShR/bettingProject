import Vue from 'vue'
import VueRouter from 'vue-router'
import BetCardList from  '../components/bets/BetCardList.vue'
import UserProfile from '../components/common/UserProfile.vue'
import UserBetsTable from '../components/userBets/UserBetsTable.vue'
import StatisticsTable from '../components/statistics/StatisticsTable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/profile', component: UserProfile },
    { path: '/userBets', component: UserBetsTable },
    { path: '/statistics', component: StatisticsTable },
    { path: '/', component: BetCardList }
]

export default new VueRouter({
    mode: 'history',
    routes
})