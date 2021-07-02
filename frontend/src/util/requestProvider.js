import axios from 'axios'

export const requestProvider = {
  auth:{
    headers: {
        "Authorization": localStorage.token,
    }
},
authAndContent: {
    headers: {
        "Authorization": localStorage.token,
        "Content-Type": "application/json"
    }
},

  // VENUES

  getVenues: async () => {
    return await axios.get('/venues/')
  },
  getVenue: async (venueId) => {
    return await axios.get(`/venues/${venueId}`)
  },

  postVenue: async (username, name, password, email) => {
    return await axios.post(
      '/authenticate/registrate/',
      username,
      email,
      password,
      name
    )
  },
  updateVenue: async (venueId, body, auth) => {
    return await axios.put(`/venues/${venueId}`, body, auth)
  },

  deleteVenue: async (venueId, auth) => {
    return await axios.delete(`/venues/${venueId}`, auth)
  },

  // EVENTS

    getEvent: async (eventId) => {
        return await axios.get(`/events/${eventId}`)
    },
    getEventsForVenue: async (venueId) => {
        return await axios.get(`/events/?venue=${venueId}`)
    },
    getEventsForVenueAndDate: async (venueId, date) => {
        return await axios.get(`/events/?venue=${venueId}&date=${date}`)
    },
    getEventsForDate: async (date) => {
        return await axios.get(`/events/?date=${date}`)
    },
    getEventsForDateVenueTag: async (venueId, date, tag) => {
        if (date && venueId && tag) {
            return await axios.get(`/events/?venue=${venueId}&date=${date}&tag=${tag}`)
        }else if (date && venueId) {
            return await axios.get(`/events/?venue=${venueId}&date=${date}`)
        }else if (date && tag) {
            return await axios.get(`/events/?date=${date}&tag=${tag}`)
        }else if (tag) {
            return await axios.get(`/events/?tag=${tag}`)
        }else{
            return await axios.get(`/events/`)
        }
    },
    deleteEvent: async (eventId, auth) => {
        return await axios.delete('/events/' + eventId, auth)
    },
    updateEvent: async (eventId, body, auth) => {
        return await axios.put(`/events/${eventId}`, body, auth)
    },
    postEvent: async (body, auth) => {
        return await axios.post(`/events/`, body, auth)
    },

  // LOGIN / REGISTRATE

  loginUser: async (credentials) => {
    return await axios.post('/authenticate/', credentials)
  },

  // MAPS

  getMapData: async (finalUrl) => {
    delete axios.defaults.headers.common.Authorization

    const mapData = await axios.get(finalUrl)

    axios.defaults.headers.common.Authorization =
      'Baerer ' + localStorage.getItem('token')

    return mapData
  },

  // MEMBERS

  postMember: async (username, password, email) => {
    return await axios.post('/members', username, email, password)
  },
  getMember: async (memberId, auth) => {
    return await axios.get(`/members/${memberId}`, auth)
  },
  deleteMember: async (memberId, auth) => {
    return await axios.delete(`/members/${memberId}`, auth)
  },
  updateMember: async (memberId, body, auth) => {
    return await axios.put(`/members/${memberId}`, body, auth)
  },
  joinEvent: async (memberId, eventId) => {
    return await axios.put(`/members/${memberId}?joinEvent=${eventId}`,   { headers: {
      "Authorization": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZW1iZXI5MCIsImV4cCI6MTYyNTI2NzM4MSwiaWF0IjoxNjI1MjMxMzgxfQ.B-GsB3Gv40UcAOOVHlZHvcp95SjtpMqd9ySze8rLP8g",
  }})
  },
 
}
