<template>
    <li :id="statPeriod.number" >
        <div class="collapsible-header"><i class="material-icons">assessment</i>
            {{timestampToDateString(statPeriod.startDate)}} - {{timestampToDateString(statPeriod.endDate)}}
            profit {{statPeriod.profit}}</div>
        <div class="collapsible-body">
            <ul class="collection">
                <li class="collection-item" v-for="bet in betList">
                    <p>{{bet.game.date}} {{bet.game.league.name}}</p>
                    <p>{{bet.game.teamHome.name}} ({{bet.game.scoresHome}} - {{bet.game.scoresAway}}) {{bet.game.teamAway.name}}</p>
                    <p>{{bet.bookmaker.name}} event - {{bet.event}} odd - {{bet.odd}}
                    ratio - {{bet.ratio}} result  {{bet.result}} </p>
                </li>
            </ul>
        </div>
    </li>
</template>

<script>
    import DateMixin from "components/mixin/DateMixin";

    export default {
        mixins: [DateMixin],
        props: ['statPeriod'],
        data() {
            return {
                items: []
            }
        },
        computed: {
            betList: function () {
                return this.items.map(x => {
                    x.game.date = this.timestampToDateTimeString(x.game.date)
                    return x
                })
            }
        }
    }
</script>