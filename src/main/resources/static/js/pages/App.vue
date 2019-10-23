<template>
    <div>
        <navbar />
        <router-view></router-view>
    </div>
</template>

<script>
    import BetTable from 'components/bets/BetTable.vue'
    import Navbar from 'components/bets/Navbar.vue'
    export default {
        components :{
            BetTable,
            Navbar
        }
    }
</script>

<style>

</style>