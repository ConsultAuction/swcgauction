import {
  COUNTDOWN_LOADED,
} from '../types';

const countdownReducer = (state, action) => {
  switch (action.type) {
    case COUNTDOWN_LOADED:

    return {
      ...state,
      countdown: action.payload
    };

    default:
      return state;
  }
};

export default countdownReducer;