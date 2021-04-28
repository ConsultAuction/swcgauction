import axios from 'axios';
import React, { Fragment, useContext, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import AuctionContext from '../../context/auction/AuctionContext';

const Projects = (props) => {
  const [clientId] = useState(localStorage.getItem('userid'));
  const auctionContext = useContext(AuctionContext);
  const { loadAllProject, allProjects } = auctionContext;

  useEffect(() => {
    loadAllProject(clientId);
  }, [clientId]);

  return (
    <Fragment>
      <div>
        {allProjects ? (
          allProjects.map((project) => (
            <Link
              to={{
                pathname: '/projectOfferForm',
                state: {
                  userId: props.location.user.userId,
                  projectId: project.projectId,
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

export default Projects;
