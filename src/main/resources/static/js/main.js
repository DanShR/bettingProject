import Vue from 'vue'
import App from 'pages/App.vue'
import 'materialize-css'
import 'materialize-css/dist/css/materialize.css'
import VueResource from 'vue-resource'
import store from 'store/store'
import router from 'router/router'
import  'datejs'
Vue.use(VueResource);

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
});



