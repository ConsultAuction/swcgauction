import {
    LOAD_PROJECT,
    LOAD_ALL_PROJECTS,
    ADD_PROJECT,
    DELETE_PROJECT
  } from '../types';
  
  const auctionReducer = (state, action) => {
    switch (action.type) {
      case LOAD_PROJECT:
  
      return {
        ...state,
        project: action.payload,
        loading: false
      };

      case LOAD_ALL_PROJECTS:
  
      return {
        ...state,
        allProjects: action.payload,
        loading: false
      };

      case ADD_PROJECT:
  
      return {
        ...state,
        project: action.payload,
        loading: false
      };

      default:
      return state;
    }
  };
  
  export default auctionReducer;