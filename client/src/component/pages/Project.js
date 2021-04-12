import React, { Fragment, useContext, useEffect } from 'react';
import { Link } from 'react-router-dom';
import AuctionContext from '../../context/auction/AuctionContext';
import Countdown from '../Countdown';
import ProjectForm from '../forms/ProjectForm';

const Project = () => {
  const auctionContext = useContext(AuctionContext);
  const {loadAllProject, allProjects} = auctionContext;

  useEffect(() => {

    if(allProjects === null) {
      loadAllProject(localStorage.getItem('userid'));
      console.log(allProjects);
    }

  }, [allProjects]);
  return (
    <Fragment>
      <Link to='/createProject'><p>Create new project</p></Link>
      <Countdown />

      <div>
        {allProjects ? (
          allProjects.map((project) => (
            <p></p>
          ))
        ): (
          <p>No projects found</p>
        )}
      </div>
    </Fragment>
    
  );
};

export default Project;
