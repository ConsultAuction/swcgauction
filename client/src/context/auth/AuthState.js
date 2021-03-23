import React, { useReducer } from 'react';
import axios from 'axios';
import AuthContext from './authContext';
import AuthReducer from './authReducer';
import {
  USER_LOADED,
  AUTH_ERROR,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
} from '../types';

const AuthState = (props) => {
  
  const initialState = {
    isAuthenticated: false,
    loading: true,
    user: null,
    userid: null,
  };

  const [state, dispatch] = useReducer(AuthReducer, initialState);

  const { userid } = initialState;

  // Load User
  const loadUser = async () => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    console.log(userid);

    try {
      const res = await axios.get(
        '/api/user/' + localStorage.getItem('userid'),
        config
      );
      console.log(res);

      dispatch({
        type: USER_LOADED,
        payload: res.data,
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
  const login = async (formData) => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      const res = await axios.post('/api/user/login', formData, config);
      console.log(res);
      localStorage.clear();
      localStorage.setItem('userid', res.headers.userid);

      dispatch({
        type: LOGIN_SUCCESS,
        payload: res.headers.userid,
      });
      localStorage.setItem('isAuthenticated', true);
      loadUser();
    } catch (error) {
      console.log(error);
      dispatch({
        type: LOGIN_FAIL,
      });
    }
  };

  // Logout
  const logout = async () => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    try {
      const res = await axios.get('/api/user/logout', config);
      console.log(res);
      dispatch({ type: LOGOUT });
    } catch (error) {
      console.log(error);
    }
  };

  // Clear Errors

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated: state.isAuthenticated,
        loading: state.loading,
        user: state.user,
        userid: state.userid,
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
