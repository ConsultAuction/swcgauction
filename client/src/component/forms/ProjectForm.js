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

            <label htmlFor='startDate'>Start date</label>
            <input
              type='date'
              name='startDate'
              value={startDate}
              onChange={onChange}
            />
            
        </Fragment>
    )

};
export default ProjectForm
