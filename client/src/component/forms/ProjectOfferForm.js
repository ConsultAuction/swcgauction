import { Fragment, useState } from "react";
import { useForm } from "react-hook-form";

const ProjectOfferForm = (props) =>{

  const [isNew, setIsNew] = useState(true);
  const [projectOffer, setProjectOffer] = useState(null);

  const { register, handleSubmit, errors } = useForm();


  const onSubmit = data =>{
    console.log(data, isNew);
  }

  return (
      <form onSubmit={handleSubmit(onSubmit)}>
          <label htmlFor='consultantId'>consultantId</label>
          <input
            type='text'
            name='consultantId'
            defaultValue={isNew ? '' : projectOffer.projectName}
          />

          <label htmlFor='projectId'>projectId</label>
          <input
            type='text'
            name='projectId'
            defaultValue={isNew ? '' : projectOffer.projectName}
          />

          <label htmlFor='startTime'>startTime</label>
          <input
            type='date'
            name='startTime'
            defaultValue={isNew ? '' : projectOffer.projectName}
            
          />

          <label htmlFor='bids'>bids</label>
          <input
            type='number'
            min='0'
            name='bids'
            defaultValue={isNew ? '' : projectOffer.projectName}
          />
      </form>
  );
};
export default ProjectOfferForm