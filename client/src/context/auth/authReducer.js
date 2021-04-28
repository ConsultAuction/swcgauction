import {
  REGISTER_SUCCESS,
  REGISTER_FAIL,
  USER_LOADED,
  AUTH_ERROR,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
  CLEAR_ERRORS,
} from '../types';

const authReducer = (state, action) => {
  switch (action.type) {
    case REGISTER_SUCCESS:

    case LOGIN_SUCCESS:
      localStorage.setItem('userid', action.payload);

      return {
        ...state,
        ...action.payload,
        isAuthenticated: true,
        loading: false,
      };
    case REGISTER_FAIL:
    case AUTH_ERROR:
    case LOGIN_FAIL:
    case LOGOUT:
      localStorage.clear();
      return {
        ...state,
        isAuthenticated: false,
        loading: false,
        user: null,
        userid: null,
      };
    case USER_LOADED:
      localStorage.setItem('user', action.payload);
      return {
        ...state,
        isAuthenticated: true,
        loading: false,
        user: action.payload,
      };

    case CLEAR_ERRORS:
      return {
        ...state,
        error: null,
      };
    default:
      return state;
  }
};

export default authReducer;
