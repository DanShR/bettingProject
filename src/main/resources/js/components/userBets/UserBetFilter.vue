<template>
    <ul id="filter" class="collapsible" >
        <li>
            <div class="collapsible-header"><i class="material-icons">filter_list</i>Filter <span style="font-size: 12px"> ({{filtetText}})</span></div>
            <div  class="collapsible-body">
                    <span class="row">
                        <span class="col">
                            <label class="label" >Game date range: </label>
                            <date-range-picker
                                style="width: 200px"
                                ref="picker"
                                :locale-data="{ firstDay: 1, format: 'DD-MM-YYYY' }"
                                v-model="filter.dateRange"
                                :opens="'right'"
                            >
                            </date-range-picker>
                            <a class="btn btn-floating btn-small " @click="clearDateRange"><i class="material-icons right">clear</i></a>
                        </span>
                        <span class="col">
                            <div class="input-field col s12">
                                  <select id="sb" multiple v-model="filter.selectedBookmakers">
                                    <option v-for="bookmaker in bookmakerList" :value="bookmaker.id">{{bookmaker.name}}</option>
                                </select>
                                <label>Bookmakers</label>
                            </div>
                        </span>
                    </span>
                <a class="btn" @click="applyFilter">apply</a>
                <a class="btn" @click="clearFilter">clear all</a>
            </div>
        </li>
    </ul>
</template>

<script>
    import DateRangePicker from 'vue2-daterange-picker'
    import 'vue2-daterange-picker/dist/vue2-daterange-picker.css'
    export default {
        props: ['filter'],
        data()  {
            return {
                filtetText : 'empty',
                bookmakerList: []
            }
        },
        computed: {

        },
        components: {
            DateRangePicker
        },
        created() {
            this.fetchBookmakerList()
        },
        methods: {
            clearDateRange: function () {
                this.filter.dateRange.startDate = null
                this.filter.dateRange.endDate = null
            },
            applyFilter: function () {
                this.isActive = false
                this.setFilterText()
                this.$emit('applyFilter')
            },
            clearFilter : function() {
                this.filter.dateRange.startDate = null
                this.filter.dateRange.endDate = null
                this.filter.selectedBookmakers = []
                this.$emit('applyFilter')
                this.setFilterText()
            },
            setFilterText: function () {
                console.log(document.querySelector('#r'))
                let text = null
                let textStartDate = null
                let textEndDate = null
                if (this.filter.dateRange.startDate != null){
                    textStartDate =  this.filter.dateRange.startDate.toString('dd.MM.yyyy')
                }
                if (this.filter.dateRange.endDate != null){
                    textEndDate = this.filter.dateRange.endDate.toString('dd.MM.yyyy')
                }

                if (textStartDate != null) {
                    if (textStartDate == textEndDate) {
                        text = 'game date - ' + textStartDate
                    } else {
                        text = 'game date between ' + textStartDate + '' - '' + textEndDate
                    }
                }

                if (this.filter.selectedBookmakers.length > 0) {
                    if (text != null) {
                        text = text + '; bookmaker in : '
                    } else {
                        text = 'bookmaker in : '
                    }
                    for(let i = 0; i < this.filter.selectedBookmakers.length; i++) {
                        let bookmaker = this.bookmakerList.find(x => x.id === this.filter.selectedBookmakers[i])
                        if (bookmaker != undefined) {
                            text = text + bookmaker.name
                            if (i != this.filter.selectedBookmakers.length - 1) {
                                text = text + ', '
                            }
                        }
                    }
                }

                if (text == null)
                    text = 'empty'
                this.filtetText = text
            },
            fetchBookmakerList: function () {
                let xmlHttp = new XMLHttpRequest()
                xmlHttp.open( 'GET', 'http://localhost:9000/bookmaker', false ) // false for synchronous request
                xmlHttp.send( null )
                this.bookmakerList =  JSON.parse(xmlHttp.response)
            }
        },
        mounted() {
            M.Collapsible.init( document.querySelector('#filter'))
            M.FormSelect.init(document.querySelectorAll('#sb'))
        }
    }
</script>