<template>
  <div class = "row">
     <h2>Covid-19 summary results</h2>
      <div class="key">Below are the estimated {{covidData['Country_text']}} data</div>
      <div class="key">Total Covid Cases:{{covidData['Total Cases_text']}} </div>
      <div class="key">Total Death Cases:{{covidData['Total Deaths_text']}} </div>
      <div class="key">Total Recovered Cases:{{covidData['Total Recovered_text']}} </div>
  </div>
</template>

<script>
import PatientService from '../services/PatientService';
//import axios from 'axios';import PatientService from '../services/PatientService';

export default{
    name: "covid-list",
    data(){
        return{ 
        //  responseData: null,
        //  apiUrl: 'https://covid-19.dataflowkit.com/v1/USA'
        covidData:{
            "Country_text":'',
            "Last Update":'',
            "Total Cases_text":'',
            "Total Deaths_text":'',
            "Total Recovered_text":''
        }

        };
},
created(){
    PatientService.getCovidInfo().then(response => {
        this.covidData = response.data;
    })
}
}

</script>

<style>

.row {
    border: 2px solid lightgrey;
    overflow: hidden;
}

.key {
    width: 50%;
    float:left;
    padding-left: 5px;
}

</style>