import React, { Fragment, useContext } from 'react';
import Logo from '../layout/logo.png';
import { Link } from 'react-router-dom';
import AuthContext from '../../context/auth/authContext';

const Navbar = () => {
  const authContext = useContext(AuthContext);

  const { isAuthenticated, loadUser, logout, user } = authContext;

  const onLogout = () => {
    logout();
  };

  const onSubmit = (e) => {
    e.preventDefault();
    loadUser();
  };

  const authLinks = (
    <Fragment>
      <li>Hello {user && user.firstName}</li>
      <li className='nav-item ml-2'>
        <a onClick={onLogout} href='#!'>
          <span className='hide-sm'> Logout</span>
        </a>
      </li>
    </Fragment>
  );

  const guestLinks = (
    <Fragment>
      <li className='nav-item pr-2'>
        <Link className='btn btn-primary' to='/register'>
          Sign Up
        </Link>
      </li>
      <li className='nav-item'>
        <Link className='btn btn-primary' to='/login'>
          Login
        </Link>
      </li>
    </Fragment>
  );

  return (
    <nav className='navbar navbar-expand navbar-light bg-light'>
      <Link to='/'>
        <img className='navbar brand' height='60px' src={Logo} alt='logo' />
      </Link>
      <h1>Konsultauktion</h1>
      <ul className='nav navbar-nav ml-auto'>
        <li>
          <button onClick={onSubmit}>
            Load user
          </button>
        </li>
        {isAuthenticated ? authLinks : guestLinks}
      </ul>
    </nav>
  );
};

export default Navbar;
