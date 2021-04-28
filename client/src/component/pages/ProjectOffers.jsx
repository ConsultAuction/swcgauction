import axios from 'axios';
import React, { useState, useEffect, useContext, Fragment } from 'react';
import { Link } from 'react-router-dom';

const ProjectOffers = (props) => {
  const [offers, setOffers] = useState();

  useEffect(() => {
    axios
      .get(`/api/projectOffer/user/` + localStorage.getItem('userid'))
      .then((res) => {
        setOffers(res.data);
      });
  }, []);

  return (
    <Fragment>
      {offers ? (
        offers.map((offer) => (
          <Link
            to={{
              pathname: '/ProjectOffer',
              state: {
                projectOffer: offer,
              },
            }}
          >
            <div key={offer.offerId}>
              <p>Name: {offer.projectName}</p>
              <p>Description: {offer.description}</p>
              <p>Start date: {offer.startDate}</p>
              <p>End date: {offer.endDate}</p>
              {offer.bids.map((bid) => (
                <Fragment>
                  <p>Bid: {bid.price}</p>
                </Fragment>
              ))}
            </div>
          </Link>
        ))
      ) : (
        <p>No projects offers found</p>
      )}
    </Fragment>
  );
};

export default ProjectOffers;
