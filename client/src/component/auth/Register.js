import React, { useState, useContext } from 'react';
import AuthContext from '../../context/auth/authContext';
import { useForm } from 'react-hook-form';

const Register = () => {
  const authContext = useContext(AuthContext);

  const { registerClient, registerConsultant } = authContext;

  const [role, setRole] = useState('CONSULTANT');
  const onChange = (e) => setRole(e.target.value);

  const { register, handleSubmit } = useForm();

  const onSubmit = (data) => {
    if (role === 'CONSULTANT') {
      console.log('CONSULTANT');
      registerConsultant(data);
    } else {
      console.log('CLIENT');
      registerClient(data);
    }
  };

  return (
    <div
      className='container rounded bg-light mt-2'
      style={{ maxWidth: '750px' }}
    >
      <form onSubmit={handleSubmit(onSubmit)}>
        <h1>Create New Account</h1>
        <div className='form-check'>
          <input
            className='form-check-input'
            type='radio'
            name='desiredRole'
            value='CONSULTANT'
            checked={role === 'CONSULTANT'}
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
            value='CLIENT'
            checked={role === 'CLIENT'}
            onChange={onChange}
          />
          <label className='form-check-label' htmlFor='client'>
            {' '}
            Client{' '}
          </label>
        </div>

        <div className='form-row'>
          <label htmlFor='Email'>Username / Email address</label>
          <input
            type='email'
            className='form-control'
            name='email'
            ref={register}
          />
        </div>
        <div className='form-row'>
          <label htmlFor='Password'>Password</label>
          <input
            type='password'
            className='form-control'
            name='password'
            ref={register}
          />
        </div>
        <div className='form-group mt-4'>
          <input
            className='btn-lg btn-block btn-primary'
            type='submit'
            value='Create New Account'
          />
        </div>
      </form>
    </div>
  );
};

export default Register;
