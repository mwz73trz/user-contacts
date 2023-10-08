<template>

<div id="container">
  <div class = "row">
      <!-- <iframe src="https://covid-19.dataflowkit.com/v1/WORLD.html" 
	frameborder="0" scrolling="no"
	width="250" height="250"> -->
<!-- </iframe>  -->
   <h1 class = "Covid"> 
      <img src="../assets/Covid-19.png" alt = "Click me" class="logo" id ="imageToClick"></h1>
      <div id ="detailsContainer"> </div>
     <h1>Covid-19 summary results</h1>
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

document.addEventListener('DOMContentLoaded', function(){
    const imageToClick = document.getElementById('imageToClick');
    // const detailsContainer = document.getElementById('detailsContainer');
    // const additionalDetails = {
    //        "Country_text":'',
    //         "Last Update":'',
    //         "Total Cases_text":'',
    //         "Total Deaths_text":'',
    //         "Total Recovered_text":''

    // };
    imageToClick.addEventListener('click', function () {
        // detailsContainer.innerHTML = `
        // <h2> ${additionalDetails['Total Cases_text']}</h2>`;
    });
});



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

#container {
    width: 30%;
    border: 2px solid #1b1c1d; 
    font-family: monospace;
    font-weight: bold; 
     border-radius: 8px;
}

.logo{
  border: 1px solid rgb(176, 165, 196);
  border-radius: 6px;
  padding: 6px;
  width: 150px;
}
</style>