export default {
    methods: {
        timestampToDateTimeString(timestamp) {
            return new Date(timestamp).toString('dd.MM.yyyy HH:mm')
        },
        timestampToDateString(timestamp) {
            return new Date(timestamp).toString('dd.MM.yyyy')
        },
        startOfDay(date) {
            return date.setHours(0, 0, 0, 0)
        },
        endOfDay(date) {
            return date.setHours(23, 59, 59)
        }
    }
}