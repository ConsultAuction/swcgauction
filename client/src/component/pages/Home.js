import React, { Fragment } from 'react';

const Home = () => {

    const [userId] = React.useState(
        localStorage.getItem('userId')
      );
    
    let user = null;

    if(localStorage.getItem('user')) {
        user= JSON.parse(localStorage.getItem('user'));
    };

    let userObject;

    if(user){
        userObject = (
            <Fragment>
                <h3>UserId!</h3>
                <p>{userId}</p>
                <p>{user.firstName}</p>
                <p>{user.lastName}</p>
                <p>{user.email}</p>
                <p>{user.role}</p>
                <h3>Contact</h3>
                <p>{user.contact.address}</p>
                <p>{user.contact.city}</p>
                <p>{user.contact.country}</p>
                <p>{user.contact.phoneNumber}</p>
                <p>{user.contact.zipCode}</p>
            </Fragment>
          );
    }
      

    return (
        <div>
            {user ? userObject : null}
        </div>
    )
}

export default Home;
