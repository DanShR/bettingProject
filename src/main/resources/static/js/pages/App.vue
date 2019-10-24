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
                let options = {
                    format: 'mmm dd yyyy',
                    showDaysInNextAndPreviousMonths: true
                }

                var elems = document.querySelectorAll('.collapsible');
                var instances3 = M.Collapsible.init(elems);
            });
        }
    }
</script>

<style>

</style>