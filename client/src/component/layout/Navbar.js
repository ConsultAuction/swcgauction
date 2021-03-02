import React, { Fragment } from 'react'
import Logo from '../layout/logo.png'
import {Link} from 'react-router-dom'

const Navbar = () => {

    const guestLinks = (
        <Fragment>
        <li className="nav-item pr-2">
            <Link to='/register'>Sign Up</Link>
        </li>
        <li className="nav-item">
            <Link to='/login'>Login</Link>
        </li>
        </Fragment>
    )
    
    return (
        <nav className="navbar navbar-expand navbar-light bg-light">
            <img className="navbar brand" height='60px' src={Logo} alt="logo"/>
            <h1>Konsultauktion</h1>
            <ul className="nav navbar-nav ml-auto">
                {guestLinks}
                
            </ul>
            
        </nav>
    )
}

export default Navbar;
