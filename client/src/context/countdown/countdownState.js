import React, { useReducer } from 'react';
import axios from 'axios';
import CountDownContext from './countdownContext';
import CountDownReducer from './countdownReducer';
import {
    COUNTDOWN_LOADED
} from '../types';

const CountdownState = props => {
    const initialState = {
      countdown: null
    };
  
    const [state, dispatch] = useReducer(CountDownReducer, initialState);
  
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
  
  export default CountdownState;