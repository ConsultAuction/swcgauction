import React, { Fragment, useContext, useEffect } from 'react';
import Logo from '../layout/logo.png';
import { Link } from 'react-router-dom';
import AuthContext from '../../context/auth/authContext';

const Navbar = () => {
  const authContext = useContext(AuthContext);
  const { logout, user } = authContext;

  const onLogout = () => {
    logout();
  };

  useEffect(() => {}, [user]);

  const roleLinks = (
    <Fragment>
      {user !== null && user.role === 'CLIENT' ? (
        <Fragment>
          <li className='nav-item'>
            <Link to='/Project'>Projects</Link>
          </li>
          <li className='nav-item'>
            <Link to='/Search'>Search</Link>
          </li>
        </Fragment>
      ) : (
        <div></div>
      )}
      {user !== null && user.role === 'CONSULTANT' ? (
        <li className='nav-item'>
          <Link to='/ProjectOffers'>Project offers</Link>
        </li>
      ) : (
        <div></div>
      )}
    </Fragment>
  );

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
      <ul className='nav navbar-nav ml-2'> {roleLinks} </ul>
      <ul className='nav navbar-nav ml-auto'>
        {localStorage.isAuthenticated ? authLinks : guestLinks}
      </ul>
    </nav>
  );
};

export default Navbar;
