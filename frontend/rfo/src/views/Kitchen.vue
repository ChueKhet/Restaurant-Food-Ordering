<template>
  <div class="ma-5">
      <!--  Table -->
      <v-data-table
        :headers="headers"
        :items="orderRecords"
        :items-per-page="10"
      > 
      </v-data-table>
    </div>
  </template>

<script>
export default {
    data() {
    return {
  
      slipNo:"",
      tableNo:"",

      headers: [
        { text: "Sip No", align: "start", value: "slipNo" ,sortable: false},
        { text: "Table No", value: "tableNo", sortable: false },
       
      ],
      orderRecords: [],

      createMenuForm: "",
      editMenuForm: "",
      categoryLists: [1, 2],

      createDialog: false,
      createMenuForm: false,
      createSuccessSnackBar: false,
      createSuccessMsg: "Successfully created menu",

      editDialog: false,
      editMenuForm: false,
      editSuccessSnackBar: false,
      editSuccessMsg: "Successfully edited menu",

      deleteDialog: false,
      toDeleteMenu: {},
      deleteSuccessSnackBar: false,
      deleteSuccessMsg: "Successfully deleted menu",

    }
    }
}
</script>

<style>

</style>