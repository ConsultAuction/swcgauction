import React, { Fragment, useState, useContext } from 'react';
import ReactFlagsSelect from 'react-flags-select';
import ProfileContext from '../../context/profile/profileContext';

const ConsultantForm = () => {
  const profileContext = useContext(ProfileContext);
  const {
    addExperience,
    deleteExperience,
    addSkill,
    deleteSkill,
    experiences,
    skills,
  } = profileContext;

  const [consultant, setConsultant] = useState({
    firstName: '',
    lastName: '',
    email: '',
    role: 'consultant',
    password: '',
    password2: '',
    address: '',
    zipCode: '',
    city: '',
    phoneNr: '',
    frontEnd: false,
    backEnd: false,
    forHire: false,
    salary: '',
  });

  const {
    firstName,
    lastName,
    email,
    role,
    password,
    password2,
    address,
    zipCode,
    city,
    phoneNr,
    frontEnd,
    backEnd,
    forHire,
    salary,
  } = consultant;

  const [experience, setExperience] = useState('');
  const [skill, setSkill] = useState('');
  const [selected, setSelected] = useState('');

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
    setConsultant({ ...consultant, [e.target.name]: e.target.value });

  return (
    <Fragment>
      <div className='form-group'>
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
          />
        </div>
        <div className='col'>
          <label htmlFor='password2'>Confirm Password</label>
          <input
            className='form-control'
            type='password2'
            name='password2'
            value={password2}
            onChange={onChange}
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
        />
      </div>
      <div className='form-group'>
        <div className='form-row'>
          <div className='form-check form-check-inline'>
            <input
              className='form-check-input'
              type='checkbox'
              name='frontEnd'
              value={frontEnd}
              onChange={onChange}
            />
            <label className='form-check-label' htmlFor='frontEnd'>
              Front End
            </label>
          </div>{' '}
          <div className='form-check form-check-inline'>
            <input
              className='form-check-input'
              type='checkbox'
              name='backEnd'
              value={backEnd}
              onChange={onChange}
            />
            <label className='form-check-label' htmlFor='backEnd'>
              Back End
            </label>
          </div>{' '}
          <div className='form-check form-check-inline'>
            <input
              className='form-check-input'
              type='checkbox'
              name='forHire'
              value={forHire}
              onChange={onChange}
            />
            <label className='form-check-label' htmlFor='avalibleHire'>
              Avalible For Hire
            </label>
          </div>
          <div className='col'>
            <label htmlFor='minSalary'>Minimum Salary</label>
            <input
              type='text'
              className='form-control'
              name='salary'
              value={salary}
              onChange={onChange}
              placeholder='SEK'
            />
          </div>
        </div>
        <div className='form-row'>
          <label htmlFor='skills'>Previous Experience:</label>
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
                {item}
                <span item={item.name}>
                  <button
                    className='btn btn-danger btn-sm'
                    onClick={handleRemoveExperience(index)}
                  >
                    x
                  </button>
                </span>
              </li>
            ))}
          </ul>
        </div>
        <div className='form-row'>
          <label htmlFor='skills'>Programming Language:</label>
          <div className='input-group mb-3'>
            <select
              className='form-select'
              aria-label='Add a Programming Language'
            >
              <option selected>Select a Programming Language</option>
              <option value='1'>C#</option>
              <option value='2'>C++</option>
              <option value='3'>Java</option>
              <option value='4'>Javascript</option>
            </select>

            <button
              className='btn btn-outline-secondary'
              type='button'
              id='button-addon2'
            >
              Add
            </button>
          </div>
        </div>

        <div className='form-row'>
          <label htmlFor='skills'>Special Skills:</label>
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
              <li className='list-group-item' key={index} id={'skill' + index}>
                {item}
                <span item={item.name}>
                  <button
                    className='btn btn-danger btn-sm'
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
    </Fragment>
  );
};

export default ConsultantForm;
