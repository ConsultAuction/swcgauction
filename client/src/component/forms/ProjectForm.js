import React, { useState, useEffect, useContext } from 'react';
import AuthContext from '../../context/auth/authContext';

const ProjectForm = () => {
  const authContext = useContext(AuthContext);

  const { loadUser, isAuthenticated } = authContext;

  useEffect(() => {
    if (!isAuthenticated) {
      localStorage.getItem('userid');
      loadUser();
    }
  }, [loadUser, isAuthenticated]);

  const [project, setProject] = useState({
    projectName: '',
    startDate: '',
    endDate: '',
    workLoad: 100,
    description: '',
    located: '',
    distanceWork: false,
    companyHardware: false,
    contactName: '',
    contactEmail: '',
    contactPhoneNumber: '',
  });

  const {
    projectName,
    startDate,
    endDate,
    workLoad,
    description,
    located,
    distanceWork,
    companyHardware,
    contactName,
    contactEmail,
    contactPhoneNumber,
  } = project;

  const onChange = (e) =>
    setProject({ ...project, [e.target.name]: e.target.value });

  return (
    <div className='container' style={{ maxWidth: '750px' }}>
      <label htmlFor='projectName'>Project name</label>
      <input
        type='text'
        className='form-control'
        name='projectName'
        value={projectName}
        onChange={onChange}
      />
      <label htmlFor='startDate'>Start date</label>
      <input
        type='date'
        className='form-control'
        name='startDate'
        value={startDate}
        onChange={onChange}
      />
      <label htmlFor='endDate'>End date</label>
      <input
        type='date'
        className='form-control'
        name='endDate'
        value={endDate}
        onChange={onChange}
      />
      <label htmlFor='workLoad'>Work load</label>
      <input
        type='number'
        className='form-control'
        min='0'
        max='100'
        name='workLoad'
        value={workLoad}
        onChange={onChange}
      />
      <label htmlFor='description'>Description</label>
      <input
        type='text'
        className='form-control'
        name='description'
        value={description}
        onChange={onChange}
      />
      <label htmlFor='located'>Location</label>
      <input
        type='text'
        className='form-control'
        name='located'
        value={located}
        onChange={onChange}
      />
      <label htmlFor='distanceWork'>Distance work: </label>{' '}
      <input
        type='checkbox'
        name='distanceWork'
        value={distanceWork}
        onChange={onChange}
      />{' '}
      <label htmlFor='companyHardware'>Company hardware: </label>{' '}
      <input
        type='checkbox'
        name='companyHardware'
        value={companyHardware}
        onChange={onChange}
      />
      <div className='form-row'>
        <label htmlFor='contactName'>Contact name</label>
        <input
          type='text'
          className='form-control'
          name='contactName'
          value={contactName}
          onChange={onChange}
        />
        <label htmlFor='contactEmail'>Contact email</label>
        <input
          type='text'
          className='form-control'
          name='contactEmail'
          value={contactEmail}
          onChange={onChange}
        />
        <label htmlFor='contactPhoneNumber'>Contact phoneNumber</label>
        <input
          type='text'
          className='form-control'
          name='contactPhoneNumber'
          value={contactPhoneNumber}
          onChange={onChange}
        />
      </div>
    </div>
  );
};
export default ProjectForm;
