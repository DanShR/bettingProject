<template>
    <div class="row">
        <div class="col s12 m4 offset-m4">
            <h2 class="header">User profile</h2>
            <div class="card horizontal" style="width: 600px">
                <div class="card-image">
                    <img class="materialboxed" width="100" :src="profile.userpic">
                </div>
                <div class="card-stacked">
                    <div class="card-content">
                        <p>{{profile.name}}</p>
                        <p>{{profile.email}}</p>
                        <p>{{profile.gender}}</p>
                        <p>locale: {{profile.locale}}</p>
                        <p>last visit: {{profile.lastVisit}}</p>
                        <p>id: {{profile.id}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex';
    export default {
        computed:
            mapState(['profile'])
    }
</script>