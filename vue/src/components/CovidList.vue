<template>

<div class="container">
  <div class = "row">
      <a href="" target="_blank">
      <img src="../assets/Covid-19.png" alt = "Click me" class="logo" id ="imageToClick">
      </a>
     <p>Covid-19 summary results</p>
      <div class="key">Below are the estimated {{covidData['Country_text']}} data</div>
      <div class="key">Total Covid Cases:{{covidData['Total Cases_text']}} </div>
      <div class="key">Total Death Cases:{{covidData['Total Deaths_text']}} </div>
      <div class="key">Total Recovered Cases:{{covidData['Total Recovered_text']}} </div>
  </div>
  </div>
</template>

<script>
import PatientService from '../services/PatientService';

export default{
    name: "covid-list",
    data(){
        return{ 
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
    border: 1px 
    solid rgb(141, 11, 11);
    overflow: hidden;
     float:right;
    text-align: left;
    font:bold;
}

.key {
    width: 50%;
    float:left;
    font:bold;
}

.container {
    
    width: 30%;
    font-family: calibri;
    font-size: 80%;
    font: bold;
    border-radius: 8px;
    text-align: center;
    float: right;
    margin-bottom: 20rem;
    margin-top: 20rem;
    
} 

.logo{
  border: 1px ;
  border-radius: 6px;
  padding: 15px;
  width: 160px;
  float: left;
}
</style>