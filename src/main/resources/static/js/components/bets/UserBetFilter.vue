<template>
    <ul class="collapsible" >
        <li>
            <div class="collapsible-header"><i class="material-icons">filter_list</i>Filter ({{filtetText}})</div>
            <div  class="collapsible-body">
                    <span class="row">
                        <label class="label" style="height: 50px">Select range: </label>
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
               filtetText : 'empty'
           }
       },
       components: {
           DateRangePicker
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
               this.$emit('applyFilter')
               this.setFilterText()
           },
           setFilterText: function () {
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
                       text = "game date - " + textStartDate
                   } else {
                       text = "game date between " + textStartDate + " - " + textEndDate
                   }
               }

               if (text == null)
                   text = 'empty'
               this.filtetText = text
           }
       }
   }
</script>