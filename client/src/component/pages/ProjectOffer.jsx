import axios from 'axios';
import { Fragment, useEffect, useState } from 'react';

const ProjectOffer = (props) => {
  const [offer, setOffer] = useState([]);

  useEffect(() => {
    if (props.location.state) {
      setOffer(props.location.state.projectOffer);
      console.log(offer);
    }
  }, []);

  const onAccept = () => {
    axios
      .post('/api/projectOffer/accepted/' + offer.projectOfferId)
      .then((res) => {
        setOffer({ ...offer, accepted: res.data.accepted });
      })
      .catch((error) => {
        console.log(console.log(error));
      });
  };

  const onReject = () => {
    axios
      .post('/api/projectOffer/rejected/' + offer.projectOfferId)
      .then((res) => {
        setOffer({ ...offer, rejected: res.data.rejected });
      })
      .catch((error) => {
        console.log(console.log(error));
      });
  };

  console.log(offer);

  return (
    <Fragment>
      {offer ? (
        <Fragment>
          <p>Project name: {offer.projectName}</p>
          <p>Start date: {offer.startDate}</p>
          <p>End date: {offer.endDate}</p>
          <p>Workload: {offer.workLoad}</p>
          <p>Description: {offer.description}</p>
          <p>Located: {offer.located}</p>
          <p>Distance work: {offer.distanceWork ? 'Yes' : 'No'}</p>
          <p>Company hardware: {offer.companyHardware ? 'Yes' : 'No'}</p>
          <p>Contact name: {offer.contactName}</p>
          <p>Contact email: {offer.contactEmail}</p>
          <p>Contact phone number: {offer.contactPhoneNumber}</p>

          {offer.accepted || offer.rejected ? (
            offer.accepted ? (
              <p>Offer is accepted</p>
            ) : (
              <p>Offer is rejected</p>
            )
          ) : (
            <div>
              <button onClick={onAccept}>Accept</button>
              <button onClick={onReject}>Reject</button>
            </div>
          )}
        </Fragment>
      ) : (
        <p>No found Offer</p>
      )}
    </Fragment>
  );
};

export default ProjectOffer;
