import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    scorketId: ''
  },
  mutations: {
    SCORKETID (state, obj) {
      state.scorketId = obj
    },
    RESET (state) {
      state.scorketId = null
    }
  },
  actions: {
    SAVE_ID ({commit}, obj) {
      commit('SCORKETID', obj)
    },
    RESET_ID ({commit}) {
      commit('SCORKETID')
    }
  }
})
export default({
  store
})
