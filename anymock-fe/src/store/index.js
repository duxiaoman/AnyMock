import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import errorLog from './modules/errorLog'
import getters from './getters'
import business from './modules/business'
import navBar from './modules/navBar'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    errorLog,
    business,
    navBar
  },
  getters
})

export default store
