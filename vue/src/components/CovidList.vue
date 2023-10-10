<template>

<div class="container">
  <div class = "row">
      <a href="" target="_blank">
      <img src="../assets/Covid-19.png" alt = "Click me" class="logo">
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

<style scoped>

.row {
    /* border: 1px 
    solid rgb(141, 11, 11); */
    /* overflow: hidden; */
    float:right;
    text-align: center;
    font:bold;
    height:120%;
    /* font-size: 20%; */
}

.key {
    width: 50%;
    float:left;
    font:bold;
}

.container {
    
    width: 40%;
    font-family: calibri;
    font-size: 160%;
    font: bold;
  
    text-align: left;
    float: right;
    height:120% ;
    margin-bottom: 20rem;
    margin-top: 8rem; 
    
} 

.logo{
   width: 220px;
   float: left;
   padding: 15px;
 
}

p{
    float:center;
    
}
</style>