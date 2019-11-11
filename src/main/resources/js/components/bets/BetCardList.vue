<template>
    <div>
        <div v-if="profile">
             <preloader :loading="loading"/>
            <div v-if="!loading">
                <bet-card  v-for="bet in betList" :bet="bet" :key="bet.id"></bet-card>
                <pagination
                        :current="currentPage"
                        :totalPages="totalPages"
                        @page-changed="fetchBets" />
            </div>
        </div>
    </div>
</template>

<script>
    import BetCard from  'components/bets/BetCard.vue'//   '../components/bets/BetCard.vue'
    import Pagination from 'components/bets/Pagination.vue'
    import Preloader from 'components/common/Preloader.vue'
    import { mapState } from 'vuex';

    export default {
        components: {
            BetCard,
            Pagination,
            Preloader
        },
        data() {
            return {
                betList : [],
                totalPages: 0,
                perPage : 7,
                currentPage : 1,
                loading: false
            }
        },
        computed:
            mapState(['profile',]),
        created() {
            if (this.profile)
                this.fetchBets(this.currentPage)
        },
        methods: {
            fetchBets: function (page) {
                this.loading = true;

                let options= {
                    params : {
                        perPage: this.perPage,
                        currentPage: page
                    }
                }
                this.$http.get('/bet', options)
                    .then(function (response) {
                        this.betList = response.data.content
                        this.currentPage = page
                        this.totalPages = response.data.totalPages
                        this.loading = false;
                    })
            }
        }
    }
</script>

<style>

</style>