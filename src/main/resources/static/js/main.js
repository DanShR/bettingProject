import Vue from 'vue'
import App from 'pages/App.vue'
import 'materialize-css'
import 'materialize-css/dist/css/materialize.css'
import VueResource from 'vue-resource'
Vue.use(VueResource);

new Vue({
    el: '#app',
    render: a => a(App)
});

