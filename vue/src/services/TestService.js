import axios from 'axios';
export default{
    getTestList(){
        return axios.get('/test')
    }
}