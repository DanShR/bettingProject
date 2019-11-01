<template>
    <li :id="statPeriod.key">
        <div class="collapsible-header"><i class="material-icons">assessment</i>
            <span>{{periodView}}</span> &nbsp&nbsp
            <span :class="{'green-text' : isProfit, 'red-text' : !isProfit}" >profit {{statPeriod.profit}}</span> &nbsp&nbsp
            <span class="green-text "> win {{statPeriod.winQuantity}}</span> &nbsp&nbsp
            <span class="red-text">lose {{statPeriod.loseQuantity}}</span> &nbsp&nbsp
        </div>
        <div class="collapsible-body">
            <ul class="collection">
                <li class="collection-item" v-for="bet in betList">
                    <p>{{bet.game.date}} {{bet.game.league.name}}</p>
                    <p>{{bet.game.teamHome.name}} ({{bet.game.scoresHome}} - {{bet.game.scoresAway}}) {{bet.game.teamAway.name}}</p>
                    <p>{{bet.bookmaker.name}} {{eventBetText(bet.event)}} {{bet.odd}}
                    ratio - {{bet.ratio}} result  {{betResultText(bet.result)}} </p>
                </li>
            </ul>
        </div>
    </li>
</template>

<script>
    import DateMixin from "components/mixin/DateMixin";
    import BetMixin from "components/mixin/BetMixin";
    import GameMixin from "components/mixin/GameMixin";

    export default {
        mixins: [DateMixin, BetMixin, GameMixin],
        props: ['statPeriod','periodMode'],
        data() {
            return {
                items: []
            }
        },
        computed: {
            isProfit : function() {
                return this.statPeriod.profit >= 0
            },
            periodView: function() {
                if (this.periodMode == 'day')
                    return this.timestampToDateString(this.statPeriod.startDate)
                else
                    return  this.timestampToDateString(this.statPeriod.startDate) + '-' + this.timestampToDateString(this.statPeriod.endDate)
            },
            betList: function () {
                return this.items.map(x => {
                    x.game.date = this.timestampToDateTimeString(x.game.date)
                    return x
                })
            }
        }
    }
</script>