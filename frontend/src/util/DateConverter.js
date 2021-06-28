export const DateConverter = {

    getTime: (date) => {
        const editDate = date.split('T')
        const finalDate = editDate[1].split(':')
        return (`${finalDate[0]}:${finalDate[1]}`)
    },

    getDate: (date) => {
        const editDate = date.split('-')
        return (`${editDate[2]}.${editDate[1]}.${editDate[0]}`)
    },

    getDateTimeComplete: (date) => {
        console.log(date);
    },

    getTodayDate: () => {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0')
        var mm = String(today.getMonth() + 1).padStart(2, '0'); 
        var yyyy = today.getFullYear()
        return (`${yyyy}-${mm}-${dd}`)
    }
}