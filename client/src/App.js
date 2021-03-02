import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Navbar from './component/layout/Navbar';
import Home from './component/pages/Home'
import Login from './component/auth/Login'
import './App.css';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Switch>
        <Route exact path='/' component={Home} />
        {/* <Route exact path='/register' component={Register} />  */}
        <Route exact path='/login' component={Login} />

      </Switch>

    </Router>
    
  );
}

export default App;
