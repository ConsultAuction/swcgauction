import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './component/layout/Navbar';
import Home from './component/pages/Home';
import Login from './component/auth/Login';
import Project from './component/pages/Project';
import Register from './component/auth/Register';
import PrivateRoute from './component/routing/PrivateRoute';

import AuthState from './context/auth/AuthState';
import CountdownState from './context/countdown/countdownState';
import './App.css';
import AuctionState from './context/auction/AuctionState';
import ProjectForm from './component/forms/ProjectForm';
import ProjectOfferForm from './component/forms/ProjectOfferForm';
import SearchbarDropdown from './component/pages/SearchbarDropdown';
import Projects from './component/pages/Projects';
import ProjectOffers from './component/pages/ProjectOffer';

const App = () => {
  return (
    <AuthState>
      <CountdownState>
        <AuctionState>
          <Router>
            <Navbar />
            <Switch>
              <Route exact path='/' component={Home} />
              <Route exact path='/register' component={Register} />
              <Route exact path='/login' component={Login} />
              <PrivateRoute exact path='/Project' component={Project} />
              <PrivateRoute
                exact
                path='/createProject'
                component={ProjectForm}
              />
              <PrivateRoute
                exact
                path='/projectOffer'
                component={ProjectOfferForm}
              />
              <PrivateRoute
                exact
                path='/Search'
                component={SearchbarDropdown}
              />
              <PrivateRoute exact path='/Projects' component={Projects} />
              <PrivateRoute
                exact
                path='/ProjectOffers'
                component={ProjectOffers}
              />
            </Switch>
          </Router>
        </AuctionState>
      </CountdownState>
    </AuthState>
  );
};

export default App;
