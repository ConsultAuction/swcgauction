import React, { useState } from 'react';
import ReactFlagsSelect from 'react-flags-select';
import axios from 'axios';

const CompanyForm = (props) => {
  const [user, setUser] = useState({
    firstName: '',
    lastName: '',
    companyName: '',
    email: '',
    role: 'client',
    password: '',
    password2: '',
    address: '',
    zipCode: '',
    city: '',
    phoneNr: '',
  });

  const {
    firstName,
    lastName,
    companyName,
    email,
    role,
    password,
    password2,
    address,
    zipCode,
    city,
    phoneNr,
  } = user;

  const [selected, setSelected] = useState('');

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const createClient = async (formData) => {
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      await axios.post('/api/user/client', formData, config);
      props.history.push('/registerSucess');
    } catch (error) {
      console.log(error);
    }
  };

  const onSubmit = (e) => {
    e.preventdefault();
    createClient({
      companyName,
      firstName,
      lastName,
      email,
      password,
      address,
      zipCode,
      city,
      selected,
      phoneNr,
    });
  };

  return (
    <div>
      <form onSubmit={onSubmit}>
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
        <div className='form-row'>
          <div className='col'>
            <label htmlFor='email'>Email Address</label>
            <input
              className='form-control'
              type='email'
              name='email'
              value={email}
              onChange={onChange}
            />
          </div>
        </div>

        <div className='form-row'>
          <div className='col'>
            <label htmlFor='password'>Password</label>
            <input
              className='form-control'
              type='password'
              name='password'
              value={password}
              onChange={onChange}
              required
            />
          </div>
          <div className='col'>
            <label htmlFor='password2'>Confirm Password</label>
            <input
              className='form-control'
              type='password'
              name='password2'
              value={password2}
              onChange={onChange}
              required
            />
          </div>
        </div>
        <div className='form-row'>
          <div className='col'>
            <label htmlFor='address'>Address</label>
            <input
              className='form-control'
              type='text'
              name='address'
              value={address}
              onChange={onChange}
              required
            />
          </div>
        </div>
        <div className='form-row'>
          <div className='col'>
            <label htmlFor='zipCode'>Postal Code</label>
            <input
              className='form-control'
              type='text'
              name='zipCode'
              value={zipCode}
              onChange={onChange}
              required
            />
          </div>
          <div className='col'>
            <label htmlFor='city'>City</label>
            <input
              className='form-control'
              type='text'
              name='city'
              value={city}
              onChange={onChange}
              required
            />
          </div>
          <div className='col'>
            <label htmlFor='country'>Country</label>
            <ReactFlagsSelect
              selected={selected}
              onSelect={(code) => setSelected(code)}
            />
          </div>
        </div>
        {/* ToDo: Snygga till fältet med en landskodslista */}

        <div className='form-row'>
          <label htmlFor='phonenr'>Phone Number</label>
          <input
            className='form-control'
            type='text'
            name='phoneNr'
            value={phoneNr}
            onChange={onChange}
            required
          />
        </div>
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

export default CompanyForm;
