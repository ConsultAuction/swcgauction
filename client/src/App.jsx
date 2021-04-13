import React, { useState, useRef,  useEffect, useContext } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import Navbar from './component/layout/Navbar';
import Home from './component/pages/Home';
import Login from './component/auth/Login';
import Register from './component/auth/Register';

import AuthState from './context/auth/AuthState';
import './App.css';
import axios from 'axios';
import AuthContext from './context/auth/authContext'
import createHistory from 'history/createBrowserHistory'



const SearchbarDropdown = (props) => {



    const { options, onInputChange } = props;
    const ulRef = useRef();
    const inputRef = useRef();


    useEffect(() => {
      inputRef.current.addEventListener('click', (event) => {
        event.stopPropagation();
        ulRef.current.style.display = 'flex';
        onInputChange(event);
      });
      document.addEventListener('click', (event) => {
        ulRef.current.style.display = 'none';
      });
    }, []);

    return (
      <div className="search-bar-dropdown">
        <input
          id="search-bar"
          type="text"
          className="form-control"
          placeholder="Search"
          ref={inputRef}
          onChange={onInputChange}
        />
        <ul id="results" className="list-group" ref={ulRef}>
          {options.map((option) => {
            return (
              <button
                type="button"
                key={option.userId}
                onClick={(e) => {
                  inputRef.current.value = option;
                }}
                className="list-group-item list-group-item-action"
              >
                {option.firstName} {option.lastName}
              </button>
            );
          })}
        </ul>
      </div>
    );
  };




 function App  ()  {

  const authContext = useContext(AuthContext);





  const [users, setUsers] = useState([]);
  const [options, setOptions] = useState([]);



  const onInputChange = (event) => {

    console.log(users)

    setOptions(
      users.filter((option) => (option.firstName.toLowerCase() + " " + option.lastName.toLowerCase()).includes(event.target.value.toLowerCase()))
    );
  }


  const getList = async () => {
    const response = await axios.get(`/api/user`);
    return response.data;
  }




  useEffect(() => {
    const getAllUsers = async () => {
      const allUsers = await getList();



      if(allUsers) setUsers(allUsers);

    };

    getAllUsers();
  }, []);



  return (

    <AuthState>
      <Router>
        <Navbar />
        {localStorage.isAuthenticated ? <SearchbarDropdown options={options} onInputChange={onInputChange} /> : null  }
        <Switch>
          <Route exact path='/' component={Home} />
          <Route exact path='/register' component={Register} />
          <Route exact path='/login' component={Login} />
        </Switch>
      </Router>
    </AuthState>
  );

 };


export default App;
