import React, { useContext, useState, useEffect } from 'react';
import EmptyProfile from '../layout/images/blank-profile-picture.png';
import AuthContext from '../../context/auth/authContext';

const ClientUserProfile = () => {
  const authContext = useContext(AuthContext);

  const { user, isAuthenticated, loadUser } = authContext;

  const [currentUser, setCurrentUser] = useState({
    companyName: user.companyName,
    firstName: user.firstName,
    lastName: user.lastName,
    email: user.email,
    role: user.role,
    password: user.password,
    password2: user.password2,
    address: user.contact.address,
    zipCode: user.contact.zipCode,
    city: user.contact.city,
    country: user.contact.country,
    phoneNr: user.contact.phoneNumber,
  });

  const {
    companyName,
    firstName,
    lastName,
    email,
    role,
    password,
    password2,
    address,
    zipCode,
    city,
    country,
    phoneNr,
  } = currentUser;

  const onChange = (e) =>
    setCurrentUser({ ...currentUser, [e.target.name]: e.target.value });

  useEffect(() => {
    if (!isAuthenticated) {
      localStorage.getItem('userid');
      loadUser();
    }
  }, [loadUser, isAuthenticated]);

  return (
    <div className='container'>
      <div className='main-body mt-2'>
        <div className='row gutters-sm'>
          <div className='col-md-4 mb-3'>
            <div className='card'>
              <div className='card-body'>
                <img
                  src={EmptyProfile}
                  alt='EmptyProfile'
                  className='rounded-circle'
                  width='300'
                />
                <div className='d flex flex-colum align-items-center text-center'>
                  <h4>
                    {firstName} {lastName}
                  </h4>
                  <h5>{role}</h5>
                  <h6>{country}</h6>
                </div>
              </div>
            </div>
          </div>

          <div className='col md-8'>
            <div className='card mb-3'>
              <div className='card-body'>
                <h6>User Details</h6>
                <div className='form-row'>
                  <div className='col-sm-3'>
                    <label htmlFor='companyName'>Company Name</label>
                    <input
                      className='form-control'
                      type='text'
                      name='companyName'
                      value={companyName}
                      onChange={onChange}
                    />
                  </div>

                  <div className='col-sm-3'>
                    <label htmlFor='firstName'>First Name</label>
                    <input
                      className='form-control'
                      type='text'
                      name='firstName'
                      value={firstName}
                      onChange={onChange}
                    />
                  </div>
                  <div className='col-sm-3'>
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
                    <label htmlFor='password'>Current Password</label>
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
                    <label htmlFor='password2'>New Password</label>
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
                    <input
                      className='form-control'
                      type='text'
                      name='country'
                      value={country}
                      onChange={onChange}
                      required
                    />
                  </div>
                </div>
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
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ClientUserProfile;
