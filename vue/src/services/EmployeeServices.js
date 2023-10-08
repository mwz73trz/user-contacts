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

    getEmployeeById(employeeId) {
        return axios.get(`/employees/${employeeId}`)
    },
    //updating employeeinfo
    createPersonalInfo(employee){
        return axios.post(`/employee`, employee)
    },
    //updating employeeinfo with office
    addOfficeIdToEmployee(employee){
        return axios.post(`/employee/offieId`, employee)
    },
    getScheduleByEmployeeId(employeeId) {
        return axios.get(`/schedules/${employeeId}`)
    },
    addSchedule(schedule) {
        return axios.post(`/schedules`, schedule)
    },
    updateSchedule(scheduleId, schedule) {
        return axios.put(`/schedules/${scheduleId}`, schedule)
    },

   

}