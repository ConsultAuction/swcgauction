import React, {useState} from 'react'

const Login = () => {

    const [user, setUser] = useState({
        email: '',
        password: '',
      });

      const { email, password } = user;

    const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });
    
    return (
        <div className="container" style={{maxWidth: '500px'}}>
        <form>
            <h1>Account Login</h1>
            <div className="form-group">
                <label htmlFor="Email">Username / Email address</label>
                <input 
                    type="email"
                    className="form-control"
                    name="email"
                    value={email}
                    onChange={onChange}
                    required
                />
            </div>
            <div className="form-group">
                <label htmlFor="Password">Password</label>
                <input 
                    type="password" 
                    className="form-control" 
                    name="password"
                    value={password}
                    onChange={onChange}
                    required />
            </div>
            </form>
            <div className="text-left mb-2">Forgot Password?</div>
            <button type="submit" className="btn btn-primary">Login</button>
        
        
        </div>
        
    )
}

export default Login;
