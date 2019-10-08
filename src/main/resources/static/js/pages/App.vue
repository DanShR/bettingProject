<template>
    <div>
        <navbar/>
        <bet-table/>
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