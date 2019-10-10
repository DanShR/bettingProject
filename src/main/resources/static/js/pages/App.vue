<template>
    <div>
        <navbar :profile="profile"/>
        <bet-table :profile="profile"/>
    </div>
</template>

<script>
    import BetTable from 'components/bets/BetTable.vue'
    import Navbar from 'components/bets/Navbar.vue'
    export default {
        components :{
            BetTable,
            Navbar
        },
        data() {
            return {
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>