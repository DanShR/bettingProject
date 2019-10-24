<template>
    <div>
        <ul class="collapsible" >
            <li >
                <div class="collapsible-header"><i class="material-icons">filter_list</i>Filter</div>
                <div  class="collapsible-body">
                    <span class="row">
                   <date-range-picker
                           ref="picker"
                           :locale-data="{ firstDay: 1, format: 'DD-MM-YYYY' }"
                           v-model="dateRange"
                           :opens="'right'"
                   >

                    </date-range-picker>

                    </span>
                </div>
            </li>
        </ul>
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
    import DateRangePicker from 'vue2-daterange-picker'
    //you need to import the CSS manually (in case you want to override it)
    import 'vue2-daterange-picker/dist/vue2-daterange-picker.css'

    export default {
        components: {
            DateRangePicker ,
            UserBetRow
        },
        data() {
            return {
                betList : [],
                startDate: null,
                endDate: null,
                dateRange: {
                    startDate:'2019-05-01',
                    endDate: '2019-05-15',
                }
            }
        },
        created() {
            this.fetchUserBets()
        },
        methods: {
            fetchUserBets: function (page) {
                this.$http.get('http://localhost:9000/userBet')
                    .then(function (response) {
                        this.betList = response.data
                    })
            }
        }
    }
</script>