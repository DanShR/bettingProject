<template>
    <li :id="statPeriod.number" >
        <div class="collapsible-header"><i class="material-icons">assessment</i>
            {{startDate}} - {{endDate}}
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
    export default {
        props: ['statPeriod'],
        data() {
            return {
                items: []
            }
        },
        computed: {
            startDate : function () {
                return new Date(this.statPeriod.startDate).toString('dd.MM.yyyy');
            },
            endDate : function () {
                return new Date(this.statPeriod.endDate).toString('dd.MM.yyyy');
            },
            betList: function () {
                return this.items.map(x => {
                    x.game.date = new Date(x.game.date).toString('dd.MM.yyyy')
                    return x
                })
            }
        }
    }
</script>