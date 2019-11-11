<template>
    <div class="row">
        <div class="col s12 m4 offset-m4">
            <div class="card grey darken-3">
                <div class="card-content white-text bottom"  style="padding-bottom: 0px">
                    <span class="row">
                        <span class="left">#{{bet.id}}</span>
                        <span class="right">{{bet.game.league.name}}</span>
                    </span>
                    <span class="row">
                        <span class="left">{{eventBetText(bet.event)}} - {{bet.odd}}</span>
                        <span class="right valign-wrapper">
                            <img :src="'/img/' + bet.game.teamHome.image_id  + '.png'"/>&nbsp;
                            {{bet.game.teamHome.name}} {{gameScoresText(bet.game)}} {{bet.game.teamAway.name}}&nbsp;
                            <img :src="'/img/' + bet.game.teamAway.image_id  + '.png'"/>
                        </span>
                    </span>
                    <span class="row">
                         <span class="left">ratio - {{bet.ratio}}</span>
                        <span class="right">{{timestampToDateTimeString(bet.game.date)}}, {{gameStatusText(bet.game.status)}} </span>
                    </span>
                    <span class="row">
                        <span class="left">bookmaker - <span class="">{{bet.bookmaker.name}}</span></span>
                        <span class="right">result - {{betResultText(bet.result)}}</span>
                    </span>
                </div>
                <div class="card-action">
                    <span v-if="hasUserBet&&mode==null">
                        <a class="btn-floating btn-small waves-effect waves-light green"  @click="editUserBetClick"><i class="material-icons">edit</i></a>
                        <a class="btn-floating btn-small waves-effect waves-light red lighten-1"  @click="deleteUserBet"><i class="material-icons">delete</i></a>
                        <span class="white-text"> odd: {{bet.userBet.odd}} summ: {{bet.userBet.summ}}</span>
                    </span>
                    <a v-if="!hasUserBet&&mode==null" a class="btn-floating btn-small waves-effect waves-light green" @click="addUsetBetClick"><i class="material-icons">add</i></a>
                    <span v-if="mode!=null">
                        <a class="btn-floating btn-small waves-effect waves-light green"  @click="saveUserBet"><i class="material-icons">done</i></a>
                        <a class="btn-floating btn-small waves-effect waves-light red lighten-1"  @click="cancelChanges"><i class="material-icons">cancel</i></a>
                        <div class="input-field inline">
                            <div>
                                <label class="white-text bet__label__input">odd: </label>
                               <input v-model="userBetOdd" class="col s7 grey darken-3 white-text"  type="number" style="border-top: 30px; border-color: white; margin-bottom: 0px;  height: 20px; width: 60px; text-align: right">
                            </div>
                        </div>
                        <div class="input-field inline">
                            <div>
                                <label class="grey darken-3 white-text bet__label__input">summ: </label>
                               <input v-model="userBetSumm" class="col s7 grey darken-3 white-text"  type="number" style="border-top: 30px; border-color: white; margin-bottom: 0px;  height: 20px; width: 80px; text-align: right">
                            </div>
                        </div>
                    </span>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import { mapState } from 'vuex';
    import GameMixin from 'components/mixin/GameMixin.js'
    import BetMixin from "components/mixin/BetMixin";
    import DateMixin from "components/mixin/DateMixin";

    export default {
        props: ['bet'],
        mixins: [GameMixin, BetMixin, DateMixin],
        data() {
            return {
                mode: null,
                showUserBet: this.bet.userBet != null,
                userBetOdd: this.bet.odd,
                userBetSumm: this.bet.userBet == null ? 1000 : this.bet.userBet.summ
            }
        },
        computed :{
            hasUserBet: function(x) {
                return this.bet.userBet != null
            },
            ...mapState([
                         'profile'
                     ])
        },
        methods: {
            cancelChanges: function() {
                this.mode = null
            },
            editUserBetClick: function() {
               this.mode = 'editUserBet'
            },
            addUsetBetClick: function() {
                this.mode = 'addUserBet'
                this.changeShowUserBet()
            },
            saveUserBet: function() {
                if (this.mode == 'addUserBet') {
                    this.addUserBet()
                    this.mode = null
                } else if (this.mode == 'editUserBet') {
                    this.editUserBet()
                    this.mode = null
                }
            },
            addUserBet : function () {
                let  params = {
                    odd : this.userBetOdd,
                    summ : this.userBetSumm
                }
                  this.$http.post('http://localhost:9000/userBet?bet=' +  this.bet.id, params)
                    .then(function (response) {
                        this.bet.userBet  = response.data
                        this.changeShowUserBet()
                    })
            },
            editUserBet: function() {
                let  params = {
                    odd : this.userBetOdd,
                    summ : this.userBetSumm
                }
                this.$http.put('http://localhost:9000/userBet?userBet=' +  this.bet.userBet.id, params)
                    .then(function (response) {
                        this.bet.userBet  = response.data
                        this.changeShowUserBet()
                    })
            },
            deleteUserBet: function () {
                this.$http.delete('http://localhost:9000/userBet?userBet=' +  this.bet.userBet.id)
                    .then(function (response) {
                        this.bet.userBet  = null
                    })
            },
            changeShowUserBet: function () {
                this.showUserBet = !this.showUserBet
            }
        }
    }
</script>

<style>
    .bet__label__input {
        height: 20px; font-size: 15px; float: left; padding-right: 10px;
    }
</style>