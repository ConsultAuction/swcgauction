import React, { useState } from 'react';

const Register = () => {
  const [user, setUser] = useState({
    firstName: '',
    lastName: '',
    companyName: '',
    email: '',
    role: '',
    password: '',
    password2: '',
  });

  const {
    firstName,
    lastName,
    companyName,
    email,
    role,
    password,
    password2,
  } = user;

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  return (
    <div className='container' style={{ maxWidth: '500px' }}>
      <h1>Create New Account</h1>
      <form>
        <div className='form-check'>
          <input
            className='form-check-input'
            type='radio'
            name='role'
            value='consultant'
            checked={role === 'consultant'}
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
            name='role'
            value='company'
            checked={role === 'company'}
            onChange={onChange}
          />
          <label className='form-check-label' htmlFor='company'>
            {' '}
            Company{' '}
          </label>
        </div>

        <div className='form-group'>
          {role === 'consultant' ? (
            <div className='form-row'>
              <div className='col'>
                <label htmlFor='firstName'>First Name</label>
                <input
                  className='form-control'
                  type='text'
                  name='firstName'
                  value={firstName}
                  onChange={onChange}
                />
              </div>
              <div className='col'>
                <label htmlFor='lastName'>Last Name</label>
                <input
                  className='form-control'
                  type='text'
                  name='lastName'
                  value={lastName}
                  onChange={onChange}
                />
              </div>
            </div>
          ) : (
            <div className='form-row'>
              <div className='col'>
                <label htmlFor='companyName'>Company Name</label>
                <input
                  className='form-control'
                  type='text'
                  name='companyName'
                  value={companyName}
                  onChange={onChange}
                />
              </div>
            </div>
          )}

          <label htmlFor='email'>Email Address</label>
          <input
            className='form-control'
            type='email'
            name='email'
            value={email}
            onChange={onChange}
          />
          <label htmlFor='password'>Password</label>
          <input
            className='form-control'
            type='password'
            name='password'
            value={password}
            onChange={onChange}
            required
          />
          <label htmlFor='password2'>Confirm Password</label>
          <input
            className='form-control'
            type='password2'
            name='password2'
            value={password2}
            onChange={onChange}
            required
          />
        </div>
        <div className='form-group mt-4'>
          <input
            className='btn-lg btn-block btn-primary'
            type='submit'
            value='Submit'
          />
        </div>
      </form>
    </div>
  );
};

export default Register;
