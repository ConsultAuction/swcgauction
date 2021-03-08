import React, { useState, useContext, useEffect } from 'react';
import AuthContext from '../../context/auth/authContext';

const Login = (props) => {
  const authContext = useContext(AuthContext);

  const { login, isAuthenticated } = authContext;

  useEffect(() => {
    if (isAuthenticated) {
      props.history.push('/');
    }
  }, [isAuthenticated, props.history]);

  const [user, setUser] = useState({
    email: '',
    password: '',
  });

  const { email, password } = user;

  const onSubmit = (e) => {
    e.preventDefault();
    login({ email, password });
  };

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  return (
    <div className='container' style={{ maxWidth: '500px' }}>
      <form onSubmit={onSubmit}>
        <h1>Account Login</h1>
        <div className='form-row'>
          <label htmlFor='Email'>Username / Email address</label>
          <input
            type='email'
            className='form-control'
            name='email'
            value={email}
            onChange={onChange}
            required
          />
        </div>
        <div className='form-row'>
          <label htmlFor='Password'>Password</label>
          <input
            type='password'
            className='form-control'
            name='password'
            value={password}
            onChange={onChange}
            required
          />
        </div>
        <div className='form-group mt-4'>
          <input
            className='btn-lg btn-block btn-primary'
            type='submit'
            value='Login'
          />
        </div>
      </form>
    </div>
  );
};

export default Login;
