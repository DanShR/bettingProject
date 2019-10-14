<template>
    <nav>
        <div class="navbar-fixed grey darken-3">
            <a href="#!" class="brand-logo"><i class="large material-icons">directions_run</i>Betting</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li v-if="profile"><a href="/profile"><i class="material-icons">{{profile.name}}</i></a></li>
                <li v-if="profile"><a href="/logout"><i class="material-icons">exit_to_app</i></a></li>
                <li v-if="!profile"><a href="/login">Login</a></li>
            </ul>
        </div>
    </nav>
</template>

<script>
    import { mapState } from 'vuex';
    export default {
        computed:
            mapState(['profile'])
    }
</script>

<style>

</style>