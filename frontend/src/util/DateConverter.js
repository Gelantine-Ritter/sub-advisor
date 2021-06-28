export const DateConverter = {

    getTime: (date) => {
        const editDate = date.split('T')
        const finalDate = editDate[1].split(':')
        return (`${finalDate[0]}:${finalDate[1]}`)
    },

    getDate: (date) => {
        const editDate = date.split('-')
        return (`${editDate[2]}.${editDate[1]}.${editDate[2]}`)
    },

    getDateTimeComplete: (date) => {
        console.log(date);
    }
}