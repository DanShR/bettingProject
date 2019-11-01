<template>
    <div class="row" style="padding-top: 20px">

        <div class="col s12 m4 offset-m4" style="padding-top: 20px; padding-bottom: 20px">
            <label>
                <input name="groupPeriod" type="radio" value="day" v-model="periodMode" @change="onChangeParams"/>
                <span>by days</span>
            </label>
            <label>
                <input name="groupPeriod" type="radio"  value="week" v-model="periodMode" @change="onChangeParams"/>
                <span>by weeks</span>
            </label>
            <label>
                <input name="groupPeriod" type="radio"   value="month" v-model="periodMode" @change="onChangeParams"/>
                <span>by month</span>
            </label>
            &nbsp;&nbsp;
            <div class="input-field inline">
                <div>
                    <label>Bet summ</label>
                    <input v-model="defaultSumm" type="number" value="10000" @change="onChangeParams"/>
                </div>
            </div>
            <div>Total profit {{totalProfit}}</div>
        </div>



        <ul id="stat" class="collapsible expandable">
            <statistics-item v-for="stat in statistics" class="col s12 m4 offset-m4"
                             :statPeriod="stat"
                             :key="stat.number"
                             :periodMode="periodMode"
                             ref="items">

            </statistics-item>
        </ul>
    </div>
</template>

<script>
    import StatisticsItem from 'components/statistics/StatisticsItem.vue'
    import DateMixin from "components/mixin/DateMixin";
    export default {
        mixins: [DateMixin],
        components: {
            StatisticsItem
        },
        data() {
            return {
                statistics: [],
                periodMode: 'week',
                defaultSumm: 10000
            }
        },

        created() {
             this.fetchStatistics()
        },
        computed: {
            totalProfit: function () {
                if (this.statistics.length > 0) {
                    let initialValue = 0
                    return this.statistics.reduce(function (previousValue, currentValue) {
                        return previousValue + currentValue.profit;
                    }, initialValue)
                }
                else
                    return 0
            }
        },
        methods: {
            onChangeParams: function() {
                let instance = M.Collapsible.getInstance(document.querySelector("#stat"))
                for(let i = 0; i < instance.el.childElementCount; i++) {
                    instance.close(i)
                }
                this.$refs.items.map(x => x.items = [])
                this.fetchStatistics()
            },
            fetchBets: function(itemId) {
                let itemComponent = this.$refs.items.find(a => a.$props.statPeriod.key == itemId)
                if (itemComponent.$data.items.length != 0) {
                    return
                }
                let itemPeriod =  itemComponent.$props.statPeriod
                let requestParams = {}
                requestParams.startDate = this.startOfDay(new Date(itemPeriod.startDate))
                requestParams.endDate = this.endOfDay(new Date(itemPeriod.endDate))

                this.$http.get('http://localhost:9000/bet/statistics/items', {params : requestParams})
                    .then(function (response) {
                        itemComponent.$data.items = response.data
                    })
            },
            fetchStatistics: function () {
                let requestParams = {}
                requestParams.defaultSumm = this.defaultSumm
                this.$http.get('http://localhost:9000/bet/statistics/' + this.periodMode, {params : requestParams})
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