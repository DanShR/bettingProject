<template>
    <div class="row center">
        <div class="col s12 m4 offset-m4">
            <ul class="pagination">
                <span v-if="startPage!=1">
                    <li class="waves-effect"><a @click.prevent="changePage(1)" href="#!">1</a></li>
                    <li><a>...</a></li>
                </span>
                <li v-for="page in pages" :class="{active: isCurrentPage(page), 'waves-effect': !isCurrentPage(page)}"><a @click.prevent="changePage(page)" href="#!">{{page}}</a></li>
                <span v-if="endPage!=totalPages">
                    <li><a>...</a></li>
                    <li class="waves-effect"><a @click.prevent="changePage(totalPages)" href="#!">{{totalPages}}</a></li>
                </span>
                <li :class="['waves-effect',{disabled: !hasPrev()}]"><a @click.prevent="changePagePrev(prevPage)"><i class="material-icons">chevron_left</i></a></li>
                <li :class="['waves-effect',{disabled: !hasNext()}]"><a @click.prevent="changePageNext(nextPage)"><i class="material-icons">chevron_right</i></a></li>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            current: {
                type: Number,
                default: 1
            },
            totalPages: {
                type: Number,
                default: 0
            }
        },
        data(){
            return {
                pageRange: 2
            }
        },
        computed :{
            nextPage: function() {
                return this.current + 1
            },
            prevPage: function() {
                return this.current - 1
            },
            pages: function () {
                let pages = []
                for(var i = this.startPage; i <= this.endPage; i++) {
                    pages.push(i)
                }
                return pages
            },
            startPage: function () {
                let start = this.current - this.pageRange
                let startPage = (start > 0) ? start : 1
                if (this.endPage - startPage < this.pageRange * 2)
                    startPage = this.endPage - this.pageRange * 2
                return startPage
            },
            endPage: function () {
                let end = this.current + this.pageRange
                let endPage =  Math.max(this.pageRange * 2 + 1,(end < this.totalPages) ? end : this.totalPages)
                return endPage
            }
        },
        methods: {
            hasPrev: function() {
                return this.current > 1
            },
            hasNext: function() {
                return this.current < this.totalPages
            },
            changePageNext: function(page) {
                if (this.hasNext())
                    this.$emit('page-changed', page)
            },
            changePagePrev: function(page) {
                if (this.hasPrev())
                    this.$emit('page-changed', page)
            },
            changePage: function(page) {
                this.$emit('page-changed', page)
            },
            isCurrentPage: function (page) {
                return this.current == page
            }
        }
    }
</script>

<style>

</style>