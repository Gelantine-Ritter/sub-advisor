import axios from 'axios'

export const requestProvider = {
    
    // VENUES

    getVenues: async () => {
        return await axios.get('/venues/')
    },
    getVenue: async (venueId) => {
        return await axios.get(`/venues/${venueId}`)
    },

    postVenue: async (username, name, password, email ) => {
        return await axios.post('/authenticate/registrate/', username, name, password, email)
    },
    deleteVenue: async (venueId) => {
        return await axios.delete(`/venues/${venueId}`)
    },

    // EVENTS

    getEvent: async (eventId) => {
        return await axios.get(`/events/${eventId}`)
    },
    getEventsForVenue: async (venueId) => {
        return await axios.get(`/events/?venue=${venueId}`)
    },
    deleteEvent: async (eventId) => {
        return await axios.delete(`/events/${eventId}`)
    },
    updateEvent: async (eventId, body) => {
        return await axios.get(`/events/${eventId}`, body)
    },

    // LOGIN / REGISTRATE

    loginUser: async (credentials) => {
        return await axios.post('/authenticate/', credentials)
    },


}