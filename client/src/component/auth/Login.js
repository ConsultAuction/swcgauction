import React from 'react'

const Login = () => {
    return (
        <div className="container" style={{maxWidth: '500px'}}>
        <form>
            <h1>Account Login</h1>
            <div className="form-group">
                <label htmlFor="Email">Username / Email address</label>
                <input type="email" className="form-control" name="email"/>
            </div>
            <div className="form-group">
                <label htmlFor="Password">Password</label>
                <input type="password" className="form-control" name="password" />
            </div>
            <button type="submit" className="btn btn-primary">Login</button>
        </form>
        </div>
        
    )
}

export default Login;
