import React, { Fragment, useState } from 'react';
import ReactFlagsSelect from 'react-flags-select';
import { useForm } from "react-hook-form";

function createArrayWithNumbers(length) {
  return Array.from({ length }, (_, k) => k);
}

const ConsultantForm = () => {
  const [user, setUser] = useState({
    experience: [""],
    skills: [""],
  });

  const {
    password2,
    experience,
    skills,
  } = user;


  const [selected, setSelected] = useState('');

  const { register, handleSubmit, errors } = useForm();

  const [size, setSize] = useState(1);

  const onSubmit = (data) => {
    console.log(data);
  };

  const onFormSubmit  = data => {
    console.log(data)
  };

  const onErrors = errors => console.error(errors);

  const handleExperienceNameChange = idx => evt => {
    /* console.log(evt.target.value);
    const value = evt.target.value;
    const newExperience = experience.map((experience, sidx) => {
      if (idx !== sidx) return experience;
       return { value };
    });
 */
    experience[idx] = evt.target.value;

    setUser({ experience: experience });
  };

  const handleAddExperience = () => {
    setUser({
      experience: experience.concat([ "" ])
    });
    console.log(experience);
  };

  const handleRemoveExperience = idx => () => {
    
    setUser({
      experience: experience.filter((s, sidx) => idx !== sidx)
    });
  };

  return (
    <Fragment>
      <form
      onSubmit={handleSubmit(onFormSubmit, onErrors)}
      >
      <div className='form-group'>
        <div className='form-row'>
          <div className='col'>
            <label htmlFor='firstName'>First Name</label>
            <input
              className='form-control'
              type='text'
              name='firstName'
              ref={register}
            />
          </div>
          <div className='col'>
            <label htmlFor='lastName'>Last Name</label>
            <input
              className='form-control'
              type='text'
              name='lastName'
              ref={register}
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
            ref={register}
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
            ref={register}
          />
        </div>
        <div className='col'>
          <label htmlFor='password2'>Confirm Password</label>
          <input
            className='form-control'
            type='password'
            name='password2'
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
            ref={register}
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
            ref={register}
          />
        </div>
        <div className='col'>
          <label htmlFor='city'>City</label>
          <input
            className='form-control'
            type='text'
            name='city'
            ref={register}
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
          ref={register}
        />
      </div>
      <div className='form-group'>
        <div className='form-row'>
          <div className='form-check form-check-inline'>
            <input
              className='form-check-input'
              type='checkbox'
              name='frontEnd'
              ref={register}
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
              ref={register}
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
              ref={register}
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
              placeholder='SEK'
              ref={register}
            />
          </div>
        </div>
 
        {createArrayWithNumbers(size).map((number) => {
          return (



          <div className='form-row, input-group mb-3'
          key={number}>
            <div>
                <input
                  name={`experience[${number}]`}
                  placeholder="experience"
                  ref={register({ required: true })}
                />
            </div>
              <button
                type="button"
                onClick={() => setSize(size + 1)}
                className='btn btn-outline-secondary'
              >
                -
              </button>
          </div>
          );
          })}
        <button
          type="button"
          onClick={() => setSize(size + 1)}
          className='btn btn-outline-secondary'
        >
          Add Shareholder
        </button>





        <div className='form-row'>
          <label htmlFor='skills'>Special Skills:</label>
          <div className='input-group mb-3'>
            <input
              type='text'
              className='form-control'
              name='skills'
              placeholder='Add A Special Skill'
              aria-label='Add A Special Skill'
              aria-describedby='button-addon2'
            />
            <button
              className='btn btn-outline-secondary'
              type='button'
              id='button-addon2'
            >
              Add
            </button>
          </div>
        </div>
      </div>
      <button className='btn btn-success' type='submit' >
              Save
            </button>
      </form>
    </Fragment>
  );
};

export default ConsultantForm;
