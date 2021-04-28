import React, { useState, useContext } from 'react';
import AuthContext from '../../context/auth/authContext';

const Register = () => {
  const [user, setUser] = useState({
    desiredRole: 'consultant',
    email: '',
    password: '',
  });

  const authContext = useContext(AuthContext);

  const { registerClient, registerConsultant } = authContext;

  const { desiredRole, email, password } = user;

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const onSubmit = (e) => {
    e.preventDefault();
    if (desiredRole === 'CLIENT') {
      registerClient({
        email,
        password,
      });
    } else {
      registerConsultant({
        email,
        password,
      });
    }
  };

  return (
    <div
      className='container rounded bg-light mt-2'
      style={{ maxWidth: '750px' }}
    >
      <form onSubmit={onSubmit}>
        <h1>Create New Account</h1>
        <div className='form-check'>
          <input
            className='form-check-input'
            type='radio'
            name='desiredRole'
            value='CONSULTANT'
            checked={desiredRole === 'CONSULTANT'}
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
            checked={desiredRole === 'CLIENT'}
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
            value={email}
            onChange={onChange}
            required
          />
        </div>
        <div className='form-row'>
          <label htmlFor='Password'>Password</label>
          <input
            type='password'
            className='form-control'
            name='password'
            value={password}
            onChange={onChange}
            required
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
