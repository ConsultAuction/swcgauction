import React, { Fragment, useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import AuctionContext from '../../context/auction/AuctionContext';
import Countdown from '../Countdown';
import ProjectForm from '../forms/ProjectForm';
import SearchbarDropdown from './SearchbarDropdown';
import axios from 'axios';

const Project = () => {
  const auctionContext = useContext(AuctionContext);
  const { loadAllProject, allProjects } = auctionContext;
  const [clientId, setClientId] = useState(localStorage.getItem('userid'));

  useEffect(() => {
    loadAllProject(clientId);
    console.log(allProjects);
  }, [clientId]);
  return (
    <Fragment>
      {/* <Countdown /> */}
      <div>
        {allProjects ? (
          allProjects.map((project) => (
            <Link
              to={{
                pathname: '/createProject',
                state: {
                  linkedProject: project,
                },
              }}
            >
              <p>{project.projectName}</p>
            </Link>
          ))
        ) : (
          <p>No projects found</p>
        )}
      </div>
    </Fragment>
  );
};

export default Project;
