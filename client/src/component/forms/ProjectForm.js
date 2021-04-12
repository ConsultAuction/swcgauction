import React, { useState, useEffect, useContext } from 'react';
import { useForm } from 'react-hook-form';
import { useParams } from 'react-router';
import AuctionContext from '../../context/auction/AuctionContext';

const ProjectForm = () => {
  const auctionContext = useContext(AuctionContext);
  const { saveProject, project } = auctionContext;

  //project id if edit
  let params = useParams();
  let isNew = true;

  useEffect(() => {
   
  }, []);

  const { register, handleSubmit, errors } = useForm();

  const onSubmit = data =>{
    console.log(data);
    saveProject(data);
  }


  return (
    <form   onSubmit={handleSubmit(onSubmit)}>
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
          defaultValue={isNew ? '' : project.distanceWork}
          ref={register}
        />{' '}
        <label htmlFor='companyHardware'>Company hardware: </label>{' '}
        <input
          type='checkbox'
          name='companyHardware'
          defaultValue={isNew ? '' : project.companyHardware}
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
              readOnly value={localStorage.getItem('userid')}
              ref={register}
            />

      </div>
      <button className='btn btn-success' type='submit'>
              Save project
            </button>
    </form>
  );
};
export default ProjectForm;
