import React, { Fragment, useContext, useEffect, useState } from 'react';
import moment from 'moment';
import axios from 'axios';

const Countdown = () => {
  const [endDate, setEndDate] = useState();

  const [days, setDays] = useState('00');
  const [hours, setHours] = useState('00');
  const [minutes, setMinutes] = useState('00');
  const [seconds, setSeconds] = useState('00');

  const calculateTimeLeft = () => {
    const then = moment(endDate);
    const now = moment();
    const countdown = moment(then - now);
    const days = countdown.format('D');
    const hours = countdown.format('HH');
    const minutes = countdown.format('mm');
    const seconds = countdown.format('ss');

    setDays(days);
    setHours(hours);
    setMinutes(minutes);
    setSeconds(seconds);
  };

  useEffect(() => {
    axios
      .get('/api/auction')
      .then((res) => {
        setEndDate(new Date(res.data.auctionEndDateTime));
      })
      .catch((error) => {
        console.log(error);
      });

    let intervalId;

    intervalId = setInterval(() => {
      calculateTimeLeft();
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  useEffect(() => {
    calculateTimeLeft();
    console.log(endDate);
  }, [calculateTimeLeft]);

  return (
    <Fragment>
      <p>
        {' '}
        {days}:{hours}:{minutes}:{seconds}{' '}
      </p>
    </Fragment>
  );
};

export default Countdown;
