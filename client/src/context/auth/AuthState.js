import React, { useReducer } from 'react';
import axios from 'axios';
import AuthContext from './authContext';
import AuthReducer from './authReducer';
import {
  REGISTER_SUCCESS,
  USER_LOADED,
  AUTH_ERROR,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  LOGOUT,
} from '../types';
import { useHistory } from 'react-router-dom';

const AuthState = (props) => {
  const initialState = {
    isAuthenticated: false,
    loading: true,
    user: null,
    userid: null,
  };

  const [state, dispatch] = useReducer(AuthReducer, initialState);

  const history = useHistory();
  const goToPage = (path) => {
    history.push(path);
  };

  //Context for dealing with actions related to authenticating users, registering new users, loading users

  // Load User
  const loadUser = async () => {
    await axios
      .get('/api/user/' + localStorage.getItem('userid'))
      .then((res) => {
        console.log(res);

        dispatch({
          type: USER_LOADED,
          payload: res.data,
        });

        if (res.data.role === 'CLIENT') {
          goToPage('/clientUserProfile');
        } else {
          goToPage('/consultantUserProfile');
        }
      })
      .catch((error) => {
        console.log(error);
        dispatch({
          type: AUTH_ERROR,
        });
      });
  };

  // Login User
  const login = async (formData) => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      const res = await axios.post('/api/user/login', formData, config);
      localStorage.clear();
      localStorage.setItem('userid', res.headers.userid);
      console.log(res);

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

  // Register Consultant
  const registerConsultant = async (formData) => {
    await axios
      .post('/api/user/consultant', formData)
      .then((res) => {
        dispatch({
          type: REGISTER_SUCCESS,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // Register Client
  const registerClient = async (formData) => {
    await axios
      .post('/api/user/client', formData)
      .then((res) => {
        dispatch({
          type: REGISTER_SUCCESS,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
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
        registerClient,
        registerConsultant,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthState;
