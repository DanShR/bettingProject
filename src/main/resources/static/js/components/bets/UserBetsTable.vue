<template>
    <div>
        <user-bet-filter :filter="filter" @applyFilter="fetchUserBets"></user-bet-filter>
        <table class="striped">
            <thead>
            <tr>
                <th width="250px">Date</th>
                <th>Status</th>
                <th>League</th>
                <th>Team Home</th>
                <th>Scores</th>
                <th>Team Away</th>
                <th>Bookmaker</th>
                <th>Odd</th>
                <th>Result</th>
                <th>Summ</th>
                <th>Profit</th>
            </tr>
            </thead>
            <tbody>
            <user-bet-row  v-for="userBet in betList" :userBet="userBet" :key="userBet.id"></user-bet-row>
            </tbody>
        </table>
    </div>
</template>

<script>
    import UserBetRow from 'components/bets/UserBetRow.vue'
    import UserBetFilter from 'components/bets/UserBetFilter.vue'
    export default {
        components: {
            UserBetRow,
            UserBetFilter
        },
        data() {
            return {
                betList : [],
                startDate: null,
                endDate: null,
                filter : {
                    dateRange: {
                        startDate: null,
                        endDate: null,
                    }
                }
            }
        },
        created() {
            this.fetchUserBets()
        },
        methods: {
            fetchUserBets: function (page) {
                let requestParams = {}
                if (this.filter.dateRange.startDate != null) {
                    requestParams.startDate = this.filter.dateRange.startDate.setHours(0, 0, 0, 0)
                }
                if (this.filter.dateRange.endDate != null) {
                    requestParams.endDate = this.filter.dateRange.endDate.setHours(23, 59, 59)
                }
                console.log(typeof this.filter.dateRange.endDate)

                this.$http.get('http://localhost:9000/userBet', {params : requestParams})
                    .then(function (response) {
                        this.betList = response.data
                    })
            }
        }
    }
</script>