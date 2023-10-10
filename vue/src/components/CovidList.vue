<template>
<div class="banner">
  <div class = "row">
      <!-- <a href="" target="_blank"> -->
      <!-- </a> -->
          <img src="../assets/Covid-19.png" alt = "Click me" class="logo">
     <p>Covid-19 summary results</p>
      <div class="key">Below are the estimated {{covidData['Country_text']}} data ##</div>
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

.banner {
	width: 70em;
	background: gray;
	height: 15em;
	overflow: hidden;
	display: flex;
	justify-content: flex-start;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.35);
	cursor: pointer;
	border-radius: 6em;
    float: right;
	box-shadow: 0 5px 15px 10px rgba(0, 0, 0, 0.55);
    transform: scale(1);
}
.row {
    border-radius: 15px 20px 20px;
    /* border:silver solid 3px; */
    /* overflow: hidden; */
    float:right;
    /* text-align:start; */
    font:bold;
    /* display: inline-flexbox; */
    margin-right:10px;
}

.key {
    width: 50%;
    float:left;
    text-decoration-style: double;
    font:bold;
    text-align: justify;
    display: inline-flex;
}

.container {
    
    width: 35%;
    font-family: calibri;
    font-size: 160%;
    font: bold;
    text-align: left;
    float: right;
    height:120% ;
    margin-bottom: 20rem;
    margin-top: 1rem; 
    background-color: slategrey;
    
} 

.logo{
   width: 180px;
   height: 2in;
   float: left;
    /* border-radius: 15px 20px 20px; */
   /* padding: 15px; */
}
/* p{
    float:center;
    
} */
</style>