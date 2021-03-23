import { Fragment, useState } from "react";

const ProjectForm = () =>{

    const [project, setProject] = useState({
        projectName: '',
        startDate: '',
        endDate: '',
        workLoad: 100,
        description: '',
        located: '',
        distanceWork: false,
        companyHardware: false,
        contactName: '',
        contactEmail: '',
        contactPhoneNumber: '',
        userId: localStorage.getItem('userId')
    });

    return (
        <Fragment>
            <label htmlFor='projectName'>Project name</label>
            <input
              type='text'
              name='projectName'
              value={projectName}
              onChange={onChange}
            />

            <label htmlFor='startDate'>Start date</label>
            <input
              type='date'
              name='startDate'
              value={startDate}
              onChange={onChange}
            />

            <label htmlFor='endDate'>End date</label>
            <input
              type='date'
              name='endDate'
              value={endDate}
              onChange={onChange}
            />

            <label htmlFor='endDate'>End date</label>
            <input
              type='date'
              name='endDate'
              value={endDate}
              onChange={onChange}
            />

            <label htmlFor='workLoad'>Work load</label>
            <input
              type='number'
              min='0'
              max='100'
              name='workLoad'
              value={workLoad}
              onChange={onChange}
            />

            <label htmlFor='description'>Description</label>
            <input
              type='text'
              name='description'
              value={description}
              onChange={onChange}
            />

            <label htmlFor='located'>Location</label>
            <input
              type='text'
              name='located'
              value={located}
              onChange={onChange}
            />

            <label htmlFor='distanceWork'>Distance work</label>
            <input
              type='checkbox'
              name='distanceWork'
              value={distanceWork}
              onChange={onChange}
            />

            <label htmlFor='companyHardware'>Company hardware</label>
            <input
              type='checkbox'
              name='companyHardware'
              value={companyHardware}
              onChange={onChange}
            />
            
            <label htmlFor='contactName'>Contact name</label>
            <input
              type='text'
              name='contactName'
              value={contactName}
              onChange={onChange}
            />

            <label htmlFor='contactEmail'>Contact email</label>
            <input
              type='text'
              name='contactEmail'
              value={contactEmail}
              onChange={onChange}
            />

            <label htmlFor='contactPhoneNumber'>Contact phoneNumber</label>
            <input
              type='text'
              name='contactPhoneNumber'
              value={contactPhoneNumber}
              onChange={onChange}
            />
            
        </Fragment>
    )

};
export default ProjectForm
