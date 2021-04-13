import React, { Fragment, useContext, useState, useEffect } from 'react';

import EmptyProfile from '../layout/images/blank-profile-picture.png';
import AuthContext from '../../context/auth/authContext';
import ProfileContext from '../../context/profile/profileContext';

const UserProfile = () => {
  const authContext = useContext(AuthContext);

  const { user, isAuthenticated, loadUser } = authContext;

  const profileContext = useContext(ProfileContext);
  const {
    addExperience,
    deleteExperience,
    addSkill,
    deleteSkill,
    experiences,
    skills,
  } = profileContext;

  const [experience, setExperience] = useState('');

  const [skill, setSkill] = useState('');

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
    frontEnd: user.consultantDetails.frontend,
    backEnd: user.consultantDetails.backend,
    forHire: user.consultantDetails.availableForHire,
    salary: user.consultantDetails.minPrice,
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
    frontEnd,
    backEnd,
    forHire,
    salary,
  } = currentUser;

  const handleChangeExperience = (e) => {
    setExperience(e.target.value);
  };

  const handleAddExperience = (e) => {
    e.preventDefault();
    addExperience(experience);
    setExperience('');
  };

  const handleRemoveExperience = (index) => (e) => {
    e.preventDefault();
    deleteExperience(index);
    setExperience('');
  };

  const handleChangeSkill = (e) => {
    setSkill(e.target.value);
  };

  const handleAddSkills = (e) => {
    e.preventDefault();
    addSkill(skill);
    setSkill('');
  };

  const handleRemoveSkill = (index) => (e) => {
    e.preventDefault();
    deleteSkill(index);
    setSkill('');
  };

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
      <div className='main-body'>
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
                  <h6>{user.contact.country}</h6>
                </div>
              </div>
            </div>

            {user.role === 'CONSULTANT' ? (
              <Fragment>
                <div className='card mt-3'>
                  {/* ToDo: Byt ut ikonerna mot fungerande checkboxes */}
                  <ul className='list-group list-group-flush'>
                    <li className='list-group-item d-flex justify-content-between align-items-center flex-wrap'>
                      <h6 className='mb-0'>FrontEnd: </h6>
                      {frontEnd ? (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi-success bi-check-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z' />
                        </svg>
                      ) : (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi-danger bi-x-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z' />
                        </svg>
                      )}
                    </li>
                    <li className='list-group-item d-flex justify-content-between align-items-center flex-wrap'>
                      <h6 className='mb-0'>BackEnd: </h6>
                      {backEnd ? (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi bi-check-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z' />
                        </svg>
                      ) : (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi bi-x-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z' />
                        </svg>
                      )}
                    </li>
                    <li className='list-group-item d-flex justify-content-between align-items-center flex-wrap'>
                      <h6 className='mb-0'>Avalible for hire: </h6>
                      {forHire ? (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi bi-check-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.235.235 0 0 1 .02-.022z' />
                        </svg>
                      ) : (
                        <svg
                          xmlns='http://www.w3.org/2000/svg'
                          width='16'
                          height='16'
                          fill='currentColor'
                          className='bi bi-x-square'
                          viewBox='0 0 16 16'
                        >
                          <path d='M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z' />
                          <path d='M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z' />
                        </svg>
                      )}
                    </li>
                    <li className='list-group-item d-flex justify-content-between align-items-center flex-wrap'>
                      <h6 className='mb-0'>Min Salary: </h6>
                      <input
                        className='form-control-sm'
                        type='text'
                        name='salary'
                        value={salary}
                        onChange={onChange}
                      />
                      SEK
                    </li>
                  </ul>
                </div>
              </Fragment>
            ) : (
              <Fragment></Fragment>
            )}
          </div>

          <div className='col md-8'>
            <div className='card mb-3'>
              <div className='card-body'>
                <h6>User Details</h6>
                <div className='form-row'>
                  {user.role === 'CLIENT' ? (
                    <Fragment>
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
                    </Fragment>
                  ) : (
                    <Fragment></Fragment>
                  )}
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
            {user.role === 'CONSULTANT' ? (
              <Fragment>
                <div className='row gutters-sm'>
                  <div className='col-sm-6 mb-3'>
                    <div className='card h-100'>
                      <div className='card-body'>
                        <h6 className='d-flex align-items-center mb-3'>
                          Previous Experience:
                        </h6>
                        <div className='form-row'>
                          <div className='input-group mb-3'>
                            <input
                              type='text'
                              className='form-control'
                              name='experience'
                              value={experience}
                              placeholder='Add A Previous Experience'
                              onChange={handleChangeExperience}
                              aria-label='Add A Previous Experience'
                              aria-describedby='button-addon2'
                            />
                            <button
                              className='btn btn-outline-secondary'
                              type='button'
                              onClick={handleAddExperience}
                              id='button-addon2'
                            >
                              Add
                            </button>
                          </div>
                        </div>
                        <div className='form-row'>
                          <ul className='list-group list-group-horizontal'>
                            {experiences.map((item, index) => (
                              <li
                                className='list-group-item'
                                key={index}
                                id={'experience' + index}
                              >
                                {item}{' '}
                                <span item={item.name}>
                                  <button
                                    className='btn btn-sm'
                                    onClick={handleRemoveExperience(index)}
                                  >
                                    x
                                  </button>
                                </span>
                              </li>
                            ))}
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className='col-sm-6 mb-3'>
                    <div className='card h-100'>
                      <div className='card-body'>
                        <h6 className='d-flex align-items-center mb-3'>
                          Skills
                        </h6>
                        <div className='form-row'>
                          <div className='input-group mb-3'>
                            <input
                              type='text'
                              className='form-control'
                              name='skill'
                              value={skill}
                              onChange={handleChangeSkill}
                              placeholder='Add A Special Skill'
                              aria-label='Add A Special Skill'
                              aria-describedby='button-addon2'
                            />
                            <button
                              className='btn btn-outline-secondary'
                              onClick={handleAddSkills}
                              type='button'
                              id='button-addon2'
                            >
                              Add
                            </button>
                          </div>
                        </div>
                        <div className='form-row'>
                          <ul className='list-group list-group-horizontal'>
                            {skills.map((item, index) => (
                              <li
                                className='list-group-item'
                                key={index}
                                id={'skill' + index}
                              >
                                {item}{' '}
                                <span item={item.name}>
                                  <button
                                    className='btn btn-sm'
                                    onClick={handleRemoveSkill(index)}
                                  >
                                    x
                                  </button>
                                </span>
                              </li>
                            ))}
                          </ul>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Fragment>
            ) : (
              <Fragment></Fragment>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserProfile;
