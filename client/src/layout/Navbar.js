import React from 'react'
import Logo from '../layout/logo.png'
import {Link} from 'react-router-dom'

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand navbar-light bg-light">
            <img className="navbar brand" height='60px' src={Logo} alt="logo"/>
            <h1>Konsultauktion</h1>
            <ul className="nav navbar-nav ml-auto">
                <li className="nav-item pr-2">Sign Up</li><li className="nav-item">Login</li>
            </ul>
            
        </nav>
    )
}

export default Navbar;
