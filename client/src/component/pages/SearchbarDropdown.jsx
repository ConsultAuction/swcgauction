import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const SearchbarDropdown = (props) => {

    const [users, setUsers] = useState([]);
    const [search, setSearch] = useState("");
    const [filteredUsers, setFilteredUsers] = useState([]);

    useEffect(() => {
        axios
            .get(`/api/user/available/hire`)
            .then(res => {
                setUsers(res.data);
            })

    }, []);

    useEffect(() =>{

        setFilteredUsers(
            users.filter((user) =>
                user.firstName.toLowerCase().includes(search.toLowerCase())
            )
        )
    }, [users, search]);

    console.log(users)
    console.log(search);
    console.log(filteredUsers);

    return (
      <div className="search-bar-dropdown">
        <input
          id="search-bar"
          type="text"
          className="form-control"
          placeholder="Search"
          onChange={(e) => setSearch(e.target.value)}
        />
        <ul id="results" className="list-group">

            {filteredUsers.map((user, idx) => (
                <Link to={{
                    pathname: '/Projects',
                    user: {
                        userId: user.userId
                    }
                }} >
                    <p>{user.firstName} {user.lastName}</p>
                </Link>
            ))}
         
        </ul>
      </div>
    );
  };

  export default SearchbarDropdown