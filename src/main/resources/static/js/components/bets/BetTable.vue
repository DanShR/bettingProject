<template>
    <div>
        <preloader :loading="loading"/>
        <div v-if="!loading">
            <pagination
                    :current="currentPage"
                    :totalPages="totalPages"
                    @page-changed="fetchBets" />
            <bet-row  v-for="bet in betList" :bet="bet" :key="bet.id"></bet-row>
            <pagination
                        :current="currentPage"
                        :totalPages="totalPages"
                        @page-changed="fetchBets" />
        </div>
    </div>
</template>

<script>
    import BetRow from 'components/bets/BetRow.vue'
    import Pagination from 'components/bets/Pagination.vue'
    import Preloader from 'components/bets/Preloader.vue'
    export default {
        components: {
            BetRow,
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
        created() {
            this.fetchBets(this.currentPage)
        },
        methods: {
            fetchBets: function (page) {
                this.loading = true;

                var options= {
                    params : {
                        perPage: this.perPage,
                        currentPage: page
                    }
                }
                this.$http.get('http://localhost:9000/bet', options)
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