<template>
  <div class="home">
      <h1>Test Data</h1>
  <div v-for="item in testList"
      v-bind:key="item"> {{item }}
  </div>
  </div>
</template>

<script>
import testService from '../services/TestService'
export default {
    name: 'test-list',
    data(){
        return{
            testList:[]
        }
    },
        created(){
            testService.getTestList().then(response => {
                this.testList = response.data;
            })
        }
    
}

</script>

<style>

</style>