const navBar = {
  state: {
    navBarActive: []
  },
  mutations: {
    UPDATE_NAR_BAR_ACTIVE: (state, data) => {
      state.navBarActive = data.slice(-1)
    }
  },
  actions: {

  }
}
export default navBar
