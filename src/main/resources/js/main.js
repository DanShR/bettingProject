import Vue from 'vue'
import App from  '../js/pages/App.vue'
import 'materialize-css'
import 'materialize-css/dist/css/materialize.css'
import VueResource from 'vue-resource'
import store from '../js/store/store'
import router from '../js/router/router'
import  'datejs'
Vue.use(VueResource);

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
});



