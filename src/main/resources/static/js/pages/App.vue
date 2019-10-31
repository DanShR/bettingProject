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
        },
        created : function () {
            document.addEventListener('DOMContentLoaded', function() {
                let instances1 = M.Dropdown.init(document.querySelectorAll('.dropdown-trigger'));

                var elems3 = document.querySelectorAll('select');
                var instances3 = M.FormSelect.init(elems3);
            });
        }
    }
</script>

<style>

</style>