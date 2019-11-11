<template>
    <div v-if="loading" class="progress">
        <div  class="indeterminate"></div>
    </div>
</template>

<script>
    export default {
        props: ['loading']
    }
</script>