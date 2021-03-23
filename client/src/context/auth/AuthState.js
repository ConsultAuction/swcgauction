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
    isAuthenticated: null,
    loading: true,
    user: null,
    userId: localStorage.getItem('userId')
  };

  const [state, dispatch] = useReducer(AuthReducer, initialState);


  // Load User
  const loadUser = () => {

    console.log(localStorage.getItem("userId"));

    try {
      axios.get('/api/user/' + `${userId}`).then((res) => {
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
  console.log(state)
  return (
    <AuthContext.Provider
      value={{
        isAuthenticated: state.isAuthenticated,
        loading: state.loading,
        user: state.user,
        userId: state.userId,
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