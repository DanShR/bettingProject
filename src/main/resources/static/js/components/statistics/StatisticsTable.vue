<template>
    <div class="row" style="padding-top: 20px">
        <ul id="stat" class="collapsible expandable">
            <statistics-item v-for="stat in statistics" class="col s12 m4 offset-m4" :statPeriod="stat" :key="stat.number" ref="items"></statistics-item>
        </ul>
    </div>
</template>

<script>
    import StatisticsItem from 'components/statistics/StatisticsItem.vue'
    export default {
        components: {
            StatisticsItem
        },
        data() {
            return {
                statistics: []
            }
        },

        created() {
             this.fetchStatistics()
        },
        methods: {
            fetchBets: function(itemId) {
                let itemComponent = this.$refs.items.find(a => a.$props.statPeriod.number == itemId)
                if (itemComponent.$data.items.length != 0) {
                    return
                }
                let itemPeriod =  itemComponent.$props.statPeriod
                let requestParams = {}
                requestParams.startDate = itemPeriod.startDate
                requestParams.endDate = itemPeriod.endDate

                this.$http.get('http://localhost:9000/bet/statistics/week/items', {params : requestParams})
                    .then(function (response) {
                        itemComponent.$data.items = response.data
                    })
            },
            fetchStatistics: function () {
                let requestParams = {}
                requestParams.defaultSumm = 1000
                this.$http.get('http://localhost:9000/bet/statistics/week', {params : requestParams})
                    .then(function (response) {
                        this.statistics = response.data
                    })
            }
        },
        mounted() {
            let self = this
            let elem = document.querySelector('#stat')
            M.Collapsible.init(elem)
            let instance = M.Collapsible.getInstance(elem)
            instance.options.onOpenStart = function (item) {
                self.fetchBets(item.id)
            }
        }
    }
</script>