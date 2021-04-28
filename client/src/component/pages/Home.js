import React, { useEffect, useContext, Fragment } from 'react';
import AuthContext from '../../context/auth/authContext';

const Home = () => {
  const authContext = useContext(AuthContext);

  const { user, loadUser, isAuthenticated, loading } = authContext;

  useEffect(() => {
    if (!isAuthenticated) {
      localStorage.getItem('userid');
      loadUser();
    }
  }, [loadUser, isAuthenticated, user]);
  return (
    <div>
      This is the Home Page.{' '}
      {isAuthenticated && !loading && user !== null ? (
        <Fragment>You are logged in as a {user.role}</Fragment>
      ) : (
        <Fragment></Fragment>
      )}
    </div>
  );
};

export default Home;
