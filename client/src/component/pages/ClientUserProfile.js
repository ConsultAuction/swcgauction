import React, { useContext, useState, useEffect } from 'react';
import EmptyProfile from '../layout/images/blank-profile-picture.png';
import AuthContext from '../../context/auth/authContext';
import axios from 'axios';

const ClientUserProfile = () => {
  const authContext = useContext(AuthContext);

  const { user, isAuthenticated, loadUser } = authContext;
  const [newPassword, setNewPassword] = useState('');
  const [newPasswordAgain, setNewPasswordAgain] = useState();
  const [password, setPassword] = useState({
    password: '',
  });

  const [currentUser, setCurrentUser] = useState({
    companyName: user.companyName,
    firstName: user.firstName,
    lastName: user.lastName,
    email: user.email,
    role: user.role,
    address: user.contact.address,
    zipCode: user.contact.zipCode,
    city: user.contact.city,
    country: user.contact.country,
    phoneNumber: user.contact.phoneNumber,
  });

  const {
    companyName,
    firstName,
    lastName,
    email,
    role,
    address,
    zipCode,
    city,
    country,
    phoneNumber,
  } = currentUser;

  const onChange = (e) =>
    setCurrentUser({ ...currentUser, [e.target.name]: e.target.value });

  useEffect(() => {
    if (!isAuthenticated) {
      localStorage.getItem('userid');
      loadUser();
    }
  }, [loadUser, isAuthenticated]);

  const onSubmit = (e) => {
    e.preventDefault();
    console.log(e);
    axios.put('/api/user/client/' + user.userId, currentUser).catch((error) => {
      console.log(error);
    });
  };

  const submitPassword = (e) => {
    e.preventDefault();
    if (newPassword === newPasswordAgain) {
      setPassword({ ...password, password: newPassword });
      axios
        .put('/api/user/password/' + user.userId, password)
        .catch((error) => {
          console.log(error);
        });
    }
  };

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
                <div className='form-body'></div>
                <form onSubmit={onSubmit}>
                  <h6>User Details</h6>
                  <div className='form-row'>
                    <div className='col-sm-3'>
                      <label htmlFor='companyName'>Company Name</label>
                      <input
                        className='form-control'
                        type='text'
                        name='companyName'
                        value={companyName ? companyName : ''}
                        onChange={onChange}
                      />
                    </div>

                    <div className='col-sm-3'>
                      <label htmlFor='firstName'>First Name</label>
                      <input
                        className='form-control'
                        type='text'
                        name='firstName'
                        value={firstName ? firstName : ''}
                        onChange={onChange}
                      />
                    </div>
                    <div className='col-sm-3'>
                      <label htmlFor='lastName'>Last Name</label>
                      <input
                        className='form-control'
                        type='text'
                        name='lastName'
                        value={lastName ? lastName : ''}
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
                    <label htmlFor='address'>Address</label>
                    <input
                      className='form-control'
                      type='text'
                      name='address'
                      value={address ? address : ''}
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
                        value={zipCode ? zipCode : ''}
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
                        value={city ? city : ''}
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
                        value={country ? country : ''}
                        onChange={onChange}
                        required
                      />
                    </div>
                  </div>
                  <div className='form-row'>
                    <label htmlFor='phoneNumber'>Phone Number</label>
                    <input
                      className='form-control'
                      type='text'
                      name='phoneNumber'
                      value={phoneNumber ? phoneNumber : ''}
                      onChange={onChange}
                      required
                    />
                  </div>

                  <input
                    className='btn-block mt-2'
                    type='submit'
                    value='Save'
                  />
                </form>
              </div>
              <form onSubmit={submitPassword}>
                <div className='form-body mb-3 ml-3 mr-3'>
                  <div className='form-row'>
                    <div className='col'>
                      <label htmlFor='password'>New Password</label>
                      <input
                        className='form-control'
                        type='password'
                        name='password'
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                        required
                      />
                    </div>
                    <div className='col'>
                      <label htmlFor='password2'>New Password Again</label>
                      <input
                        className='form-control'
                        type='password'
                        name='password2'
                        value={newPasswordAgain}
                        onChange={(e) => {
                          setNewPasswordAgain(e.target.value);
                        }}
                        required
                      />
                    </div>
                  </div>
                  <input
                    className='btn-block mt-2'
                    type='submit'
                    value='Save new password'
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ClientUserProfile;
