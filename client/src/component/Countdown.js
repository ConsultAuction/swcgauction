import React, { Fragment, useContext, useEffect, useState } from 'react';
import CountdownContext from '../context/countdown/countdownContext';
import moment from 'moment';

const Countdown = () => {

    const countdownContext = useContext(CountdownContext);
    const { loadCountdown, countdown } = countdownContext;

    const [endDate, setEndDate] = useState(new Date())

    const [days, setDays] = useState('00');
    const [hours, setHours] = useState('00');
    const [minutes, setMinutes] = useState('00');
    const [seconds, setSeconds] = useState('00');

    const calculateTimeLeft = () => {

        const then = moment(endDate);
        const now = moment();
        const countdown = moment(then-now);
        const days = countdown.format('D');
        const hours = countdown.format('HH');
        const minutes = countdown.format('mm');
        const seconds = countdown.format('ss');

        setDays(days);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    useEffect(() => {

        

        if(countdown != null) {
            console.log(countdown.auctionEndDateTime);
            setEndDate(new Date(countdown.auctionEndDateTime));
        } else {
            loadCountdown();
        }

        console.log(countdown)
        

        let intervalId;

        intervalId = setInterval(() => {
            calculateTimeLeft();
        }, 1000)

        return () => clearInterval(intervalId);
        
    }, [countdown, loadCountdown]);

  return (
    <Fragment>
        <p>Days: {days} Hours: {hours} Minutes: {minutes} Seconds: {seconds}</p>
    </Fragment>
  );
};

export default Countdown;