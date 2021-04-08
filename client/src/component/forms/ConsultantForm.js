import React, { Fragment, useState, useEffect } from 'react';
import ReactFlagsSelect from 'react-flags-select';

const ConsultantForm = () => {
  const initialState = {
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
    experiences: [],
    languages: '',
    skills: [],
  };

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
    experiences,
    languages,
    skills,
  } = initialState;

  const [consultant, setConsultant] = useState(() => {
    return initialState;
  })
  const [experience, setExperience] = useState('');
  const [skill, setSkill] = useState('');

  const [selected, setSelected] = useState('');

  const handleChangeExperience = (e) => {
    setExperience(e.target.value);
  };

  const handleAddExperience = () => {
     const newConsultant = { ...consultant };
    newConsultant.experiences = consultant.experiences.concat(experience);
    setExperience('');
    setConsultant(newConsultant);
  };

  const handleRemoveExperience = idx => () => {
    const newConsultant = { ...consultant };
    newConsultant.experiences = consultant.experiences.filter((s, sidx) => idx !== sidx);
    setConsultant(newConsultant);
  };

  const handleChangeSkill = (e) => {
    setSkill(e.target.value);
  };

  const handleAddSkills = () => {
    const newConsultant = { ...consultant };
    newConsultant.skills = consultant.skills.concat(skill);
    setSkill('');
    setConsultant(newConsultant);
  };

  const handleRemoveSkill = () => {};

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
            required
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
            <label class='form-check-label' htmlFor='frontEnd'>
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
            <label class='form-check-label' htmlFor='backEnd'>
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
            <label class='form-check-label' htmlFor='avalibleHire'>
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
            {consultant.experiences.map((item, index) => (
              <li
                className='list-group-item'
                key={index}
                id={'experience' + index}
              >
                {item}
                <button
                  className='btn btn-danger btn-sm'
                  onClick={handleRemoveExperience(index)}
                >
                  x
                </button>
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
            {consultant.skills.map((item, index) => (
              <li className='list-group-item' key={index} id={'skill' + index}>
                {item}
                <button
                  className='btn btn-danger btn-sm'
                  onClick={handleRemoveSkill}
                >
                  x
                </button>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </Fragment>
  );
};

export default ConsultantForm;
