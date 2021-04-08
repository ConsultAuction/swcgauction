import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './component/layout/Navbar';
import Home from './component/pages/Home';
import Login from './component/auth/Login';
import Project from './component/pages/Project';
import Register from './component/auth/Register';
import PrivateRoute from './component/routing/PrivateRoute';

import AuthState from './context/auth/AuthState';
import CountDownState from './context/countdown/countdownState';
import './App.css';

const App = () => {
  return (
    <AuthState>
      <CountDownState>
        <Router>
          <Navbar />
          <Switch>
            <Route exact path='/' component={Home} />
            <Route exact path='/register' component={Register} />
            <Route exact path='/login' component={Login} />
            <PrivateRoute exact path='/createProject' component={Project} />
          </Switch>
        </Router>
      </CountDownState>
    </AuthState>
  );
};

export default App;
