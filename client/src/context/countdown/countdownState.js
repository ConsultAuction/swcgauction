import React, { useReducer } from 'react';
import axios from 'axios';
import CountDownContext from './countdownContext';
import countdownReducer from './countdownReducer';
import {
    COUNTDOWN_LOADED
} from '../types';

const countdownState = props => {
    const initialState = {
      countdown: null
    };
  
    const [state, dispatch] = useReducer(countdownReducer, initialState);
  
    // Load User
    const loadCountdown =  async () => {
  
      try {
        const res = await axios.get('/api/auction');
  
        dispatch({
          type: COUNTDOWN_LOADED,
          payload: res.data
        });
      } catch (err) {
        console.log(err);
      }
    };
  
    return (
      <CountDownContext.Provider
        value={{
          countdown: state.countdown,
          loadCountdown,
        }}
      >
        {props.children}
      </CountDownContext.Provider>
    );
  };
  
  export default countdownState;