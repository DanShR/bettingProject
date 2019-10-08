import axios from 'axios'
import Vue from 'vue'
import Vuex from 'vuex'

const axiosInstance = axios.create({
    baseURL: 'http://localhost:9000/bet',
    withCredentials: true,
})

Vue.prototype.$axios = axiosInstance
Vuex.Store.prototype.$axios = axiosInstance
Vue.use(Vuex);

export default  new Vuex.Store({
    state: {
        bets: []
    },
    getters :{
        BETS(state) {
            return state.bets
        }
    },
    mutations: {
        SET_BETS(state, bets) {
            state.bets = bets
        }
    },
    actions :{
        BET_LIST({commit}) {
            let info = ''
            this.$axios
                .get('http://localhost:9000/bet')
                .then(r => r.data)
                .then(bets => {
                    commit('SET_BETS', bets)
                })

        }
    }

})