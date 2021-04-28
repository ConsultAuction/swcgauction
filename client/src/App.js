import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './component/layout/Navbar';
import Home from './component/pages/Home';
import Login from './component/auth/Login';
import Project from './component/pages/Project';
import Success from './component/pages/Success';
import Register from './component/auth/Register';
import ClientUserProfile from './component/pages/ClientUserProfile';
import ConsultantUserProfile from './component/pages/ConsultantUserProfile';
import PrivateRoute from './component/routing/PrivateRoute';

import AuthState from './context/auth/AuthState';
import CountdownState from './context/countdown/countdownState';
import './App.css';
import AuctionState from './context/auction/AuctionState';
import ProjectForm from './component/forms/ProjectForm';
import ProjectOfferForm from './component/forms/ProjectOfferForm';
import SearchbarDropdown from './component/pages/SearchbarDropdown';
import Projects from './component/pages/Projects';
import ProjectOffers from './component/pages/ProjectOffers';
import ProjectOffer from './component/pages/ProjectOffer';
import ProfileState from './context/profile/ProfileState';
import MyOffers from './component/pages/MyOffers';

const App = () => {
  return (
    <Router>
      <AuthState>
        <ProfileState>
          <CountdownState>
            <AuctionState>
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
                  path='/projectOfferForm'
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
                <PrivateRoute
                  exact
                  path='/ProjectOffer'
                  component={ProjectOffer}
                />
                <Route exact path='/registerSuccess' component={Success} />
                <PrivateRoute
                  exact
                  path='/clientUserProfile'
                  component={ClientUserProfile}
                />
                <PrivateRoute
                  exact
                  path='/consultantUserProfile'
                  component={ConsultantUserProfile}
                />
                <PrivateRoute exact path='/myOffers' component={MyOffers} />
              </Switch>
            </AuctionState>
          </CountdownState>
        </ProfileState>
      </AuthState>
    </Router>
  );
};

export default App;
