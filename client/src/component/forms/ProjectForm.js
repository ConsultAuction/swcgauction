import React, { useState, useEffect, useContext } from 'react';
import { useForm } from 'react-hook-form';
import AuctionContext from '../../context/auction/AuctionContext';

const ProjectForm = (props) => {
  const auctionContext = useContext(AuctionContext);
  const { saveProject } = auctionContext;

  const [isNew, setIsNew] = useState(true);
  const [isDistance, setIsDistance] = useState(false);
  const [isCompanyHw, setIsComanyHw] = useState(false);
  const [project, setProject] = useState(null);

  const { register, handleSubmit, errors } = useForm();

  useEffect(() => {
    if (props.location.state != null) {
      setProject(props.location.state.linkedProject);
      setIsNew(false);
    }

    if (project) {
      setIsDistance(project.distanceWork);
      setIsComanyHw(project.companyHardware);
    }
  }, [project, isNew, props.location.state]);

  const onSubmit = (data) => {
    console.log(data, isNew);
    saveProject(data, isNew);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      {!isNew && (
        <input
          type='hidden'
          name='projectId'
          value={project.projectId}
          ref={register}
        />
      )}

      <div className='container' style={{ maxWidth: '750px' }}>
        <label htmlFor='projectName'>Project name</label>
        <input
          type='text'
          className='form-control'
          name='projectName'
          defaultValue={isNew ? '' : project.projectName}
          ref={register({ required: true })}
        />
        <label htmlFor='startDate'>Start date</label>
        <input
          type='date'
          className='form-control'
          name='startDate'
          defaultValue={isNew ? '' : project.startDate}
          ref={register({ required: true })}
        />
        <label htmlFor='endDate'>End date</label>
        <input
          type='date'
          className='form-control'
          name='endDate'
          defaultValue={isNew ? '' : project.endDate}
          ref={register({ required: true })}
        />
        <label htmlFor='workLoad'>Work load</label>
        <input
          type='number'
          className='form-control'
          min='0'
          max='100'
          name='workLoad'
          defaultValue={isNew ? '' : project.workLoad}
          ref={register({ required: true })}
        />
        <label htmlFor='description'>Description</label>
        <input
          type='text'
          className='form-control'
          name='description'
          defaultValue={isNew ? '' : project.description}
          ref={register({ required: true })}
        />
        <label htmlFor='located'>Location</label>
        <input
          type='text'
          className='form-control'
          name='located'
          defaultValue={isNew ? '' : project.located}
          ref={register({ required: true })}
        />
        <label htmlFor='distanceWork'>Distance work: </label>{' '}
        <input
          type='checkbox'
          name='distanceWork'
          defaultChecked={isNew ? '' : project.distanceWork}
          ref={register}
        />{' '}
        <label htmlFor='companyHardware'>Company hardware: </label>{' '}
        <input
          type='checkbox'
          name='companyHardware'
          defaultChecked={isNew ? '' : project.companyHardware}
          ref={register}
        />
        <div className='form-row'>
          <label htmlFor='contactName'>Contact name</label>
          <input
            type='text'
            className='form-control'
            name='contactName'
            defaultValue={isNew ? '' : project.contactName}
            ref={register({ required: true })}
          />
          <label htmlFor='contactEmail'>Contact email</label>
          <input
            type='text'
            className='form-control'
            name='contactEmail'
            defaultValue={isNew ? '' : project.contactEmail}
            ref={register({ required: true })}
          />
          <label htmlFor='contactPhoneNumber'>Contact phoneNumber</label>
          <input
            type='text'
            className='form-control'
            name='contactPhoneNumber'
            defaultValue={isNew ? '' : project.contactPhoneNumber}
            ref={register({ required: true })}
          />
        </div>
        <input
          type='hidden'
          name='userId'
          readOnly
          value={localStorage.getItem('userid')}
          ref={register}
        />
        <button className='btn btn-success' type='submit'>
          Save project
        </button>
      </div>
    </form>
  );
};
export default ProjectForm;
