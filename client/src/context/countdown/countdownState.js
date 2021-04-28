import React, { useReducer } from 'react';
import axios from 'axios';
import CountdownContext from './countdownContext';
import CountdownReducer from './countdownReducer';
import {
    COUNTDOWN_LOADED
} from '../types';

const CountdownState = props => {
    const initialState = {
      countdown: null
    };
  
    const [state, dispatch] = useReducer(CountdownReducer, initialState);
  
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
      <CountdownContext.Provider
        value={{
          countdown: state.countdown,
          loadCountdown,
        }}
      >
        {props.children}
      </CountdownContext.Provider>
    );
  };
  
  export default CountdownState;