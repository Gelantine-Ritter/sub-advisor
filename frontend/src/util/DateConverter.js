import moment from 'moment'
moment().format()

export const DateConverter = {

    getTime: (date) => {
        const editDate = date.split('T')
        const finalDate = editDate[1].split(':')
        return (`${finalDate[0]}:${finalDate[1]}`)
    },

    getDate: (date) => {
        if (!date) return null
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
    },
    getManipulatedDate: (date, days) => {
        if (!date) return null
        if (moment(date).isValid()) {
            const pre = moment(date).add(days, 'd')._d
            const fin = moment(pre).format("YYYY-MM-DD")
            return fin
        }
    },
    sortEventArrayByStartingTime: (eventList) => {
        const afterList = eventList.sort((a, b) => {
            return moment(a.date).diff(b.date);
        });
        return afterList
    }
}