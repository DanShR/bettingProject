<template>
    <tr>
        <td>{{timestampToDateTimeString(userBet.game.date)}}</td>
        <td>{{gameStatusText(userBet.game.status)}}</td>
        <td>{{userBet.game.league.name}}</td>
        <td>{{userBet.game.teamHome.name}}</td>
        <td>{{userBet.game.scoresHome}} - {{userBet.game.scoresAway}}</td>
        <td>{{userBet.game.teamAway.name}}</td>
        <td>{{userBet.bookmaker.name}}</td>
        <td>{{userBet.userBet.odd}}</td>
        <td>{{betResultText(userBet.result)}}</td>
        <td>{{userBet.userBet.summ}}</td>
        <td>{{profit}}</td>

    </tr>
</template>

<script>
    import GameMixin from "components/mixin/GameMixin"
    import BetMixin from "components/mixin/BetMixin"
    import DateMixin from "components/mixin/DateMixin"

    export default {
        mixins: [GameMixin, BetMixin, DateMixin],
        props: ['userBet'],
        computed : {
            profit : function () {
                let profitSumm = 0
                if (this.userBet.result == -1) {
                    profitSumm =  '-' + this.userBet.userBet.summ
                } else if (this.userBet.result == 1) {
                    profitSumm = '+' + this.userBet.userBet.summ * this.userBet.userBet.odd - this.userBet.userBet.summ
                }
                return profitSumm
            }
        }
    }
</script>