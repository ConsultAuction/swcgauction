import React, { Fragment, useContext, useEffect } from 'react';
import CountDownContext from '../context/countdown/countdownContext';

const Countdown = () => {

    const countdownContext = useContext(CountDownContext);

    console.log(countdownContext);

    const { loadCountdown } = countdownContext;

    useEffect(() => {
        loadCountdown();
    }, [loadCountdown]);



  return (
    <Fragment>

    </Fragment>
  );
};

export default Countdown;