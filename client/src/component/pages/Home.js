import React, { useEffect, useContext } from 'react';
import AuthContext from '../../context/auth/authContext';

const Home = () => {
  const authContext = useContext(AuthContext);

  useEffect(() => {
    localStorage.getItem('userid');
    authContext.loadUser();
  }, []);
  return <div>This is the Home Page</div>;
};

export default Home;
