<template>
    <div class="row">
        <div class="col s12 m4 offset-m4">
            <div class="card grey darken-3">
                <div class="card-content white-text bottom pad" >
                    <span class="row">
                        <span class="left">#{{bet.id}}</span>
                        <span class="right">{{bet.game.league.name}}</span>
                    </span>
                    <span class="row">
                        <span class="left">{{event}}</span>
                        <span class="right valign-wrapper">
                            <img :src="'/img/' + bet.game.teamHome.image_id  + '.png'"/>&nbsp;
                            {{bet.game.teamHome.name}} - {{bet.game.teamAway.name}}&nbsp;
                            <img :src="'/img/' + bet.game.teamAway.image_id  + '.png'"/>
                        </span>
                    </span>

                    <span class="row">
                         <span class="left">ratio - {{bet.ratio}}</span>
                        <span class="right">{{bet.game.date}}, {{stat}}</span>
                    </span>
                    <span class="row">
                        <span class="left">bookmaker - <span class="">{{bet.bookmaker.name}}</span></span>
                        <span class="right">result - {{result}}</span>
                    </span>
                    <div class="right">
                        <a class="btn-floating btn-small waves-effect waves-light green"><i class="material-icons">add</i></a>
                    </div>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
    export default {
        props: ['bet'],
        data() {
            let gameStatus, betEvent, betResult
            let gameScores = '(' + this.bet.game.scoresHome + ' : ' + this.bet.game.scoresAway +')'

            switch (this.bet.game.status) {
                case 3:
                    gameStatus = 'завершен'
                    break
                case 0:
                    gameStatus = 'не начат'
                    break
                default:
                    gameStatus = 'неизвестно'
            }

            switch (this.bet.event) {
                case 1:
                    betEvent = 'П1'
                    break
                case 2:
                    betEvent = 'Х'
                    break
                case 3:
                    betEvent = 'П2'
                    break
                default:
                    betEvent = '-'
            }

            switch (this.bet.result) {
                case -1:
                    betResult = 'lose'
                    break
                case 0:
                    betResult = 'не рассчитано'
                    break
                case 1:
                    betResult = 'win'
                    break
            }

            betEvent = betEvent + ' - ' + this.bet.odd
            return {
                stat: gameStatus,
                scores: gameScores,
                event: betEvent,
                result: betResult,
            }
        }
    }
</script>

<style>
    .pad {
        padding-bottom: 40px;
    }
</style>