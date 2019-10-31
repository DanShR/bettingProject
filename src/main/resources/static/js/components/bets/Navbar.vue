<template>
    <nav class="grey darken-3">
        <ul id="dropdown1" class="dropdown-content grey darken-2">
            <li><a href="/profile" class="white-text">Profile</a></li>
            <li class="divider"></li>
            <li><a href="/logout" class="white-text"><i class="material-icons">exit_to_app</i></a></li>
        </ul>
        <div class="navbar-fixed">
            <a href="/" class="brand-logo"><i class="large material-icons">directions_run</i>Betting</a>

            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li v-if="profile"><a href="/statistics">Statistics</a></li>
                <li v-if="profile"><a href="/userBets">My bets</a></li>
                <li v-if="!profile"><a href="/login">Login</a></li>
                <li v-if="profile"><a class="dropdown-trigger" href="#!" data-target="dropdown1" >{{profile.name}}<i class="material-icons right">arrow_drop_down</i></a></li>
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