import { Fragment, useState } from "react";

const ProjectOfferForm = () =>{

    const [projectOffer, setProjectOffer] = useState({
      consultantId: '',
      projectId: '',
      startTime: '',
      bids: 0.0

    });

    return (
        <Fragment>
            <label htmlFor='consultantId'>consultantId</label>
            <input
              type='text'
              name='consultantId'
              value={consultantId}
              onChange={onChange}
            />

            <label htmlFor='projectId'>projectId</label>
            <input
              type='text'
              name='projectId'
              value={projectId}
              onChange={onChange}
            />

            <label htmlFor='startTime'>startTime</label>
            <input
              type='date'
              name='startTime'
              value={startTime}
              onChange={onChange}
            />

            <label htmlFor='bids'>bids</label>
            <input
              type='number'
              min='0'
              name='bids'
              value={bids}
              onChange={onChange}
            />
        </Fragment>
    )

};
export default ProjectOfferForm