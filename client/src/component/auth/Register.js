import React, { useState } from 'react';
import ConsultantForm from '../forms/ConsultantForm';
import CompanyForm from '../forms/CompanyForm';

const Register = () => {
  const [user, setUser] = useState({
    desiredRole: '',
  });

  const { desiredRole } = user;

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  return (
    <div className='container' style={{ maxWidth: '750px' }}>
      <h1>Create New Account</h1>
      <div className='form-check'>
        <input
          className='form-check-input'
          type='radio'
          name='desiredRole'
          value='consultant'
          checked={desiredRole === 'consultant'}
          onChange={onChange}
        />

        <label className='form-check-label' htmlFor='consultant'>
          {' '}
          Consultant{' '}
        </label>
      </div>
      <div className='form-check'>
        <input
          className='form-check-input'
          type='radio'
          name='desiredRole'
          value='company'
          checked={desiredRole === 'company'}
          onChange={onChange}
        />
        <label className='form-check-label' htmlFor='company'>
          {' '}
          Company{' '}
        </label>
      </div>
      <form>
        {desiredRole === 'consultant' ? <ConsultantForm /> : <CompanyForm />}
      </form>

      <div className='form-group mt-4'>
        <input
          className='btn-lg btn-block btn-primary'
          type='submit'
          value='Submit'
        />
      </div>
    </div>
  );
};

export default Register;
