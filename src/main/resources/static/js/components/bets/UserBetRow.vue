<template>
    <tr>
        <td>{{gameDate}}</td>
        <td>{{userBet.game.status}}</td>
        <td>{{userBet.game.league.name}}</td>
        <td>{{userBet.game.teamHome.name}}</td>
        <td>{{userBet.game.scoresHome}} - {{userBet.game.scoresAway}}</td>
        <td>{{userBet.game.teamAway.name}}</td>
        <td>{{userBet.bookmaker.name}}</td>
        <td>{{userBet.userBet.odd}}</td>
        <td>{{userBet.result}}</td>
        <td>{{userBet.userBet.summ}}</td>
        <td>{{profit}}</td>

    </tr>
</template>

<script>
    export default {
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
            },
            gameDate : function () {
                return  new Date(this.userBet.game.date).toString('dd.MM.yyyy hh:mm')
            }
        }
    }
</script>