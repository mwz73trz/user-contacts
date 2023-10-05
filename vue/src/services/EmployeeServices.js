import axios from "axios";

export default {
    getEmployee(user) {
        return axios.get('/employee/user', user)
    },

    getOffices(){
        return axios.get('/offices')
    },
    getOfficeById(officeId) {
        return axios.get(`/offices/${officeId}`)
    },

    getReviews(review) {
        return axios.get('/reviews', review)
    },

    // updateAvailability(){
    //     },

    // updatePersonalInfo(id, information){
    //     return axios.put(`/employee/${id}`, information)
    // },
    getEmployeeById(employeeId) {
        return axios.get(`/employees/${employeeId}`)
    },
    createPersonalInfo(employee){
        return axios.post(`/employee`, employee)
    },
    getScheduleByEmployeeId(employeeId) {
        return axios.get(`/schedules/${employeeId}`)
    }

    

}