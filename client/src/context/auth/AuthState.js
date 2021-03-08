import React, { useReducer } from 'react';
import axios from 'axios';
import AuthContext from './authContext';
import AuthReducer from './authReducer';
import setAuthToken from '../../utils/setAuthToken';
import {
  USER_LOADED,
  AUTH_ERROR,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
} from '../types';

const AuthState = (props) => {
  const initialState = {
    token: localStorage.getItem('token'),
    isAuthenticated: null,
    loading: true,
    user: null,
    userid: localStorage.getItem('id'),
  };

  const [state, dispatch] = useReducer(AuthReducer, initialState);

  const { userid, token } = initialState;

  // Load User
  const loadUser = () => {
    if (token) {
      console.log(localStorage.getItem('token'));
      setAuthToken(localStorage.getItem('token'));
    }

    try {
      axios.get('/api/user/' + `${userid}`).then((res) => {
        console.log(res);
        dispatch({
          type: USER_LOADED,
          payload: res.data,
        });
      });
    } catch (error) {
      console.log(error);
      dispatch({
        type: AUTH_ERROR,
      });
    }
  };

  // Register User

  // Login User
  const login = (formData) => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      axios.post('/api/user/login', formData, config).then((res) => {
        console.log(res.headers.authorization);
        dispatch({
          type: LOGIN_SUCCESS,
          payload: res.headers,
        });
        loadUser();
      });
    } catch (error) {
      console.log(error);
      dispatch({
        type: LOGIN_FAIL,
      });
    }
  };

  // Logout
  const logout = () => dispatch({ type: LOGOUT });

  // Clear Errors

  return (
    <AuthContext.Provider
      value={{
        token: state.token,
        isAuthenticated: state.isAuthenticated,
        loading: state.loading,
        user: state.user,
        uderid: state.userid,
        loadUser,
        login,
        logout,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthState;
