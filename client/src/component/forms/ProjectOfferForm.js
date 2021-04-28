import { Fragment, useContext, useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import AuctionContext from '../../context/auction/AuctionContext';

const ProjectOfferForm = (props) => {
  const auctionContext = useContext(AuctionContext);
  const { sendOffer } = auctionContext;
  const [isNew, setIsNew] = useState(true);

  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    sendOffer(data);
  };

  useEffect(() => {
    if (props.location.state != null) {
      setIsNew(false);
    }
  });

  console.log(props);

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label htmlFor='consultantId'>consultantId</label>
      <input
        type='text'
        name='consultantId'
        defaultValue={isNew ? '' : props.location.state.userId}
        ref={register({ required: true })}
      />

      <label htmlFor='projectId'>projectId</label>
      <input
        type='text'
        name='projectId'
        defaultValue={isNew ? '' : props.location.state.projectId}
        ref={register({ required: true })}
      />

      <label htmlFor='startTime'>startTime</label>
      <input
        type='date'
        name='startTime'
        defaultValue={isNew ? '' : ''}
        ref={register({ required: true })}
      />

      <label htmlFor='bids'>bids</label>
      <input
        type='number'
        min='0'
        name='bids'
        defaultValue={isNew ? '' : ''}
        ref={register({ required: true })}
      />
      <button className='btn btn-success' type='submit'>
        Send project offer
      </button>
    </form>
  );
};
export default ProjectOfferForm;
